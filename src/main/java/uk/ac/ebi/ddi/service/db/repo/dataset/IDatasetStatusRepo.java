package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetStatus;

import java.util.List;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2016
 */
public interface IDatasetStatusRepo extends MongoRepository<DatasetStatus,ObjectId> {

    @Query("{'$and':[{accession : ?0}, {database : ?1}]}")
    public List<DatasetStatus> findByAccessionDatabaseQuery(String acc, String database);

    @Query(value="{ status : ?0 }")
    public List<DatasetStatus> findByStatus(String status);

    @Query("{_id: ?0}")
    public DatasetStatus findByIdDatabaseQuery(ObjectId _id);

}
