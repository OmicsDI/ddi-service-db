package uk.ac.ebi.ddi.service.db.repo.logger;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.ResourceStatVisit;

import java.util.List;
import java.util.Map;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2015
 */
public interface IHttpEventCustom {

    public List<ResourceStatVisit> getHttpEventByResource(int size);

}
