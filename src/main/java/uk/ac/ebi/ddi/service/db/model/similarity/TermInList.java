package uk.ac.ebi.ddi.service.db.model.similarity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by mingze on 11/09/15.
 */
public class TermInList implements Comparable<TermInList>{
    private ObjectId idInDB;
    private String termName;

    public TermInList(String termName) {
        this.termName = termName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public ObjectId getIdInDB() {
        return idInDB;
    }

    public void setIdInDB(ObjectId indexInDB) {
        this.idInDB = indexInDB;
    }

    public int compareTo(TermInList anotherTerm) {
        return this.termName.compareTo(anotherTerm.getTermName());
    }


}
