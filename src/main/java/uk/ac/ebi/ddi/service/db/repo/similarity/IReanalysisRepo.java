package uk.ac.ebi.ddi.service.db.repo.similarity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;

/**
 * Created by gaur on 27/07/17.
 */
public interface IReanalysisRepo extends MongoRepository<ReanalysisData,ObjectId> {
}
