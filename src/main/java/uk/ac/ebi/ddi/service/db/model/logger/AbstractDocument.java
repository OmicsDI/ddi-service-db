package uk.ac.ebi.ddi.service.db.model.logger;

import java.io.Serializable;
import java.math.BigInteger;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * This class allow to define a common identifier for all the objects in the
 * database for Resource, Event, HttpEvent, etc
 * The present class is not persistent in the database, it is only a business class.
 *  - Define the BigInteger Id for all the documents
 *
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 *
 */

public abstract class AbstractDocument implements Serializable, IDocument{

    private static final long serialVersionUID = 1326887243102331826L;

    @Id
    protected ObjectId _id;

    String category;

    /**
     * Default Constructor
     */
    public AbstractDocument(){
    }

    public ObjectId getId() {
        return _id;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AbstractDocument{" +
                "id=" + _id +
                '}';
    }
}
