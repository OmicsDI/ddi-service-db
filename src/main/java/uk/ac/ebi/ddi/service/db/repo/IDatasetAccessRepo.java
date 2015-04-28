package uk.ac.ebi.ddi.service.db.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.ebi.ddi.service.db.model.DatasetAccess;

import java.math.BigInteger;



/**
 * DatasetAccess MongoDB instance that extend the MongoDBRepository
 *
 * @author ypriverol
 */
public interface IDatasetAccessRepo extends MongoRepository<DatasetAccess,BigInteger>{

}
