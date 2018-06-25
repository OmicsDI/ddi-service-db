package uk.ac.ebi.ddi.service.db.service.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.dataset.UnMergeDatasets;
import uk.ac.ebi.ddi.service.db.repo.dataset.IUnMergeDatasetsRepo;

import java.util.List;

@Service
public class UnMergeDatasetService implements IUnMergeDatasetService{

    @Autowired
    private IUnMergeDatasetsRepo iUnMergeDatasetsRepo;

    public void save(UnMergeDatasets unMergeDatasets){
        iUnMergeDatasetsRepo.save(unMergeDatasets);
    }

    public List<UnMergeDatasets> findAll(){
        List<UnMergeDatasets> datasets = iUnMergeDatasetsRepo.findAll();
        return datasets;
    }
}
