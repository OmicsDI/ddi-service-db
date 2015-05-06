package uk.ac.ebi.ddi.service.db.service.logger;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetResource;
import uk.ac.ebi.ddi.service.db.repo.logger.IDatasetResourceRepo;

import java.math.BigInteger;


/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
@Component
public class DatasetResourceService implements IDatasetResourceService {

    @Autowired
    private IDatasetResourceRepo datasetAccessRepo;

    @Override
    public DatasetResource save(DatasetResource datasetResource) {
        return datasetAccessRepo.save(datasetResource);
    }

    @Override
    public DatasetResource read(ObjectId id) {
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
    public DatasetResource delete(ObjectId id) {
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





}
