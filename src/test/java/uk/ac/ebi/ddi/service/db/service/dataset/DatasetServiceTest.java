package uk.ac.ebi.ddi.service.db.service.dataset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;
import uk.ac.ebi.ddi.service.db.utils.DatasetCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 04/05/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})
public class DatasetServiceTest {

    @Autowired
    private IDatasetService datasetService;


    @Test
    public void testSave() throws Exception {
        Map<String, Set<String>> dates = new HashMap<>();
        Map<String, Set<String>> additional = new HashMap<>();
        Dataset newDataset = new Dataset("ArrayExpress", "E-ATMX-14", "Example Name", "Description Example", dates, additional,additional, DatasetCategory.INSERTED);
        datasetService.save(newDataset);
    }

    @Test
    public void testRead() throws Exception {

        List<Dataset> datasets = datasetService.readDatasetHashCode("ArrayExpress");
        System.out.println(datasets);

    }

    @Test
    public void testReadAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testRead1() throws Exception {

    }
}