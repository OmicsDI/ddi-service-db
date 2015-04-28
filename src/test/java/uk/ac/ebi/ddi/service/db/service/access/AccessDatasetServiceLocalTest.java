package uk.ac.ebi.ddi.service.db.service.access;


import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.ac.ebi.ddi.service.db.model.DatasetAccess;

import java.math.BigInteger;
import java.util.Date;

/**
 * This class allow to do the testing locally using a mongoDB instance.
 * @author ypriverol
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})

public class AccessDatasetServiceLocalTest {

    @Autowired
    private DatasetAccessService service;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setup() throws Exception {
//        Mongo mongo = new Mongo("10.0.2.15");
//        mongoTemplateLocal = new MongoTemplate(mongo, "test");
    }

    @Test
    public void testAddAndGet() throws Exception {

        DatasetAccess datasetAccess = new DatasetAccess();
        datasetAccess.setAccession("PXD0001");
        datasetAccess.setDatabase("PRIDE");
        datasetAccess.setAccessDate(new Date());
        datasetAccess.setId(BigInteger.valueOf(1));
        service.create(datasetAccess);
        DatasetAccess datasetAccess1 = service.read(datasetAccess.getId());
        Assert.assertEquals(datasetAccess.getAccession(), datasetAccess1.getAccession());

        //Add a term without id
        datasetAccess = new DatasetAccess();
        datasetAccess.setAccession("PXD0001");
        datasetAccess.setDatabase("PRIDE");
        datasetAccess.setAccessDate(new Date());
        service.create(datasetAccess);

        Page<DatasetAccess> allEntries = service.readAll(0,20);
        System.out.println(allEntries.getSize());

    }

    @Test
    public void readAll(){
        for(int i = 0; i < 100; i++){
            DatasetAccess datasetAccess = new DatasetAccess();
            datasetAccess.setAccession("PXD0001" + i);
            datasetAccess.setDatabase("PRIDE");
            datasetAccess.setAccessDate(new Date());
            datasetAccess.setId(BigInteger.valueOf(i));
            service.create(datasetAccess);
        }
        Page<DatasetAccess> datassetAccessList = service.readAll(0, 100);
        for(DatasetAccess dat: datassetAccessList){
            System.out.println(dat.getAccessDate());
        }
    }

    @Test
    public void testUpdateDatasetAccess() throws Exception {

    }

    @Test
    public void testUserList() throws Exception {
    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @After
    public void close(){
        mongoTemplate.getDb().dropDatabase();
    }





}
