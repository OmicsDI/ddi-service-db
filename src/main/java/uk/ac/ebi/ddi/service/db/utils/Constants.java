package uk.ac.ebi.ddi.service.db.utils;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2015
 */
public class Constants {

    public static String UNKNOWN_DATABASE = "unkownDatabase";
    public static String DATABASE_FIELD = "database";
    public static String ISCLAIMED_FIELD = "isClaimable";
    public static String DATASET_COLLECTION = "datasets.dataset";
    public static String SIMILARS_FIELD = "similars";
    public static String SIMILARS_RELATIONTYPE = "similars.relationType";
    public static String REANALYSIS_TYPE = "Reanalysis of";
    public static String REANALYZED_TYPE = "Reanalyzed by";
    public static String ACCESSION_FIELD = "accession";
    public static String TOTAL_FIELD ="total";
    public static String REANALYSIS_FIELD = "reanalysis_count";
    public static String VIEWCOUNT_FIELD = "view_count";
    public static String LOGGER_COLLECTION = "logger.event";

    public enum Database{
        PRIDE("Pride", "pride"),
        PEPTIDEATLAS("PeptideAtlas", "peptide_atlas"),
        MASSIVE("Massive", "massive"),
        METABOLIGHTS("MetaboLights", "metabolights_dataset"),
        EGA("EGA", "ega"),
        GPMDB("GPMDB",  "gpmdb"),
        GNPS("GNPS", "gnps"),
        ARRAY_EXPRESS("ArrayExpress", "arrayexpress-repository"),
        METABOLOMEEXPRESS("MetabolomeExpress", "metabolome_express"),
        EXPRESSION_ATLAS("ExpressionAtlas", "atlas-experiments"),
        METABOLOMICSWORKBENCH("MetabolomicsWorkbench", "metabolomics_workbench"),
        BIOMODELS("BioModels Database","biomodels"),
        LINCS("LINCS","lincs"),
        PAXDB("PAXDB","paxdb"),
        JPOST("JPOST Repository","jpost"),
        EVA("EVA","eva");

        String databaseName;
        String solarName;

        Database(String databaseName, String solrName) {
            this.databaseName = databaseName;
            this.solarName = solrName;
        }

        public String getDatabaseName() {
            return databaseName;
        }

        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }

        public String getSolarName() {
            return solarName;
        }

        public void setSolarName(String solarName) {
            this.solarName = solarName;
        }

        public static String retriveAnchorName(String name){
            for(Database database: values())
                if(database.solarName.equalsIgnoreCase(name))
                    return database.getDatabaseName();
            return name;
        }

        public static String retriveSorlName(String name) {
            for(Database database: values())
                if(database.getDatabaseName().equalsIgnoreCase(name))
                    return database.getSolarName();
            return name;
        }
    }
}
