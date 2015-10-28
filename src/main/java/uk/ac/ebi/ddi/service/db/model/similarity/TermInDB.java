package uk.ac.ebi.ddi.service.db.model.similarity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.ddi.service.db.model.logger.AbstractDocument;

/**
 * Created by mingze on 07/09/15.
 */
@Document(collection = "enrichment.TermInDB")
public class TermInDB extends AbstractDocument {
    private String termName;
    private int datasetFrequency;//the number of documents which concains this term
    private int timeOfAccurrenceInDB;
    private String dataType;

    public TermInDB(String termName, String dataType) {
        this.termName = termName;
        this.timeOfAccurrenceInDB = 1;
        this.datasetFrequency = 1;
        this.dataType = dataType;
    }

    public int getTimeOfAccurrenceInDB() {
        return timeOfAccurrenceInDB;
    }

    public void setTimeOfAccurrenceInDB(int timeOfAccurrenceInDB) {
        this.timeOfAccurrenceInDB = timeOfAccurrenceInDB;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public void increaseTimeOfAccurrenceInDB() {
        this.timeOfAccurrenceInDB++;
    }

    public void increaseDatasetFrequency() {
        this.datasetFrequency++;
    }

    public int getDatasetFrequency() {
        return datasetFrequency;
    }

    public void setDatasetFrequency(int datasetFrequency) {
        this.datasetFrequency = datasetFrequency;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


}
