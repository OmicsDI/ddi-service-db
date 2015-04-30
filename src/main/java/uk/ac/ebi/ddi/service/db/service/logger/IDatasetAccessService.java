package uk.ac.ebi.ddi.service.db.service.logger;

import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.DatasetAccess;

import java.math.BigInteger;

/**
 * THis interface allows the creation of and handling of DatasetAccess in the database.
 * @author ypriverol
 *
 */
public interface IDatasetAccessService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param datasetAccess The new datset access to be save in the database
     * @return the inserted datasetaccess
     */
    public DatasetAccess save(DatasetAccess datasetAccess);

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
    public Page<DatasetAccess> readAll(int pageStart, int size);

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

    /**
     * This function add a new Access to the DatasetAccess
     * @param acc Dataset Accession
     * @param database Domain Accession
     * @return DatasetAccess updated with the new access
     */
    public DatasetAccess addAccess(String acc, String database, HttpEvent httpEvent);

    /**
     * Read a DatasetAccess from the database using the accession and the database
     * @param acc Accession of the DatasetAccess
     * @param database  Database of the DatasetAceess
     * @return Return the entry for the dataset
     */
    public DatasetAccess read(String acc, String database);


}
