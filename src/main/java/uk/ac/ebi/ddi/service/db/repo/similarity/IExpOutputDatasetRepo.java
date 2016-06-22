package uk.ac.ebi.ddi.service.db.repo.similarity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.similarity.ExpOutputDataset;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author Mingze
 */

public interface IExpOutputDatasetRepo extends MongoRepository<ExpOutputDataset,ObjectId>{

    @Query("{$and: [{accession: ?0},{database: ?1}]}")
    public ExpOutputDataset findByAccessionQuery(String accession, String database);

    @Query("{dataType: ?0}")
    public ExpOutputDataset readAllInOneType(String dataType);
}
