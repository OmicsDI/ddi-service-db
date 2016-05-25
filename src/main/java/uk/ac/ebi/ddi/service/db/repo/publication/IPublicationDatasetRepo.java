package uk.ac.ebi.ddi.service.db.repo.publication;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.publication.PublicationDataset;

import java.util.List;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author Mingze
 */

public interface IPublicationDatasetRepo extends MongoRepository<PublicationDataset,ObjectId>{

    @Query("{'$and':[{accession : ?0}, {database : ?1}]}")
    public PublicationDataset findByAccessionDatabaseQuery(String acc, String database);

    @Query(value="{ pubmedId : ?0 }")
    public List<PublicationDataset> findByPubmedId(String name);

    @Query("{_id: ?0}")
    public Dataset findByIdQuery(ObjectId _id);

}
