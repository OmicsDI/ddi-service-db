package uk.ac.ebi.ddi.service.db.model.enrichment;

import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * This class control an access to the System using the same headers from a HttpRequest in the web-service
 * The current file control the IP, region, request size, user and date.
 * @author Yasset Perez-Riverol
 */
@Document(collection = "enrichment.enrichedDataset")
public class DatasetEnrichmentInfo extends AbstractDocument implements Serializable{

    private static final long serialVersionUID = 1326887243102331825L;

    private String accession;
    private String database;
    private Date enrichTime;
    private String status;



    private List<WordInField> title;
    private List<WordInField> abstractDescription;
    private List<WordInField> sampleProtocol;
    private List<WordInField> dataProtocol;

    public DatasetEnrichmentInfo(String accession, String database) {
        this.accession= accession;
        this.database= database;
    }


    public void setTitle(List<WordInField> title) {
        this.title = title;
    }

    public void setAbstractDescription( List<WordInField> abstractDescription) {
        this.abstractDescription = abstractDescription;
    }

    public void setSampleProtocol( List<WordInField> sampleProtocol) {
        this.sampleProtocol = sampleProtocol;
    }

    public void setDataProtocol(List<WordInField> dataProtocol) {
        this.dataProtocol = dataProtocol;
    }
    public void setEnrichTime(Date enrichTime) {
        this.enrichTime = enrichTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccession() {
        return accession;
    }

    public Date getEnrichTime() {
        return enrichTime;
    }

    public String getStatus() {
        return status;
    }

    public List<WordInField> getTitle() {
        return title;
    }


    public List<WordInField> getAbstractDescription() {
        return abstractDescription;
    }


    public List<WordInField> getSampleProtocol() {
        return sampleProtocol;
    }


    public List<WordInField> getDataProtocol() {
        return dataProtocol;
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

    @Override
    public String toString() {
        return "DatasetEnrichmentInfo{" +
                "accession='" + accession + '\'' +
                "database='" + database + '\'' +
                ", enrichTime=" + enrichTime +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", abstractDescription='" + abstractDescription + '\'' +
                ", sampleProtocol='" + sampleProtocol + '\'' +
                ", dataProtocol='" + dataProtocol + '\'' +
                '}';
    }
}
