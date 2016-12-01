package uk.ac.ebi.ddi.service.db.repo.similarity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.similarity.TermInDB;

import java.util.List;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author mingze
 */

public interface ITermInDBRepo extends MongoRepository<TermInDB,ObjectId>{

    @Query("{termAccession: ?0}")
    public TermInDB findByNameQuery(String termName);

    @Query("{dataType: ?0}")
    public List<TermInDB> findByDataType(String dataType);


    @Query("{'$and':[{dataType: ?0},{IDFCalculated: 'false'}]}")
    public List<TermInDB> findUncalculatedTermsByDataType(String dataType);

}
