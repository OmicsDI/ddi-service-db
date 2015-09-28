package uk.ac.ebi.ddi.service.db.service.enrichment;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.enrichment.EnrichedDataset;

import java.util.ArrayList;

/**
 * Created by mingze on 30/07/15.
 */
public interface IEnrichmentService {

    /**
     * Create a new EnrichedDataset in the MongoDB
     *
     * @param enrichedDataset the Enrichied Dataset to be inserted in the database
     * @return the inserted dataset
     */
    EnrichedDataset insert(EnrichedDataset enrichedDataset);

    /**
     * read a dataset from database by id
     *
     * @param id of the dataset entry in database
     * @return a dataset
     */
    EnrichedDataset read(ObjectId id);

    /**
     * Read all the enriched datasets from the database
     *
     * @return A list of datasets
     */
    Page<EnrichedDataset> readAll(int pageStart, int size);

    /**
     * Update a dataset entry in the database using the new information
     *
     * @param enrichedDataset the new datasetAccess information
     * @return the updated datasetAccess.
     */
    EnrichedDataset update(EnrichedDataset enrichedDataset);

    /**
     * Remove a DatasetAccess in the Database using the id.
     *
     * @param id Identifier of the dataset to be removed from the database
     * @return the removed dataset
     */
    EnrichedDataset delete(ObjectId id);

    EnrichedDataset readByaccession(String accession);

    boolean isDatasetExist(String wordLabel);

}
