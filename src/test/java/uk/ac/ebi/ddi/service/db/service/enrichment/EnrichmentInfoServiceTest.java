package uk.ac.ebi.ddi.service.db.service.enrichment;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.ddi.service.db.model.enrichment.DatasetEnrichmentInfo;
import uk.ac.ebi.ddi.service.db.model.enrichment.WordInField;

import java.util.ArrayList;

/**
 * Created by mingze on 03/08/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})

public class EnrichmentInfoServiceTest {
    @Autowired
    private IEnrichmentInfoService enrichmentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testAddAndGetDataset() {

        Page<DatasetEnrichmentInfo> allEntries = enrichmentService.readAll(0, 1000000000);
        System.out.println(allEntries.getSize());
//        for (DatasetEnrichmentInfo datasettemp : allEntries) {
//           if(datasettemp.getId().toString().startsWith("pxd"))
//               enrichmentService.delete(datasettemp.getId());
//        }

        DatasetEnrichmentInfo dataset = new DatasetEnrichmentInfo("pxd000001", "PRIDE");
        dataset.setStatus("status1");

        ArrayList<WordInField> enrichedTitle = new ArrayList<>();
        enrichedTitle.add(new WordInField("title", 0, 4));
        enrichedTitle.add(new WordInField("title2", 8, 13));
        dataset.setTitle(enrichedTitle);

        ArrayList<WordInField> enrichedAbs = new ArrayList<>();
        enrichedAbs.add(new WordInField("abstr", 0, 4));
        enrichedAbs.add(new WordInField("abstr2", 8, 13));
        dataset.setAbstractDescription(enrichedAbs);

        ArrayList<WordInField> enrichedSamp = new ArrayList<>();
        enrichedSamp.add(new WordInField("sampl", 0, 4));
        enrichedSamp.add(new WordInField("sampl2", 8, 13));
        dataset.setSampleProtocol(enrichedSamp);

        ArrayList<WordInField> enrichedDatap = new ArrayList<>();
        enrichedDatap.add(new WordInField("datap", 0, 4));
        enrichedDatap.add(new WordInField("datap2", 8, 13));
        dataset.setDataProtocol(enrichedDatap);

        enrichmentService.insert(dataset);
        DatasetEnrichmentInfo dataset1 = enrichmentService.read(dataset.getId());
        Assert.assertEquals(dataset.getId(), dataset1.getId());
        Assert.assertEquals(dataset.getAccession(), dataset1.getAccession());
        Assert.assertEquals(dataset.getTitle().toString(), dataset1.getTitle().toString());
        Assert.assertEquals(dataset.getAbstractDescription().toString(), dataset1.getAbstractDescription().toString());
        Assert.assertEquals(dataset.getSampleProtocol().toString(), dataset1.getSampleProtocol().toString());
        Assert.assertEquals(dataset.getStatus(), dataset1.getStatus());

        System.out.println(dataset.getTitle());

        //Add a term without id
        DatasetEnrichmentInfo dataset2 = new DatasetEnrichmentInfo("pxd000002", "PRIDE");
        dataset2.setStatus("status1");
        ArrayList<WordInField> enrichedTitle2 = new ArrayList<>();
        enrichedTitle2.add(new WordInField("title", 0, 4));
        enrichedTitle2.add(new WordInField("title2", 8, 13));
        dataset2.setTitle(enrichedTitle2);

        ArrayList<WordInField> enrichedAbs2 = new ArrayList<>();
        enrichedAbs2.add(new WordInField("abstr", 0, 4));
        enrichedAbs2.add(new WordInField("abstr2", 8, 13));
        dataset2.setAbstractDescription(enrichedAbs2);

        ArrayList<WordInField> enrichedSamp2 = new ArrayList<>();
        enrichedSamp2.add(new WordInField("sampl", 0, 4));
        enrichedSamp2.add(new WordInField("sampl2", 8, 13));
        dataset2.setSampleProtocol(enrichedSamp2);

        ArrayList<WordInField> enrichedDatap2 = new ArrayList<>();
        enrichedDatap2.add(new WordInField("datap", 0, 4));
        enrichedDatap2.add(new WordInField("datap2", 8, 13));
        dataset2.setDataProtocol(enrichedDatap2);

        enrichmentService.insert(dataset2);

        allEntries = enrichmentService.readAll(0, 100);
        System.out.println(allEntries.getSize());
        for (DatasetEnrichmentInfo datasettemp : allEntries) {
            System.out.println(datasettemp.toString());
        }

    }


    @Test
    public void testReadAll() {
        for (int i = 0; i < 1000; i++) {
            DatasetEnrichmentInfo dataset = new DatasetEnrichmentInfo("pxd00000" + i, "PRIDE");
            dataset.setStatus("status1");
            ArrayList<WordInField> enrichedTitle = new ArrayList<>();
            enrichedTitle.add(new WordInField("title", 0, 4));
            enrichedTitle.add(new WordInField("title2", 8, 13));
            dataset.setTitle(enrichedTitle);

            ArrayList<WordInField> enrichedAbs = new ArrayList<>();
            enrichedAbs.add(new WordInField("abstr", 0, 4));
            enrichedAbs.add(new WordInField("abstr22", 8, 14));
            dataset.setAbstractDescription(enrichedAbs);

            ArrayList<WordInField> enrichedSamp = new ArrayList<>();
            enrichedSamp.add(new WordInField("sampl", 0, 4));
            enrichedSamp.add(new WordInField("sampl2", 8, 13));
            dataset.setSampleProtocol(enrichedSamp);

            ArrayList<WordInField> enrichedDatap = new ArrayList<>();
            enrichedDatap.add(new WordInField("datap", 0, 4));
            enrichedDatap.add(new WordInField("datap2", 8, 13));
            dataset.setDataProtocol(enrichedDatap);

            enrichmentService.update(dataset);
        }

        long startTime = System.currentTimeMillis();

        Page<DatasetEnrichmentInfo> enrichmentDatasetss = enrichmentService.readAll(0, 10000);
        for (DatasetEnrichmentInfo datasettemp : enrichmentDatasetss) {
            //System.out.println(datasettemp.toString());
            datasettemp.getTitle();
            datasettemp.getAbstractDescription();
            datasettemp.getSampleProtocol();
            datasettemp.getDataProtocol();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds to get 1000 datasets from local MongoDB");

    }


    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testRead() throws Exception {

        DatasetEnrichmentInfo enrichmentInfo = enrichmentService.readByAccession("PXD002287", "PRIDE");
//        System.out.println(enrichmentInfo.getTitle());

    }


    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {

    }


    public void testIsDatasetExist() throws Exception {

    }
}