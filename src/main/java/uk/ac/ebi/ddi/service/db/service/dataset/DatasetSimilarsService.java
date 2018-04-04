package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetShort;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetSimilars;
import uk.ac.ebi.ddi.service.db.model.dataset.SimilarDataset;
import uk.ac.ebi.ddi.service.db.model.publication.PublicationDataset;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetSimilarsRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yperez on 13/06/2016.
 */
@Service
public class DatasetSimilarsService implements IDatasetSimilarsService{

    @Autowired
    private IDatasetSimilarsRepo datasetRepo;

    @Override
    public DatasetSimilars save(DatasetSimilars dataset) {
        return datasetRepo.save(dataset);
    }

    @Autowired
    private IDatasetService datasetService;
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
    public void delete(DatasetSimilars dataset) {
        DatasetSimilars datasetExisting = datasetRepo.findByAccessionDatabaseQuery(dataset.getAccession(), dataset.getDatabase());
        if(datasetExisting != null){
            datasetRepo.delete(datasetExisting);
        }
    }

    @Override
    public DatasetSimilars read(String acc, String database) {
        return datasetRepo.findByAccessionDatabaseQuery(acc, database);
    }

    /****** AZ: removed, why we need to query by accession, w/o database?
    @Override
    public List<DatasetSimilars> findByAccession(String accession) {
        return datasetRepo.findByAccession(accession);
    }
    *******/

    @Override
    public List<DatasetSimilars> readAll(){
        return datasetRepo.findAll();
    }

    public void addDatasetSimilars(Dataset dataset, List<DatasetShort> similars, String relationtype){
        DatasetSimilars datasetExisting = datasetRepo.findByAccessionDatabaseQuery(dataset.getAccession(), dataset.getDatabase());
        Set<SimilarDataset> similarDatasets = new HashSet<>();
        for(DatasetShort publicationDataset: similars){
            if(!publicationDataset.getAccession().equalsIgnoreCase(dataset.getAccession()) && !publicationDataset.getDatabase().equalsIgnoreCase(dataset.getDatabase())){
                Dataset datasetRelated = datasetService.read(dataset.getAccession(), dataset.getDatabase());
                if(datasetRelated != null){
                    SimilarDataset similar = new SimilarDataset(datasetRelated, relationtype);
                    similarDatasets.add(similar);
                }
            }
        }
        if(datasetExisting == null){
            datasetExisting = new DatasetSimilars(dataset.getAccession(), dataset.getDatabase(), similarDatasets);
            datasetRepo.save(datasetExisting);
        }else{
            Set<SimilarDataset> similarsData = datasetExisting.getSimilars();
            similarDatasets.addAll(similarsData);
            datasetExisting.setSimilars(similarDatasets);
            datasetRepo.save(datasetExisting);
        }
    }
}
