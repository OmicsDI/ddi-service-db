package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.dataset.Database;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2016
 */
public interface IDatabaseRepo extends MongoRepository<Database, ObjectId>{

    @Query("{name: ?0}")
    public Database findByNameQuery(String name);

}
