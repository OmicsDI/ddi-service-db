package uk.ac.ebi.ddi.service.db.repo.enrichment;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.enrichment.Synonym;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractResource;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.repo.logger.IHttpEventCustom;

import java.util.List;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author ypriverol
 */

public interface ISynonymsRepo extends MongoRepository<Synonym,ObjectId>{

//    @Query(value = "{'abstractResource.$accession' : ?0, 'abstractResource.$database' : ?1}", count = true)
//    long getNumberEventByHttpEventDataSetResource(String acc, String database);
//
//    @Query(value = "{'abstractResource.$id' : ?0}", count = true)
//    long getNumberEventByDataResource(ObjectId _id);
//
//    List<HttpEvent> findByAbstractResource(AbstractResource abstractResource);

    @Query("{label : ?0}")
    public Synonym findByLabelQuery(String wordLabel);

}
