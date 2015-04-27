package uk.ac.ebi.ddi.service.db.service.access;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.ddi.service.db.model.DatasetAccess;
import uk.ac.ebi.ddi.service.db.repo.IDatasetAccessRepo;

import java.math.BigInteger;
import java.util.List;

/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
public class AccessDatasetService implements IDatasetAccessService {

    @Autowired
    private IDatasetAccessRepo datasetAccessRepo;

    @Override
    public DatasetAccess create(DatasetAccess datasetAccess) {
        return datasetAccessRepo.save(datasetAccess);
    }

    @Override
    public DatasetAccess read(BigInteger id) {
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public List<DatasetAccess> readAll() {
        return datasetAccessRepo.findAll();
    }

    @Override
    public DatasetAccess update(DatasetAccess datasetAccess) {

        DatasetAccess existingEvent = datasetAccessRepo.findOne(datasetAccess.getId());

        existingEvent.setAccessDate(datasetAccess.getAccessDate());
        existingEvent.setAccession(datasetAccess.getAccession());
        existingEvent.setDatabase(datasetAccess.getDatabase());

        return datasetAccessRepo.save(existingEvent);
    }

    @Override
    public DatasetAccess delete(BigInteger id) {
        datasetAccessRepo.delete(id);
        return datasetAccessRepo.findOne(id);
    }



}
