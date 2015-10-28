package uk.ac.ebi.ddi.service.db.model.similarity;

import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class control an access to the System using the same headers from a HttpRequest in the web-service
 * The current file control the IP, region, request size, user and date.
 * @author Yasset Perez-Riverol
 */
@Document(collection = "enrichment.expOutputDataset")
public class ExpOutputDataset extends AbstractDocument implements Serializable{

    private static final long serialVersionUID = 1326887243102331825L;

    private String accession;
    private String database;
    private String expDataType;
    private List<TermInList> terms;

    public ExpOutputDataset(String accession, String database, String expDataType, List<TermInList> terms) {
        this.accession = accession;
        this.database = database;
        this.expDataType = expDataType;
        this.terms = terms;
    }

    public List<TermInList> getTerms() {
        return terms;
    }

    public void setTerms(List<TermInList> terms) {
        this.terms = terms;
    }

    public String getExpDataType() {
        return expDataType;
    }

    public void setExpDataType(String expDataType) {
        this.expDataType = expDataType;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
