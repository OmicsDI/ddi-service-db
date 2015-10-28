package uk.ac.ebi.ddi.service.db.model.similarity;

import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mingze on 25/09/15.
 */
@Document(collection = "enrichment.DatasetStatInfo")
public class DatasetStatInfo extends AbstractDocument implements Serializable {

    private String accession;
    private String database;
    private String expDataType;
    private List<IntersectionInfo> intersectionInfos;

    public DatasetStatInfo(String accession, String database, String expDataType, List<IntersectionInfo> intersectionInfos) {
        this.accession = accession;
        this.database = database;
        this.expDataType = expDataType;
        this.intersectionInfos = intersectionInfos;
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

    public String getExpDataType() {
        return expDataType;
    }

    public void setExpDataType(String expDataType) {
        this.expDataType = expDataType;
    }

    public List<IntersectionInfo> getIntersectionInfos() {
        return intersectionInfos;
    }

    public void setIntersectionInfos(List<IntersectionInfo> intersectionInfos) {
        this.intersectionInfos = intersectionInfos;
    }


}
