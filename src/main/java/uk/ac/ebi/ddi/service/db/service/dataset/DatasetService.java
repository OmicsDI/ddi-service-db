package uk.ac.ebi.ddi.service.db.service.dataset;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.aggregate.BaseAggregate;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.publication.PublicationDataset;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetRepo;
import org.springframework.data.mongodb.core.aggregation.*;
import uk.ac.ebi.ddi.service.db.utils.Constants;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;


/**
 * The DatasetAccess reader that implements all the methods to retrieve a dataset, remove it. or find them.
 *
 * @author Yasset Perez-Riverol
 */
@Service
public class DatasetService implements IDatasetService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Resource
    private IDatasetRepo datasetAccessRepo;

    @Override
    public Dataset save(Dataset dataset) {
        return datasetAccessRepo.save(dataset);
    }

    @Override
    public Dataset read(ObjectId id) {
        return datasetAccessRepo.findOne(id);
    }

    @Override
    public Page<Dataset> readAll(int pageStart, int size) {
        return datasetAccessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public Dataset update(ObjectId id, Dataset dataset) {

        Dataset existingDataset = datasetAccessRepo.findOne(id);

        if(existingDataset != null){
            existingDataset.setCrossReferences(dataset.getCrossReferences());
            existingDataset.setDescription(dataset.getDescription());
            existingDataset.setCurrentStatus(dataset.getCurrentStatus());
            existingDataset.setAdditional(dataset.getAdditional());
            existingDataset.setAccession(dataset.getAccession());
            existingDataset.setDatabase(dataset.getDatabase());
            existingDataset.setDates(dataset.getDates());
            existingDataset.setName(dataset.getName());
            existingDataset.setFilePath(dataset.getFilePath());
            existingDataset.setInitHashCode(dataset.getInitHashCode());
            return datasetAccessRepo.save(existingDataset);
        }
        return existingDataset;
    }

    @Override
    public void delete(ObjectId id) {
        datasetAccessRepo.delete(id);
    }

    @Override
    public Dataset read(String acc, String database) {
        return datasetAccessRepo.findByAccessionDatabaseQuery(acc, database);
    }

    @Override
    public List<Dataset> readDatasetHashCode(String database) {
        return datasetAccessRepo.findByDatabase(database);
    }

    @Override
    public Dataset updateCategory(Dataset dataset) {

        Dataset existingDataset = datasetAccessRepo.findOne(dataset.getId());
        existingDataset.setAccession(dataset.getCurrentStatus());
        return datasetAccessRepo.save(existingDataset);
    }

    @Override
    public List<Dataset> findByAccession(String accession) {
        return datasetAccessRepo.findByAccession(accession);
    }

    @Override
    public List<PublicationDataset>  getMutiomicsDatasets()
    {
        MatchOperation checkPubmedNull = Aggregation.match(new Criteria("crossReferences.pubmed").exists(true).
                andOperator(new Criteria("currentStatus").ne("Deleted")));

        UnwindOperation unwindPubMed = Aggregation.unwind("crossReferences.pubmed");

        GroupOperation groupPubmed = Aggregation.group("crossReferences.pubmed").
                addToSet(new BasicDBObject("ac","$accession").append("db","$database")).as("datasets").
                addToSet("additional.omics_type").as("omics_list").count().as("count");

        ProjectionOperation projectStage = Aggregation.project("_id", "count","datasets","omics_list").
                and("omics_list").size().as("omics_count").and("_id").as("pubmedId");

        MatchOperation checkMultiomics = Aggregation.match(new Criteria("omics_count").gte(2));

        UnwindOperation unwindDatasets = Aggregation.unwind("datasets");

        ProjectionOperation projectAsPublication = Aggregation.project("pubmedId")
                .and("datasets.ac").as("accession").
                        and("datasets.db").as("database").andExclude("_id");
        Aggregation aggregation
                = Aggregation.newAggregation(checkPubmedNull, unwindPubMed,groupPubmed,projectStage,checkMultiomics,unwindDatasets,
                projectAsPublication);


        AggregationResults<PublicationDataset> output
                = mongoTemplate.aggregate(aggregation, "datasets.dataset", PublicationDataset.class);

        return output.getMappedResults();
    }

    public <T extends BaseAggregate> List<T> getAggregationResults(Aggregation aggregation, String collectionName, Class<T> outputType)
    {
        AggregationResults<T> output
                = mongoTemplate.aggregate(aggregation, collectionName,outputType);

        return output.getMappedResults();
    }

    public List<Dataset> getSimilarByPubmed(String pubmedId){
        return datasetAccessRepo.findByCrossReferencesPubmed(pubmedId);
    }

    public void updateDatasetClaim(String[] databases){
        Query searchQuery = new Query();
        searchQuery.addCriteria(new Criteria(Constants.DATABASE_FIELD).in(databases));

        Update updateClaim = new Update();
        updateClaim.set(Constants.ISCLAIMED_FIELD,true);

        mongoTemplate.updateMulti(searchQuery,updateClaim,"datasets.dataset");
    }
}
