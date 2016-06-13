package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.ddi.service.db.model.dataset.Database;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatabaseRepo;

/**
 * Created by yperez on 26/05/2016.
 */
public class DatabaseService implements  IDatabaseService{

    @Autowired
    private IDatabaseRepo databaseRepo;

    @Override
    public Database save(Database database) {
        return databaseRepo.save(database);
    }

    @Override
    public Database read(ObjectId id) {
        return databaseRepo.findOne(id);
    }

    @Override
    public Database update(ObjectId id, Database database) {
        Database updateDatabase = read(id);
        if(updateDatabase != null)
            database.set_id(id);
        return databaseRepo.save(database);
    }

    @Override
    public void delete(ObjectId id) {
        databaseRepo.delete(id);
    }

    @Override
    public Database read(String name) {
        return databaseRepo.findByNameQuery(name);
    }
}
