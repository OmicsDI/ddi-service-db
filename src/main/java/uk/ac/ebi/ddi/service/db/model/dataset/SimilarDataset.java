package uk.ac.ebi.ddi.service.db.model.dataset;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 *
 * Created by yperez on 20/06/2016.
 */
public class SimilarDataset {

    @DBRef
    Dataset similarDataset;

    String relationType;

    public SimilarDataset() {
    }

    public SimilarDataset(Dataset similarDataset, String relationType) {
        this.similarDataset = similarDataset;
        this.relationType = relationType;
    }

    public Dataset getSimilarDataset() {
        return similarDataset;
    }

    public void setSimilarDataset(Dataset similarDataset) {
        this.similarDataset = similarDataset;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}
