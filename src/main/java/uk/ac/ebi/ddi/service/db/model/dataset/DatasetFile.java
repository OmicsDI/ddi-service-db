package uk.ac.ebi.ddi.service.db.model.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "datasets.files")
@CompoundIndexes({
        @CompoundIndex(name = "accession_database", def = "{'accession' : 1, 'database': 1}"),
        @CompoundIndex(name = "accession_database_from", def = "{'accession' : 1, 'database': 1, 'from': 1}"),
})
public class DatasetFile {

    @Indexed
    private String accession;
    @Indexed
    private String database;

    @Id
    private ObjectId _id;

    private String fileUrl;

    @Indexed
    private String from;

    public DatasetFile(String accession, String database, String fileUrl, String from) {
        this.accession = accession;
        this.database = database;
        this.fileUrl = fileUrl;
        this.from = from;
    }

    public DatasetFile() {
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

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatasetFile that = (DatasetFile) o;
        return Objects.equals(accession, that.accession) &&
                Objects.equals(database, that.database) &&
                Objects.equals(_id, that._id) &&
                Objects.equals(fileUrl, that.fileUrl) &&
                Objects.equals(from, that.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accession, database, _id, fileUrl, from);
    }
}
