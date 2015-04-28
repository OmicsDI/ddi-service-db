package uk.ac.ebi.ddi.service.db.service.access;


import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.sun.deploy.config.DefaultConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.ddi.service.db.model.DatasetAccess;
import com.mongodb.DB;
import uk.ac.ebi.ddi.service.db.repo.IDatasetAccessRepo;

import javax.validation.constraints.AssertTrue;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})

public class AccessDatasetServiceTest{

    Fongo fongo = null;

    DB db = null;

    @Autowired
    DatasetAccessService datasetAccessService;

    @Before
    public void setUp(){

        fongo = new Fongo("mylocaldb");

        db = fongo.getDB("ddiDBTest");
        DBCollection collection = db.getCollection("datasetaccess");

        collection.insert(new BasicDBObject("name", "jon"));
        DatasetAccess myItem = new DatasetAccess(0, "PXD00001", "PRIDE", new Date(System.currentTimeMillis()));
        collection.insert(new BasicDBObject("myItem", myItem));

    }

    @Test
    public void create(){
        DBCollection collection = db.getCollection("datasetaccess");
        DatasetAccess myItem = new DatasetAccess(1, "PXD00002", "PRIDE", new Date(System.currentTimeMillis()));
        collection.insert(new BasicDBObject("myItem", myItem));
    }

    @Test
    public void test_basicOperations() throws Exception {
        // check if collection is empty
        List<DatasetAccess> datasetAccesses = datasetAccessService.readAll();
        Assert.assertTrue(datasetAccesses.size() == 0);
        // create new document
        DatasetAccess access = new DatasetAccess(3,"PXD0003", "pride", new Date());

        datasetAccessService.create(access);

        datasetAccesses = datasetAccessService.readAll();

        // check if document stored
        Assert.assertEquals(2, datasetAccesses.size());
        // check stored document
        Assert.assertEquals(access, datasetAccessService.read(BigInteger.valueOf(3)));
    }

    @Test
    public void test(){

        // get all created databases (they are created automatically the first time requested)
        Collection<DB> dbs = fongo.getUsedDatabases();
        for (DB adb : dbs){
            System.out.println("db: " + adb.toString());
        }
        // also
        List<String> dbNames = fongo.getDatabaseNames();
        for (String dbname: dbNames){
            System.out.println("dbname: " + dbname);
        }
        // also
//		fongo.dropDatabase("dbName");

        // get an instance of the hijacked com.mongodb.Mongo
        Mongo mongo = fongo.getMongo();
    }
}