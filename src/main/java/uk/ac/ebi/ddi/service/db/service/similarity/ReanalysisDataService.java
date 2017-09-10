package uk.ac.ebi.ddi.service.db.service.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.Scores;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;
import uk.ac.ebi.ddi.service.db.repo.similarity.ICitationRepo;
import uk.ac.ebi.ddi.service.db.repo.similarity.IReanalysisRepo;
import uk.ac.ebi.ddi.service.db.service.dataset.DatasetService;

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
        Dataset dataset = datasetService.read(reanalysisData.getAccession(),reanalysisData.getDatabase());
        if (dataset != null) {
            if(dataset.getScores() != null) {
                dataset.getScores().setReanalysisCount(reanalysisData.getTotal());
            }else{
                Scores scores = new Scores();
                scores.setReanalysisCount(reanalysisData.getTotal());
                dataset.setScores(scores);
            }
            datasetService.update(dataset.getId(),dataset);
        }
        reanalysisRepo.save(reanalysisData);
    }

    public ReanalysisData getReanalysisCount(String accession,String database){
        return reanalysisRepo.getReanalysisCount(accession,database);
    }


}
