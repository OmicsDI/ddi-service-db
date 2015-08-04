package uk.ac.ebi.ddi.service.db.service.enrichment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.exception.DBWriteException;
import uk.ac.ebi.ddi.service.db.model.enrichment.Synonym;
import uk.ac.ebi.ddi.service.db.repo.enrichment.ISynonymsRepo;

import java.util.ArrayList;

/**
 * Created by mingze on 30/07/15.
 */

@Component
public class SynonymsService implements ISynonymsService {


    @Autowired
    private ISynonymsRepo accessRepo;

    @Override
    public Synonym insert(Synonym synonym) {
        if (accessRepo.findByLabelQuery(synonym.getlabel()) != null) {
            return synonym;
        }

        Synonym insertedWord = accessRepo.insert(synonym);
        if ((insertedWord.getId() == null))
            throw new DBWriteException("Insert failed, no _id assigned to this synonym");
        insertedWord.setNextSynonym(insertedWord.getId());
        return insertedWord;
    }

    @Override
    public Synonym insertAsSynonym(Synonym mainWord, Synonym synonym) {

        if ((mainWord.getId() == null))
            throw new DBWriteException(" The reference to the original resource should contain an Id");

        if (accessRepo.findByLabelQuery(mainWord.getlabel()) == null) {
            throw new DBWriteException("Can not find mainWord(" + mainWord + ") in this DB");
        }

        if (accessRepo.findByLabelQuery(synonym.getlabel()) != null) {
            return synonym;
        }

        Synonym insertedWord = accessRepo.insert(synonym);
        if ((synonym.getId() == null))
            throw new DBWriteException("Insert failed, no _id assigned to this synonym");
        insertedWord.setNextSynonym(mainWord.getNextSynonym());
        mainWord.setNextSynonym(insertedWord.getId());
        update(mainWord);
        update(insertedWord);

        return insertedWord;
    }

    @Override
    public Synonym read(ObjectId id) {
        return accessRepo.findOne(id);
    }

    @Override
    public Page<Synonym> readAll(int pageStart, int size) {
        return accessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public Synonym update(Synonym synonym) {
//        Synonym existingSynonym = accessRepo.findOne(synonym.getId());
//        existingSynonyms.setNextSynonym(synonym.getNextSynonym());

        return accessRepo.save(synonym);
    }

    @Override
    public Synonym delete(ObjectId id) {
        accessRepo.delete(id);
        return accessRepo.findOne(id);
    }

    @Override
    public Synonym readByLabel(String label) {
        if ((label == null))
            throw new DBWriteException(" The reference to the original resource should contain a string");

        Synonym synonym = accessRepo.findByLabelQuery(label);
        return synonym;

    }

    @Override
    public ArrayList<String> getAllSynonyms(String wordLabel) {
        ArrayList<String> wordList = new ArrayList<String>();
        Synonym mainWord = accessRepo.findByLabelQuery(wordLabel);
        ObjectId mainId = mainWord.getId();
        ObjectId nextId = mainWord.getNextSynonym();

        while (nextId != null && !nextId.equals(mainId)) {
            Synonym synonym = accessRepo.findOne(nextId);
            wordList.add(synonym.getlabel());
            nextId = synonym.getNextSynonym();
        }
        wordList.add(wordLabel);

        return wordList;
    }

    @Override
    public boolean isWordExist(String wordLabel) {
        Synonym mainWord = accessRepo.findByLabelQuery(wordLabel);
        return (mainWord != null);
    }

}
