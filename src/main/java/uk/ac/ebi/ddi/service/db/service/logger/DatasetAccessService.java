package uk.ac.ebi.ddi.service.db.service.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetResource;
import uk.ac.ebi.ddi.service.db.repo.logger.IDatasetResourceRepo;

import java.math.BigInteger;


/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
public class DatasetAccessService implements IDatasetAccessService {

    @Autowired
    private IDatasetResourceRepo datasetAccessRepo;

    @Override
    public DatasetResource save(DatasetResource datasetResource) {
        return datasetAccessRepo.save(datasetResource);
    }

    @Override
    public DatasetResource read(BigInteger id) {
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public Page<DatasetResource> readAll(int pageStart, int size) {
        return datasetAccessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public DatasetResource update(DatasetResource datasetResource) {

        DatasetResource existingDatasetResource = datasetAccessRepo.findOne(datasetResource.getId());

        existingDatasetResource.setAccession(datasetResource.getAccession());
        existingDatasetResource.setDatabase(datasetResource.getDatabase());

        return datasetAccessRepo.save(existingDatasetResource);
    }

    @Override
    public DatasetResource delete(BigInteger id) {
        datasetAccessRepo.delete(id);
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public DatasetResource addAccess(String acc, String database, HttpEvent httpEvent) {
        DatasetResource datasetResource = read(acc, database);
        update(datasetResource);
        return datasetResource;
    }

    @Override
    public DatasetResource read(String acc, String database) {
        DatasetResource datasetResource = datasetAccessRepo.findByAccessionDatabaseQuery(acc, database);
        return datasetResource;
    }


    public long countDatasetAccess(String acc, String database){
        return 0;
    }



}
