package uk.ac.ebi.ddi.service.db.utils;

/**
 * Created by yperez on 20/06/2016.
 */
public enum DatasetSimilarsType {

    REANALYZED_BY("Reanalyzed by"),
    REANALYSIS_OF("Reanalysis of"),
    OTHER_OMICS_DATA("Other Omics Data in:");

    private final String type;

    DatasetSimilarsType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
