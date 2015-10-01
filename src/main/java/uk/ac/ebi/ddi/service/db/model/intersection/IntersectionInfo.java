package uk.ac.ebi.ddi.service.db.model.intersection;

/**
 * Created by mingze on 11/09/15.
 * Intersection information between two datasets by each term
 */
public class IntersectionInfo {
    private String relatedDatasetAcc;
    private double cosineScore;
    private int sharedTermsNo;

    public String getRelatedDatasetAcc() {
        return relatedDatasetAcc;
    }

    public void setRelatedDatasetAcc(String relatedDatasetAcc) {
        this.relatedDatasetAcc = relatedDatasetAcc;
    }

    public double getCosineScore() {
        return cosineScore;
    }

    public void setCosineScore(double consineScore) {
        this.cosineScore = consineScore;
    }

    public int getSharedTermsNo() {
        return sharedTermsNo;
    }

    public void setSharedTermsNo(int sharedTermsNo) {
        this.sharedTermsNo = sharedTermsNo;
    }

    public void increaseOneSharedTermsNo() {
        this.sharedTermsNo++;
    }
}
