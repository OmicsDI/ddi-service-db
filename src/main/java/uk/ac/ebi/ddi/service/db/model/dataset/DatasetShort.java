package uk.ac.ebi.ddi.service.db.model.dataset;

/**
 * Created by azorin on 09/02/2018.
 */
public class DatasetShort {
    String database;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String accession;
    String name;
}
