package uk.ac.ebi.ddi.service.db.model.logger;

import java.math.BigInteger;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 30/04/2015
 */
public interface IDocument {

    /**
     * Every Document should contain an Id
     * @return
     */
    public BigInteger getId();

    /**
     * All the documents should be able to export to String
     * @return
     */
    public String toString();

}
