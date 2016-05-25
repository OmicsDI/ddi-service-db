package uk.ac.ebi.ddi.service.db.service.publication;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.publication.PublicationDataset;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetRepo;
import uk.ac.ebi.ddi.service.db.repo.publication.IPublicationDatasetRepo;

import java.util.List;


/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
@Component
public class PublicationDatasetService implements IPublicationDatasetService {

    @Autowired
    private IPublicationDatasetRepo datasetAccessRepo;

    @Override
    public PublicationDataset save(PublicationDataset dataset) {
        return datasetAccessRepo.save(dataset);
    }

    @Override
    public PublicationDataset read(ObjectId id) {
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public Page<PublicationDataset> readAll(int pageStart, int size) {
        return datasetAccessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public PublicationDataset update(PublicationDataset dataset) {

        PublicationDataset existingDataset = datasetAccessRepo.findOne(dataset.getId());

        existingDataset.setDatasetID(dataset.getDatasetID());
        existingDataset.setDatabaseID(dataset.getDatabase());

        return datasetAccessRepo.save(existingDataset);
    }

    @Override
    public PublicationDataset delete(ObjectId id) {
        datasetAccessRepo.delete(id);
        return datasetAccessRepo.findOne(id);
    }


    @Override
    public PublicationDataset read(String acc, String database) {
        return datasetAccessRepo.findByAccessionDatabaseQuery(acc, database);
    }

    @Override
    public List<PublicationDataset> findByPubmedId(String pubmedID) {
        return datasetAccessRepo.findByPubmedId(pubmedID);
    }
}
