package uk.ac.ebi.ddi.service.db.service.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatasetCountService implements IDatasetCountService{

    @Autowired
    DatasetService datasetService;


    public void saveDatasetDownloadCount() {

    }
   /* ElasticSearchWsClient elasticSearchClient = new ElasticSearchWsClient
            (new ElasticSearchWsConfigProd(9200,"10.3.10.28","readall","readall"));

    List<String> databases = Arrays.asList(Constants.Database.ARRAY_EXPRESS.getDatabaseName(),
            Constants.Database.PRIDE.getDatabaseName(), Constants.Database.EXPRESSION_ATLAS.getDatabaseName(),
            Constants.Database.EVA.getDatabaseName(), Constants.Database.METABOLIGHTS.getDatabaseName(),
            Constants.Database.ENA.getDatabaseName());

    public void saveDatasetDownloadCount() {
        long datasetCount = datasetService.getDatasetCount();
        int pageSize = 100;

        elasticSearchClient.setParallel(true);

*//*        Map<String, Map<String, Multiset<String>>> prideDownloads = elasticSearchClient.
                getDataDownloads(ElasticSearchWsConfigProd.DB.Pride,
                        "PXD000561", LocalDate.now());*//*

        //prideDownloads.size();
        for(int i =0; i < datasetCount/pageSize; i++ ) {

            datasetService.readAll(i, pageSize).map(dt ->  {
                if(databases.contains(dt.getDatabase())) {
                    Map<String, Map<String, Multiset<String>>> prideDownloads = elasticSearchClient.
                            getDataDownloads(ElasticSearchWsConfigProd.DB.valueOf(dt.getDatabase()),
                                    dt.getAccession(), LocalDate.now());
                    Dataset dataset = datasetService.read(dt.getAccession(), dt.getDatabase());
                    Set<String> downloadCount = new HashSet<String>();
                    if(prideDownloads != null) {
                        int count = prideDownloads.entrySet().stream().mapToInt(dst ->
                                dst.getValue().entrySet().stream().mapToInt(dtr -> dtr.getValue().elementSet().stream().mapToInt(dtrc -> dtr.getValue().count(dtrc)).sum() ).sum()
                        ).sum();
                        downloadCount.add(String.valueOf(count));
                        dataset.getAdditional().put(Constants.DOWNLOAD_COUNT, downloadCount);
                        datasetService.save(dataset);
                    }
                }
                return true;
            });
        }
    }*/

}
