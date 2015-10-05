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

//    @Query(value = "{'abstractResource.$accession' : ?0, 'abstractResource.$database' : ?1}", count = true)
//    long getNumberEventByHttpEventDataSetResource(String acc, String database);
//
//    @Query(value = "{'abstractResource.$id' : ?0}", count = true)
//    long getNumberEventByDataResource(ObjectId _id);
//
//    List<HttpEvent> findByAbstractResource(AbstractResource abstractResource);

    @Query("{accession: ?0}")
    public ExpOutputDataset findByAccessionQuery(String accession);

    @Query("{dataType: ?0}")
    public ExpOutputDataset readAllInOneType(String dataType);
}
