package uk.ac.ebi.ddi.service.db.model.enrichment;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;
import uk.ac.ebi.ddi.service.db.utils.CategoryType;

import java.io.Serializable;
import java.util.Date;


/**
 * This class control an access to the System using the same headers from a HttpRequest in the web-service
 * The current file control the IP, region, request size, user and date.
 * @author Yasset Perez-Riverol
 */
@Document(collection = "enrichment.enrichedDataset")
public class EnrichedDataset extends AbstractDocument implements Serializable{

    private static final long serialVersionUID = 1326887243102331825L;

    private String accession;
    private String database;
    private Date enrichingTime;
    private String status;



    private String title;
    private String enrichedTitle;
    private String abstractDescription;
    private String enrichedAbstractDescription;
    private String sampleProtocol;
    private String enrichedSampleProtocol;
    private String dataProtocol;
    private String enrichedDataProtocol;

    public EnrichedDataset(String accession, String database) {
        this.accession= accession;
        this.database= database;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstractDescription(String abstractDescription) {
        this.abstractDescription = abstractDescription;
    }

    public void setSampleProtocol(String sampleProtocol) {
        this.sampleProtocol = sampleProtocol;
    }

    public void setDataProtocol(String dataProtocol) {
        this.dataProtocol = dataProtocol;
    }
    public void setEnrichingTime(Date enrichingTime) {
        this.enrichingTime = enrichingTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEnrichedTitle(String enrichedTitle) {
        this.enrichedTitle = enrichedTitle;
    }

    public void setEnrichedAbstractDescription(String enrichedAbstractDescription) {
        this.enrichedAbstractDescription = enrichedAbstractDescription;
    }

    public void setEnrichedSampleProtocol(String enrichedSampleProtocol) {
        this.enrichedSampleProtocol = enrichedSampleProtocol;
    }

    public void setEnrichedDataProtocol(String enrichedDataProtocol) {
        this.enrichedDataProtocol = enrichedDataProtocol;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccession() {
        return accession;
    }

    public Date getEnrichingTime() {
        return enrichingTime;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getEnrichedTitle() {
        return enrichedTitle;
    }

    public String getAbstractDescription() {
        return abstractDescription;
    }

    public String getEnrichedAbstractDescription() {
        return enrichedAbstractDescription;
    }

    public String getSampleProtocol() {
        return sampleProtocol;
    }

    public String getEnrichedSampleProtocol() {
        return enrichedSampleProtocol;
    }

    public String getDataProtocol() {
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
        return "EnrichedDataset{" +
                "accession='" + accession + '\'' +
                "database='" + database + '\'' +
                ", enrichingTime=" + enrichingTime +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", enrichedTitle='" + enrichedTitle + '\'' +
                ", abstractDescription='" + abstractDescription + '\'' +
                ", enrichedAbstractDescription='" + enrichedAbstractDescription + '\'' +
                ", sampleProtocol='" + sampleProtocol + '\'' +
                ", enrichedSampleProtocol='" + enrichedSampleProtocol + '\'' +
                ", dataProtocol='" + dataProtocol + '\'' +
                ", enrichedDataProtocol='" + enrichedDataProtocol + '\'' +
                '}';
    }

    public String getEnrichedDataProtocol() {
        return enrichedDataProtocol;
    }
}
