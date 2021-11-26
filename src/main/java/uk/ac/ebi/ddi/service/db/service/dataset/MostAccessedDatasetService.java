package uk.ac.ebi.ddi.service.db.service.dataset;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.aggregate.BaseAggregate;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.MostAccessedDatasets;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetRepo;
import uk.ac.ebi.ddi.service.db.repo.dataset.IMostAccessedRepo;
import uk.ac.ebi.ddi.service.db.utils.Constants;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaur on 25/06/17.
 */
@Service
public class MostAccessedDatasetService implements IMostAccessedDatasetService{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private IMostAccessedRepo mostAccessedRepo;

    @Override
    public MostAccessedDatasets save(MostAccessedDatasets dataset) {
        return mostAccessedRepo.save(dataset);
    }

    @Override
    public Page<MostAccessedDatasets> readAll(int pageStart, int size) {
        return mostAccessedRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public void deleteAll() {
        mostAccessedRepo.deleteAll();
    }

    @Override
    public MostAccessedDatasets getDatasetView(String acc, String database){
        return mostAccessedRepo.getByAccessionAndDatabase(acc, database);
    }

    @Override
    public List<MostAccessedDatasets> getMostAccessedByDatabase(List<String> dbList){
        return mostAccessedRepo.getByDatabaseIn(dbList);
    }
}
