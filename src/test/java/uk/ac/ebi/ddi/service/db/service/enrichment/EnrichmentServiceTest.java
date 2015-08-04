package uk.ac.ebi.ddi.service.db.service.enrichment;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.ddi.service.db.model.enrichment.EnrichedDataset;

/**
 * Created by mingze on 03/08/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})

public class EnrichmentServiceTest {
    @Autowired
    private IEnrichmentService enrichmentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testAddAndGetDataset() {

        Page<EnrichedDataset> allEntries = enrichmentService.readAll(0, 1000000000);
        System.out.println(allEntries.getSize());
        for (EnrichedDataset datasettemp : allEntries) {
            enrichmentService.delete(datasettemp.getId());
        }

        EnrichedDataset dataset = new EnrichedDataset("pxd000002");
        dataset.setStatus("status1");
        dataset.setTitle("title");
        dataset.setAbstractDescription("abstraction");
        dataset.setSampleProtocol("sampleprotocol");
        dataset.setDataProtocol("dataprotocol");
        enrichmentService.insert(dataset);
        EnrichedDataset dataset1 = enrichmentService.read(dataset.getId());
        Assert.assertEquals(dataset.getId(), dataset1.getId());
        Assert.assertEquals(dataset.getDatasetRepoId(), dataset1.getDatasetRepoId());
        Assert.assertEquals(dataset.getTitle(), dataset1.getTitle());
        Assert.assertEquals(dataset.getAbstractDescription(), dataset1.getAbstractDescription());
        Assert.assertEquals(dataset.getSampleProtocol(), dataset1.getSampleProtocol());
        Assert.assertEquals(dataset.getDatasetRepoId(), dataset1.getDatasetRepoId());
        Assert.assertEquals(dataset.getStatus(), dataset1.getStatus());

        System.out.println(dataset.getTitle());

        //Add a term without id
        EnrichedDataset dataset2 = new EnrichedDataset("pxd000002");
        dataset.setStatus("status1");
        dataset.setTitle("title");
        dataset.setAbstractDescription("abstraction");
        dataset.setSampleProtocol("sampleprotocol");
        dataset.setDataProtocol("dataprotocol");
        enrichmentService.insert(dataset2);


        allEntries = enrichmentService.readAll(0, 1000000000);
        System.out.println(allEntries.getSize());
        for (EnrichedDataset datasettemp : allEntries) {
            System.out.println(datasettemp.toString());
        }

    }


    @Test
    public void testReadAll() {
        for (int i = 0; i < 100; i++) {
            EnrichedDataset dataset = new EnrichedDataset("pxd00000" + i);
            dataset.setStatus("status1");
            dataset.setTitle("title");
            dataset.setAbstractDescription("abstraction");
            dataset.setSampleProtocol("sampleprotocol");
            dataset.setDataProtocol("dataprotocol");
            enrichmentService.insert(dataset);
        }
        Page<EnrichedDataset> enrichmentDatasetss = enrichmentService.readAll(0, 100000000);
        for (EnrichedDataset datasettemp : enrichmentDatasetss) {
            System.out.println(datasettemp.toString());
        }
    }


    @Test
    public void testInsert() throws Exception {

    }

    public void testRead() throws Exception {

    }


    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {

    }

    public void testReadByRepoId() throws Exception {

    }

    public void testIsDatasetExist() throws Exception {

    }
}