package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.dataset.OmicsType;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2016
 */
public interface IOmicsTypeRepo extends MongoRepository<OmicsType,ObjectId> {

}
