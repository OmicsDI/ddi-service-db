package uk.ac.ebi.ddi.service.db.model;

import java.io.Serializable;
import java.math.BigInteger;
import org.springframework.data.annotation.Id;

/**
 * @author Yasset Perez-Riverol
 */
public class AbstractDocument implements Serializable{

    private static final long serialVersionUID = 1326887243102331826L;

    @Id
    protected BigInteger id;

    /**
     * Default Constructor
     */
    public AbstractDocument(){

    }

    /**
     * Default Constructor
     * @param id
     */
    public AbstractDocument(BigInteger id) {
        this.id = id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AbstractDocument{" +
                "id=" + id +
                '}';
    }
}
