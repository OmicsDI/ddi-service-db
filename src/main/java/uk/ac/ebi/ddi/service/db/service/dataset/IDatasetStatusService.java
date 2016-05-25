package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetStatus;

import java.util.List;

/**
 * Created by yperez on 24/05/2016.
 */
public interface IDatasetStatusService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param datasetResource The new datset access to be save in the database
     * @return the inserted datasetaccess
     */
    public DatasetStatus save(DatasetStatus datasetResource);

    /**
     * Read a datasetAccess entry from the database
     * @param id of the datasetaccess entry
     * @return A DatasetAccess
     */
    public DatasetStatus read(ObjectId id);

    /**
     * Read all the datasetAccess from the database
     * @return A list of datasetAccess
     */
    public Page<DatasetStatus> readAll(int pageStart, int size);

    /**
     * Update a datasetAccess entry in the database using the information of the new datasetAccess
     * @param datasetStatus the new datasetAccess information
     * @return the updated datasetAccess.
     */
    public DatasetStatus update(DatasetStatus datasetStatus);

    /**
     * Remove a DatasetAccess in the Database using the id.
     * @param id Identifier of the datasetAccess to be removed from the database
     * @return the removed datatsetAccess
     */
    public DatasetStatus delete(ObjectId id);

    /**
     * Read a DatasetAccess from the database using the accession and the database
     * @param acc Accession of the DatasetAccess
     * @param database  Database of the DatasetAceess
     * @return Return the entry for the dataset
     */
    public DatasetStatus read(String acc, String database);

    public List<DatasetStatus> readDatasetHashCode(String database);

    public DatasetStatus updateCategory(DatasetStatus dataset);
}
