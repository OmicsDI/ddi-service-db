package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetSimilars;

import java.util.List;

/**
 * Created by yperez on 13/06/2016.
 */
public interface IDatasetSimilarsRepo extends MongoRepository<DatasetSimilars,ObjectId> {

    @Query("{'$and':[{accession : ?0}, {database : ?1}]}")
    public DatasetSimilars findByAccessionDatabaseQuery(String acc, String database);

    @Query(value="{ database : ?0 }", fields ="{database : 1, accession : 1, hashCode: 1, currentStatus: 1}")
    public List<DatasetSimilars> findByDatabase(String name);

    @Query("{_id: ?0}")
    public DatasetSimilars findByIdDatabaseQuery(ObjectId _id);

    @Query("{accession: ?0}")
    public List<DatasetSimilars> findByAccession(String accession);
}
