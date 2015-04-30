package uk.ac.ebi.ddi.service.db.repo.logger;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractEvent;
import uk.ac.ebi.ddi.service.db.model.logger.GenericEvent;

import java.math.BigInteger;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 30/04/2015
 */
public interface IEventService extends MongoRepository<GenericEvent, BigInteger>{

}
