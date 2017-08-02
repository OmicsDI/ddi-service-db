package uk.ac.ebi.ddi.service.db.service.similarity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gaur on 11/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})
public class DatasetStatInfoServiceTest {

    @Autowired
    DatasetStatInfoService datasetStatInfoService;

    @Test
    public void testReanalaysisCount(){
        datasetStatInfoService.reanalysisCount();
    }
}
