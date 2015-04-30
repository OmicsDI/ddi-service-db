package uk.ac.ebi.ddi.service.db.service.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetAccess;
import uk.ac.ebi.ddi.service.db.repo.logger.IDatasetAccessRepo;

import java.math.BigInteger;
import java.util.ArrayList;
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
    public DatasetAccess save(DatasetAccess datasetAccess) {
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

        existingDatasetAccess.setAccession(datasetAccess.getAccession());
        existingDatasetAccess.setDatabase(datasetAccess.getDatabase());
        existingDatasetAccess.setHttpEventList(datasetAccess.getHttpEventList());

        return datasetAccessRepo.save(existingDatasetAccess);
    }

    @Override
    public DatasetAccess delete(BigInteger id) {
        datasetAccessRepo.delete(id);
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public DatasetAccess addAccess(String acc, String database, HttpEvent httpEvent) {
        DatasetAccess datasetAccess = read(acc, database);
        List<HttpEvent> httpEventList = datasetAccess.getHttpEventList();
        if(httpEventList == null)
            httpEventList = new ArrayList<HttpEvent>();
        httpEventList.add(httpEvent);
        datasetAccess.setHttpEventList(httpEventList);
        update(datasetAccess);
        return datasetAccess;
    }

    @Override
    public DatasetAccess read(String acc, String database) {
        DatasetAccess datasetAccess = datasetAccessRepo.findByAccessionDatabaseQuery(acc, database);
        return datasetAccess;
    }


    public long countDatasetAccess(String acc, String database){
        return 0;
    }



}
