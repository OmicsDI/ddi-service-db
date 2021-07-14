package uk.ac.ebi.ddi.service.db.service.dblucene;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.dblucene.DbLuceneMapping;
import uk.ac.ebi.ddi.service.db.repo.dblucene.IDbLuceneMappingRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by gaur on 29/3/17.
 */
@Service
public class DbLuceneMappingService implements IDbLuceneMappingService {

    @Autowired
    public IDbLuceneMappingRepo dbLuceneMappingRepo;

    @Override
    public void save(DbLuceneMapping dbLuceneMapping) {
         dbLuceneMappingRepo.save(dbLuceneMapping);
    }

    @Override
    public DbLuceneMapping read(ObjectId id) {
        DbLuceneMapping dbLuceneMapping = dbLuceneMappingRepo.findOne(id);
        return dbLuceneMapping;
    }

    @Override
    public DbLuceneMapping update(ObjectId id, DbLuceneMapping dbLuceneMapping) {
        DbLuceneMapping updateFeedback = read(id);
        if(updateFeedback != null)
            dbLuceneMapping.setId(id);
        return dbLuceneMappingRepo.save(dbLuceneMapping);
    }

    @Override
    public void delete(ObjectId id) {
        dbLuceneMappingRepo.delete(id);
    }

    @Override
    public List<DbLuceneMapping> getDatabase(String luceneName){
        return dbLuceneMappingRepo.findByLuceneName(luceneName);
    }

    @Override
    public List<DbLuceneMapping> getLuceneName(String databaseName){
        return dbLuceneMappingRepo.findByDbName(databaseName);
    }

    @Override
    public List<DbLuceneMapping> readAll(){
        return dbLuceneMappingRepo.findAll();
    }

}
