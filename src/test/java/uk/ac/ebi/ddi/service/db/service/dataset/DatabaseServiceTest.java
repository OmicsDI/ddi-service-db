package uk.ac.ebi.ddi.service.db.service.dataset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gaur on 15/08/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationTestContext.xml"})
public class DatabaseServiceTest {

    @Autowired
    DatabaseService databaseService;

    @Test
    public void checkDatabase(){
        assert (databaseService.readAll().size() > 1);
    }


}
