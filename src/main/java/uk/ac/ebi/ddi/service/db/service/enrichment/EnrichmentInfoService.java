package uk.ac.ebi.ddi.service.db.service.enrichment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.exception.DBWriteException;
import uk.ac.ebi.ddi.service.db.model.enrichment.DatasetEnrichmentInfo;
import uk.ac.ebi.ddi.service.db.repo.enrichment.IEnrichmentInfoRepo;

/**
 * Created by mingze on 30/07/15.
 */

@Component
public class EnrichmentInfoService implements IEnrichmentInfoService {


    @Autowired
    private IEnrichmentInfoRepo accessRepo;

    @Override
    public DatasetEnrichmentInfo insert(DatasetEnrichmentInfo datasetEnrichmentInfo) {

        DatasetEnrichmentInfo oldDatasetEnrichmentInfo = accessRepo.findByAccessionQuery(datasetEnrichmentInfo.getAccession(),"new");
        if (oldDatasetEnrichmentInfo!=null){
            oldDatasetEnrichmentInfo.setStatus("old");
            accessRepo.save(oldDatasetEnrichmentInfo);
        }
        datasetEnrichmentInfo.setStatus("new");
        DatasetEnrichmentInfo insertedDataset = accessRepo.insert(datasetEnrichmentInfo);
        if ((insertedDataset.getId() == null))
            throw new DBWriteException("Inserting fail, no _id assigned");
        return insertedDataset;
    }


    @Override
    public DatasetEnrichmentInfo read(ObjectId id) {
        return accessRepo.findOne(id);
    }

    @Override
    public Page<DatasetEnrichmentInfo> readAll(int pageStart, int size) {
        return accessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public DatasetEnrichmentInfo update(DatasetEnrichmentInfo datasetEnrichmentInfo) {
        return accessRepo.save(datasetEnrichmentInfo);
    }

    @Override
    public DatasetEnrichmentInfo delete(ObjectId id) {
        accessRepo.delete(id);
        return accessRepo.findOne(id);
    }

    @Override
    public DatasetEnrichmentInfo readByaccession(String accession) {
        if ((accession == null))
            throw new DBWriteException(" The accession to the original resource should contain a string");

        return accessRepo.findByAccessionQuery(accession,"new");

    }

    @Override
    public boolean isDatasetExist(String accession) {
        DatasetEnrichmentInfo dataset = accessRepo.findByAccessionQuery(accession,"new");
        return (dataset != null);
    }

}
