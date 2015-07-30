package uk.ac.ebi.ddi.service.db.model.enrichment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;
/**
 * Created by mingze on 23/07/15.
 * annotated word which has its own synonym list
 *
 * label is also the label of the word
 */
@Document(collection = "enrichment.synonymsForWord")
public class SynonymsForWord extends AbstractDocument {

    private String label;
    private ObjectId nextSynonym;


    public SynonymsForWord(String label) {
        this.label = label;
        this.nextSynonym = this._id;
    }

    public void setNextSynonym(ObjectId id) {
        this.nextSynonym = id;
    }

    public ObjectId getNextSynonym() {
        return nextSynonym;
    }

    public String getlabel() {
        return label;
    }
}
