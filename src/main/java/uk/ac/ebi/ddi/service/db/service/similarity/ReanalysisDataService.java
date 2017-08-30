package uk.ac.ebi.ddi.service.db.service.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;
import uk.ac.ebi.ddi.service.db.repo.similarity.ICitationRepo;
import uk.ac.ebi.ddi.service.db.repo.similarity.IReanalysisRepo;

/**
 * Created by gaur on 27/07/17.
 */
public class ReanalysisDataService implements IReanalysisDataService {
    @Autowired
    public IReanalysisRepo reanalysisRepo;

    public void saveReanalysis(ReanalysisData reanalysisData){
        reanalysisRepo.save(reanalysisData);
    }

    public void getReanalysisCount(String accession,String database){
        reanalysisRepo.getReanalysisCount(accession,database);
    }
}
