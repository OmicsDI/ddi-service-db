package uk.ac.ebi.ddi.service.db.repo.similarity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.model.similarity.DatasetStatInfo;

/**
 * Created by gaur on 20/07/17.
 */
public interface ICitationRepo extends MongoRepository<Citations,ObjectId> {
}
