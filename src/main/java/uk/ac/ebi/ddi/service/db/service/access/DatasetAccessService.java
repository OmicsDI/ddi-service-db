package uk.ac.ebi.ddi.service.db.service.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ebi.ddi.service.db.model.DatasetAccess;
import uk.ac.ebi.ddi.service.db.repo.IDatasetAccessRepo;

import java.math.BigInteger;
import java.util.List;

/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
public class DatasetAccessService implements IDatasetAccessService {

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
    public Page<DatasetAccess> readAll(int pageStart, int size) {
        return datasetAccessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public DatasetAccess update(DatasetAccess datasetAccess) {

        DatasetAccess existingDatasetAccess = datasetAccessRepo.findOne(datasetAccess.getId());

        existingDatasetAccess.setAccessDate(datasetAccess.getAccessDate());
        existingDatasetAccess.setAccession(datasetAccess.getAccession());
        existingDatasetAccess.setDatabase(datasetAccess.getDatabase());

        return datasetAccessRepo.save(existingDatasetAccess);
    }

    @Override
    public DatasetAccess delete(BigInteger id) {
        datasetAccessRepo.delete(id);
        return datasetAccessRepo.findOne(id);
    }

    public long countDatasetAccess(String acc, String database){
        return 0;
    }



}
