package uk.ac.ebi.ddi.service.db.repo.logger;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetResource;

import java.math.BigInteger;

/**
 * DatasetResource MongoDB instance that extend the MongoDBRepository
 *
 * @author ypriverol
 */
public interface IDatasetResourceRepo extends MongoRepository<DatasetResource,ObjectId>{

    @Query("{accession : ?0}, {database : ?1}")
    public DatasetResource findByAccessionDatabaseQuery(String acc, String database);

    @Query("{_id: ?0}")
    public DatasetResource findByIdDatabaseQuery(ObjectId _id);

}
