package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetFile;

import java.util.List;

/**
 * Created by yperez on 13/06/2016.
 */
@Repository
public interface IDatasetFilesRepo extends MongoRepository<DatasetFile, ObjectId> {

    @Query(fields="{ 'fileUrl' : 1}")
    List<DatasetFile> findByAccessionAndDatabase(String accession, String database);

    @Query(fields="{ 'fileUrl' : 1}")
    List<DatasetFile> findByAccessionAndDatabaseAndFrom(String accession, String database, String from);

    Long deleteByAccessionAndDatabase(String accession, String database);
    Long deleteByAccessionAndDatabaseAndFrom(String accession, String database, String from);
}
