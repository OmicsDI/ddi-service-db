package uk.ac.ebi.ddi.service.db.service.logger;

import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;

import java.math.BigInteger;

/**
 * @quthor Yasset Perez-Riverol (ypriverol@gmail.com)
 */
public interface IHttpEventService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param httpEvent The new datset access to be save in the database
     * @return the inserted datasetaccess
     */
    public HttpEvent save(HttpEvent httpEvent);

    /**
     * Read a datasetAccess entry from the database
     * @param id of the datasetaccess entry
     * @return A DatasetAccess
     */
    public HttpEvent read(BigInteger id);

    /**
     * Read all the datasetAccess from the database
     * @return A list of datasetAccess
     */
    public Page<HttpEvent> readAll(int pageStart, int size);

    /**
     * Update a datasetAccess entry in the database using the information of the new datasetAccess
     * @param httpEvent the new datasetAccess information
     * @return the updated datasetAccess.
     */
    public HttpEvent update(HttpEvent httpEvent);

    /**
     * Remove a DatasetAccess in the Database using the id.
     * @param id Identifier of the datasetAccess to be removed from the database
     * @return the removed datatsetAccess
     */
    public HttpEvent delete(BigInteger id);
}
