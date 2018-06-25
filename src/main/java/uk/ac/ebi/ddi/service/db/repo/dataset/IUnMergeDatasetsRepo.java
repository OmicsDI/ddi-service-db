package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.model.dataset.UnMergeDatasets;

@Repository
public interface IUnMergeDatasetsRepo extends MongoRepository<UnMergeDatasets,ObjectId> {


}
