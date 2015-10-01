package uk.ac.ebi.ddi.service.db.repo.intersection;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.enrichment.EnrichedDataset;
import uk.ac.ebi.ddi.service.db.model.intersection.TermInDB;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author mingze
 */

public interface ITermInDBRepo extends MongoRepository<TermInDB,ObjectId>{

//    @Query(value = "{'abstractResource.$accession' : ?0, 'abstractResource.$database' : ?1}", count = true)
//    long getNumberEventByHttpEventDataSetResource(String acc, String database);
//
//    @Query(value = "{'abstractResource.$id' : ?0}", count = true)
//    long getNumberEventByDataResource(ObjectId _id);
//
//    List<HttpEvent> findByAbstractResource(AbstractResource abstractResource);

    @Query("{termName: ?0}")
    public TermInDB findByNameQuery(String termName);

    @Query("{index: ?0}")
    public TermInDB findByIndexQuery(String index);
}
