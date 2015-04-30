package uk.ac.ebi.ddi.service.db.model.logger;


import java.io.Serializable;
import java.util.List;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.utils.CascadeSave;

/**
 * Information about the access of each dataset, Date of the access, the idea is to have a simple statistic about how many times
 * an specific dataset has been accessed.
 *
 * @author Yasset Perez-Riverol
 */

@Document(collection = "logger.DatasetAccess")

public class DatasetAccess extends Resource implements Serializable{

    private static final long serialVersionUID = 1326887243102331826L;

    @Indexed
    private String accession;

    @Indexed
    private String database;

    /**
     * Default constructor
     */
    public DatasetAccess(){
    }

    /**
     * This Constructor generate an Id for the entity using the MongoDB driver
     * @param accession The access of the experiment in the repository
     * @param database  The id of the repository
     */
    public DatasetAccess(String accession, String database) {
        this.accession = accession;
        this.database = database;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
