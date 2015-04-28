package uk.ac.ebi.ddi.service.db.model;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


import javax.annotation.Generated;

/**
 * Information about the access of each dataset, Date of the access, the idea is to have a simple statistic about how many times
 * an specific dataset has been accessed.
 *
 * @author Yasset Perez-Riverol
 */

@Document(collection = "DatasetAccess")

public class DatasetAccess extends AbstractDocument implements Serializable{

    private static final long serialVersionUID = 1326887243102331826L;

    private String accession;

    private String database;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date accessDate;

    /**
     * Default constructor
     */
    public DatasetAccess(){
    }

    /**
     * Constructor only with the ID
     *
     * @param id
     */
    public DatasetAccess(BigInteger id) {
        super(id);
    }

    /**
     * This Constructor generate an Id for the entity using the MongoDB driver
     * @param accession The access of the experiment in the repository
     * @param database  The id of the repository
     * @param accessDate The date of the access
     */
    public DatasetAccess(Integer id, String accession, String database, Date accessDate) {
        super(BigInteger.valueOf(id));
        this.accession = accession;
        this.database = database;
        this.accessDate = accessDate;
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

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    @Override
    public String toString() {
        return "DatasetAccess{" +
                "accession='" + accession + '\'' +
                ", database='" + database + '\'' +
                ", accessDate=" + accessDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatasetAccess that = (DatasetAccess) o;

        if (accessDate != null ? !accessDate.equals(that.accessDate) : that.accessDate != null) return false;
        if (!accession.equals(that.accession)) return false;
        if (!database.equals(that.database)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession.hashCode();
        result = 31 * result + database.hashCode();
        result = 31 * result + (accessDate != null ? accessDate.hashCode() : 0);
        return result;
    }
}
