package uk.ac.ebi.ddi.service.db.model.enrichment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;

import java.io.Serializable;

/**
 * Created by mingze on 23/07/15.
 * annotated word which has its own synonym list
 *
 * label is also the label of the word
 */
@Document(collection = "enrichment.synonyms")
public class Synonym extends AbstractDocument implements Serializable {

    private static final long serialVersionUID = 1326887243102331825L;

    private String label;
    private ObjectId nextSynonym;


    public Synonym(String label) {
        this.label = label;
        this.nextSynonym = null;
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

    @Override
    public String toString() {
        return "Synonyms{" +
                "id='" + _id + '\'' +
                "label='" + label + '\'' +
                ", nextSynonym=" + nextSynonym +
                '}';
    }
}
