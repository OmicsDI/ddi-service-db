package uk.ac.ebi.ddi.service.db.service.dataset;


import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;

import java.util.List;

/**
 * THis interface allows the creation of and handling of DatasetAccess in the database.
 * @author ypriverol
 *
 */
public interface IDatasetService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param datasetResource The new datset access to be save in the database
     * @return the inserted datasetaccess
     */
    public Dataset save(Dataset datasetResource);

    /**
     * Read a datasetAccess entry from the database
     * @param id of the datasetaccess entry
     * @return A DatasetAccess
     */
    public Dataset read(ObjectId id);

    /**
     * Read all the datasetAccess from the database
     * @return A list of datasetAccess
     */
    public Page<Dataset> readAll(int pageStart, int size);

    /**
     * Update a datasetAccess entry in the database using the information of the new datasetAccess
     * @param datasetResource the new datasetAccess information
     * @return the updated datasetAccess.
     */
    public Dataset update(ObjectId id, Dataset datasetResource);

    /**
     * Remove a DatasetAccess in the Database using the id.
     * @param id Identifier of the datasetAccess to be removed from the database
     * @return the removed datatsetAccess
     */
    public Dataset delete(ObjectId id);

    /**
     * Read a DatasetAccess from the database using the accession and the database
     * @param acc Accession of the DatasetAccess
     * @param database  Database of the DatasetAceess
     * @return Return the entry for the dataset
     */
    public Dataset read(String acc, String database);

    public List<Dataset> readDatasetHashCode(String database);

    public Dataset updateCategory(Dataset dataset);


}
