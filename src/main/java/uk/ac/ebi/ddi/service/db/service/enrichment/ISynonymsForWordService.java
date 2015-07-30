package uk.ac.ebi.ddi.service.db.service.enrichment;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.enrichment.SynonymsForWord;

import java.util.Objects;

/**
 * Created by mingze on 30/07/15.
 */
public interface ISynonymsForWordService {

    /**
     * Create a new word in the MongoDB
     * @param synonymsForWord the word to be inserted in the database
     * @return the inserted word
     */
    public SynonymsForWord insert(SynonymsForWord synonymsForWord);

    /**
     * read a word from database
     * @param id of the word entry in database
     * @return a word
     */
    public SynonymsForWord read(ObjectId id);

    /**
     * Read all the words from the database
     * @return A list of words
     */
    public Page<SynonymsForWord> readAll(int pageStart, int size);

     /**
     * Update a word entry in the database using the new information
     * @param synonymsForWord the new datasetAccess information
     * @return the updated datasetAccess.
     */
    public SynonymsForWord update(SynonymsForWord synonymsForWord);

    /**
     * Remove a DatasetAccess in the Database using the id.
     * @param id Identifier of the word to be removed from the database
     * @return the removed word
     */
    public SynonymsForWord delete(ObjectId id);
}
