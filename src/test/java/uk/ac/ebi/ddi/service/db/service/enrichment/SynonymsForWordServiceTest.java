package uk.ac.ebi.ddi.service.db.service.enrichment;

import com.mongodb.DB;
import junit.framework.TestCase;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
/**
 * Created by mingze on 30/07/15.
 */
public class SynonymsForWordServiceTest extends TestCase {
    Fongo fongo = null;

    DB db = null;

    public void setUp() throws Exception {
        fongo = new Fongo("mylocaldb");

        db = fongo.getDB("ddiDBTest");
        DBCollection collection = db.getCollection("datasetaccess");

        collection.insert(new BasicDBObject("name", "jon"));
        DatasetResource myItem = new DatasetResource("PXD00001", "PRIDE");
        collection.insert(new BasicDBObject("myItem", myItem));

    }
}