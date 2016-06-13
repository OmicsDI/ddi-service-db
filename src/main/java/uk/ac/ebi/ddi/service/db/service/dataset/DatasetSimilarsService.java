package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetSimilars;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetSimilarsRepo;

import java.util.List;

/**
 * Created by yperez on 13/06/2016.
 */
public class DatasetSimilarsService implements IDatasetSimilarsService{

    @Autowired
    private IDatasetSimilarsRepo datasetRepo;

    @Override
    public DatasetSimilars save(DatasetSimilars dataset) {
        return datasetRepo.save(dataset);
    }

    @Override
    public DatasetSimilars read(ObjectId id) {
        return datasetRepo.findOne(id);
    }

    @Override
    public Page<DatasetSimilars> readAll(int pageStart, int size) {
        return datasetRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public DatasetSimilars update(ObjectId id, DatasetSimilars dataset) {
        DatasetSimilars existingDataset = datasetRepo.findOne(id);

        if(existingDataset != null){
            existingDataset.setSimilars(dataset.getSimilars());
        }
        return existingDataset;
    }

    @Override
    public void delete(ObjectId id) {
        datasetRepo.delete(id);
    }

    @Override
    public DatasetSimilars read(String acc, String database) {
        return datasetRepo.findByAccessionDatabaseQuery(acc, database);
    }

    @Override
    public List<DatasetSimilars> findByAccession(String accession) {
        return datasetRepo.findByAccession(accession);
    }
}
