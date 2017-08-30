package uk.ac.ebi.ddi.service.db.service.similarity;

import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;

/**
 * Created by gaur on 27/07/17.
 */
public interface IReanalysisDataService {


    void saveReanalysis(ReanalysisData reanalysisData);

    void getReanalysisCount(String accession,String database);
}
