package uk.ac.ebi.ddi.service.db.repo.logger;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.logger.Resource;

import java.math.BigInteger;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date   29/04/2015
 */
public interface IResourceAccessRepo extends MongoRepository<Resource, BigInteger>{

}
