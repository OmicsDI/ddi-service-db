package uk.ac.ebi.ddi.service.db.service.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.Scores;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;
import uk.ac.ebi.ddi.service.db.repo.similarity.ICitationRepo;
import uk.ac.ebi.ddi.service.db.repo.similarity.IReanalysisRepo;
import uk.ac.ebi.ddi.service.db.service.dataset.DatasetService;
import uk.ac.ebi.ddi.service.db.utils.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by gaur on 27/07/17.
 */
@Service
public class ReanalysisDataService implements IReanalysisDataService {

    @Autowired
    public IReanalysisRepo reanalysisRepo;

    @Autowired
    private DatasetService datasetService;

    public void saveReanalysis(ReanalysisData reanalysisData){
        //String database = Constants.Database.retriveAnchorName(visit.getDatabase());
        Dataset dataset = datasetService.read(reanalysisData.getAccession(),reanalysisData.getDatabase());

        if (dataset != null) {
            if(dataset.getScores() != null) {
                Scores score = dataset.getScores();

                score.setReanalysisCount(reanalysisData.getTotal());
                dataset.setScores(score);
            }else{
                Scores scores = new Scores();
                scores.setReanalysisCount(reanalysisData.getTotal());
                dataset.setScores(scores);
            }
            HashSet<String> count = new HashSet<String>();
            count.add(reanalysisData.getTotal().toString());
            dataset.getAdditional().put(Constants.REANALYSIS_FIELD,count);
            datasetService.update(dataset.getId(),dataset);
        }
        //reanalysisRepo.save(reanalysisData);
    }

    public ReanalysisData getReanalysisCount(String accession,String database){
        return reanalysisRepo.getReanalysisCount(accession,database);
    }

    public void updateReanalysisKeywords(){
        List<ReanalysisData> reanalysisDataList = reanalysisRepo.getDatasetsByRelation("Reanalysis of");

        reanalysisDataList.parallelStream().map(dst -> {
            Dataset dataset = datasetService.read(dst.getAccession(),dst.getDatabase());
            if(dataset != null) {
                String keyword = getReanalysisKeyword(dataset.getDatabase());
                if (!StringUtils.isEmpty(keyword)) {
                    if (dataset.getAdditional().containsKey(Constants.SUBMITTER_KEYWORDS_FIELD)) {
                        Set<String> keywords = dataset.getAdditional().get(Constants.SUBMITTER_KEYWORDS_FIELD);
                        keywords.add(keyword);
                        dataset.getAdditional().put(Constants.SUBMITTER_KEYWORDS_FIELD, keywords);
                        datasetService.save(dataset);
                    } else {
                        Set<String> keywords = new HashSet<String>();
                        keywords.add(keyword);
                        dataset.getAdditional().put(Constants.SUBMITTER_KEYWORDS_FIELD, keywords);
                        datasetService.save(dataset);
                    }
                }
            }
            return true;
        }).count();
    }

    public String getReanalysisKeyword(String database){

        String keyword = "";
        Stream<String> resourceReanalysis = Stream.of("ExpressionAtlas" , "PeptideEaltas", "GPMDB");
        Stream<String> independentReanalysis = Stream.of("BioModels","GEO");

        if(resourceReanalysis.anyMatch(x -> Objects.equals(x, database))) {
            keyword = "Resource Reanalysis";
        }else if(independentReanalysis.anyMatch(x -> Objects.equals(x, database)))
        {
            keyword = "Independent Lab Reanalysis";
        }
        return keyword;
    }

}
