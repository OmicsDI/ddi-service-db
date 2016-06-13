package uk.ac.ebi.ddi.service.db.model.dataset;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by yperez on 13/06/2016.
 */
@Document(collection = "datasets.similars")
@CompoundIndexes({
        @CompoundIndex(name = "accession_database", def = "{'accession' : 1, 'database': 1}", unique = true)
})
public class DatasetSimilars implements Serializable{

    @Indexed
    private String accession;
    @Indexed
    private String database;

    private Map<String, Set<String>> similars;

    public DatasetSimilars(String accession, String database) {
        this.accession = accession;
        this.database = database;
    }

    public DatasetSimilars(String accession, String database, Map<String, Set<String>> similars) {
        this(accession, database);
        this.similars = similars;
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

    public Map<String, Set<String>> getSimilars() {
        return similars;
    }

    public void setSimilars(Map<String, Set<String>> similars) {
        this.similars = similars;
    }
}
