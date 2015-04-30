package uk.ac.ebi.ddi.service.db.repo.logger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetResource;

import java.math.BigInteger;

/**
 * DatasetAccess MongoDB instance that extend the MongoDBRepository
 *
 * @author ypriverol
 */
public interface IDatasetResourceRepo extends MongoRepository<DatasetResource,BigInteger>{

    @Query("{accession : ?0}, {database : ?1}")
    public DatasetResource findByAccessionDatabaseQuery(String acc, String database);

}
