package uk.ac.ebi.ddi.service.db.service.access;

import uk.ac.ebi.ddi.service.db.model.DatasetAccess;

import java.math.BigInteger;
import java.util.List;

/**
 * THis interface allows the creation of and handling of DatasetAccess in the database.
 * @author ypriverol
 *
 */
public interface IDatasetAccessService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param datasetAccess The new datset access to be create in the database
     * @return the inserted datasetaccess
     */
    public DatasetAccess create(DatasetAccess datasetAccess);

    /**
     * Read a datasetAccess entry from the database
     * @param id of the datasetaccess entry
     * @return A DatasetAccess
     */
    public DatasetAccess read(BigInteger id);

    /**
     * Read all the datasetAccess from the database
     * @return A list of datasetAccess
     */
    public List<DatasetAccess> readAll();

    /**
     * Update a datasetAccess entry in the database using the information of the new datasetAccess
     * @param datasetAccess the new datasetAccess information
     * @return the updated datasetAccess.
     */
    public DatasetAccess update(DatasetAccess datasetAccess);

    /**
     * Remove a DatasetAccess in the Database using the id.
     * @param id Identifier of the datasetAccess to be removed from the database
     * @return the removed datatsetAccess
     */
    public DatasetAccess delete(BigInteger id);

}
