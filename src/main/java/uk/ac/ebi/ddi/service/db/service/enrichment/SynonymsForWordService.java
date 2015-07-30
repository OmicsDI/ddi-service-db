package uk.ac.ebi.ddi.service.db.service.enrichment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.exception.DBWriteException;
import uk.ac.ebi.ddi.service.db.model.enrichment.SynonymsForWord;
import uk.ac.ebi.ddi.service.db.repo.enrichment.ISynonymsForWordRepo;

/**
 * Created by mingze on 30/07/15.
 */

@Component
public class SynonymsForWordService implements ISynonymsForWordService {


    @Autowired
    private ISynonymsForWordRepo accessRepo;

    @Override
    public SynonymsForWord insert(SynonymsForWord synonymsForWord) {
        if((synonymsForWord.getId() == null))
            new DBWriteException(" The reference to the original resource should contain an Id");
        return accessRepo.insert(synonymsForWord);
    }

    @Override
    public SynonymsForWord read(ObjectId id) {
        return accessRepo.findOne(id);
    }

    @Override
    public Page<SynonymsForWord> readAll(int pageStart, int size) {
        return accessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public SynonymsForWord update(SynonymsForWord synonymsForWord) {
        SynonymsForWord existingSynonymsForWord = accessRepo.findOne(synonymsForWord.getId());
        existingSynonymsForWord.setNextSynonym(synonymsForWord.getNextSynonym());

        return accessRepo.save(existingSynonymsForWord);
    }

    @Override
    public SynonymsForWord delete(ObjectId id) {
        accessRepo.delete(id);
        return accessRepo.findOne(id);
    }
}
