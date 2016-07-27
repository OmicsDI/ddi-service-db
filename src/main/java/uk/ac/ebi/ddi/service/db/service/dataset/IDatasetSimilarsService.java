package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetSimilars;

import java.util.List;

/**
 * Created by yperez on 13/06/2016.
 */
public interface IDatasetSimilarsService {

    /**
     * Create a new DatasetAccess in the MongoDB
     * @param dataset The new datset access to be save in the database
     * @return the inserted dataset
     */
    public DatasetSimilars save(DatasetSimilars dataset);

    /**
     * Read a datasetAccess entry from the database
     * @param id of the datasetaccess entry
     * @return A DatasetAccess
     */
    public DatasetSimilars read(ObjectId id);

    /**
     * Read all the datasetAccess from the database
     * @return A list of datasetAccess
     */
    public Page<DatasetSimilars> readAll(int pageStart, int size);

    /**
     * Update a datasetAccess entry in the database using the information of the new datasetAccess
     * @param dataset the new datasetSimilars information
     * @return the updated datasetAccess.
     */
    public DatasetSimilars update(ObjectId id, DatasetSimilars dataset);

    /**
     * Remove a Dataset in the Database using the id.
     * @param id Identifier of the datasetAccess to be removed from the database
     * @return the removed datatsetAccess
     */
    public void delete(DatasetSimilars dataset);

    /**
     * Read a DatasetAccess from the database using the accession and the database
     * @param acc Accession of the DatasetAccess
     * @param database  Database of the DatasetAceess
     * @return Return the entry for the dataset
     */
    public DatasetSimilars read(String acc, String database);

    /**
     * Find all datasets by an specific accession
     * @param accession
     * @return
     */
    public List<DatasetSimilars> findByAccession(String accession);

    public List<DatasetSimilars> readAll();
}
