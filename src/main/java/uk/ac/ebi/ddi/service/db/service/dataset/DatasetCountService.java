package uk.ac.ebi.ddi.service.db.service.dataset;

import com.google.common.collect.Multiset;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.ddi.downloas.logs.ElasticSearchWsClient;
import uk.ac.ebi.ddi.downloas.logs.ElasticSearchWsConfigProd;
import uk.ac.ebi.ddi.service.db.model.dataset.Database;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.utils.Constants;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class DatasetCountService{

    @Autowired
    DatasetService datasetService;



    ElasticSearchWsClient elasticSearchClient = new ElasticSearchWsClient
            (new ElasticSearchWsConfigProd(0,"","",""));

    List<String> databases = Arrays.asList(Constants.Database.ARRAY_EXPRESS.getDatabaseName(),
            Constants.Database.PRIDE.getDatabaseName(), Constants.Database.EXPRESSION_ATLAS.getDatabaseName(),
            Constants.Database.EVA.getDatabaseName(), Constants.Database.METABOLIGHTS.getDatabaseName(),
            Constants.Database.ENA.getDatabaseName());

    public void saveDatasetDownloadCount() {

        long datasetCount = datasetService.getDatasetCount();
        int pageSize = 100;

        elasticSearchClient.setParallel(true);

        for(int i =0; i < datasetCount /pageSize; i++ ) {

            datasetService.readAll(i, pageSize).map(dt ->  {
                if(databases.contains(dt.getDatabase())) {
                    Map<String, Map<String, Multiset<String>>> prideDownloads = elasticSearchClient.
                            getDataDownloads(ElasticSearchWsConfigProd.DB.valueOf(dt.getDatabase()),
                                    dt.getAccession(), LocalDate.now());
                    Dataset dataset = datasetService.read(dt.getAccession(), dt.getDatabase());
                    Set<String> downloadCount = new HashSet<String>();
                    if(prideDownloads != null) {
                        downloadCount.add(String.valueOf(prideDownloads.size()));
                        dataset.getAdditional().put(Constants.DOWNLOAD_COUNT, downloadCount);
                        datasetService.save(dataset);
                    }
                }
                return true;
            });
        }

    }

}
