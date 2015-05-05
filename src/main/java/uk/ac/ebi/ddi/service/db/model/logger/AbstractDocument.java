package uk.ac.ebi.ddi.service.db.model.logger;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

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

    @CreatedDate
    private Date cd;

    @LastModifiedDate
    private Date lmd;

    @CreatedBy
    private String cb;

    @LastModifiedBy
    private String lmb;

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

    public Date getCd() {
        return cd;
    }

    public void setCd(Date cd) {
        this.cd = cd;
    }

    public Date getLmd() {
        return lmd;
    }

    public void setLmd(Date lmd) {
        this.lmd = lmd;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getLmb() {
        return lmb;
    }

    public void setLmb(String lmb) {
        this.lmb = lmb;
    }

    @Override
    public String toString() {
        return "AbstractDocument{" +
                "id=" + _id +
                '}';
    }
}
