package uk.ac.ebi.ddi.service.db.service.similarity;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.exception.DBWriteException;
import uk.ac.ebi.ddi.service.db.model.similarity.ExpOutputDataset;
import uk.ac.ebi.ddi.service.db.model.similarity.TermInDB;
import uk.ac.ebi.ddi.service.db.repo.similarity.ITermInDBRepo;

import java.util.List;

/**
 * Created by mingze on 30/07/15.
 */

@Component
public class TermInDBService implements ITermInDBService {


    @Autowired
    private ITermInDBRepo accessRepo;

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public TermInDB insert(TermInDB termInDB) {
        if (accessRepo.findByNameQuery(termInDB.getTermName()) != null) {
            return termInDB;
        }

        TermInDB insertedTerm = accessRepo.insert(termInDB);
        if ((insertedTerm.getId() == null))
            throw new DBWriteException("Insert failed, no _id assigned to this termInDB");
//        insertedTerm.setNextTermInDB(insertedTerm.getId());
        return insertedTerm;
    }



    @Override
    public TermInDB read(ObjectId id) {
        return accessRepo.findOne(id);
    }

    @Override
    public Page<TermInDB> readAll(int pageStart, int size) {
        System.out.println("total in collection:" + accessRepo.count());
        return accessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public TermInDB update(TermInDB termInDB) {
//        TermInDB existingTermInDB = accessRepo.findOne(termInDB.getId());
//        existingTermInDB.setNextTermInDB(termInDB.getNextTermInDB());

        return accessRepo.save(termInDB);
    }

    @Override
    public TermInDB delete(ObjectId id) {
        accessRepo.delete(id);
        return accessRepo.findOne(id);
    }

    @Override
    public TermInDB readByName(String termName) {
        if ((termName == null))
            throw new DBWriteException(" The reference to the original resource should contain a string");

        return accessRepo.findByNameQuery(termName);

    }


    @Override
    public boolean isTermExist(String termName) {
        TermInDB mainTerm = accessRepo.findByNameQuery(termName);
        return (mainTerm != null);
    }

    @Override
    public long getNumberOfTermsInDB() {
        return (accessRepo.count());
    }

    @Override
    public void deleteAll() {
        accessRepo.deleteAll();
    }

    @Override
    public List<TermInDB> readAllInOneType(String dataType){
        Query query = new Query();
        query.addCriteria(Criteria.where("dataType").is(dataType));
        return mongoOperation.find(query, TermInDB.class);
    }
}
