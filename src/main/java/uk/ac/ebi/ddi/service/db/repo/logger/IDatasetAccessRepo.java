package uk.ac.ebi.ddi.service.db.repo.logger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetAccess;

import java.math.BigInteger;

/**
 * DatasetAccess MongoDB instance that extend the MongoDBRepository
 *
 * @author ypriverol
 */
public interface IDatasetAccessRepo extends MongoRepository<DatasetAccess,BigInteger>{

    @Query("{accession : ?0}, {database : ?1}")
    public DatasetAccess findByAccessionDatabaseQuery(String acc, String database);

}
