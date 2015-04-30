package uk.ac.ebi.ddi.service.db.repo.logger;


import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;

import java.math.BigInteger;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author ypriverol
 */
public interface IHttpEventRepo extends MongoRepository<HttpEvent,BigInteger> {
}
