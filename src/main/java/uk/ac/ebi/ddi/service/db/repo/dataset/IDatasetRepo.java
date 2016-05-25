package uk.ac.ebi.ddi.service.db.repo.dataset;


import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;


import java.util.List;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author Mingze
 */

public interface IDatasetRepo extends MongoRepository<Dataset,ObjectId>{

    @Query("{'$and':[{accession : ?0}, {database : ?1}]}")
    public Dataset findByAccessionDatabaseQuery(String acc, String database);

    @Query(value="{ database : ?0 }", fields ="{database : 1, accession : 1, hashCode: 1, currentStatus: 1}")
    public List<Dataset> findByDatabase(String name);

    @Query("{_id: ?0}")
    public Dataset findByIdDatabaseQuery(ObjectId _id);

}
