package uk.ac.ebi.ddi.service.db.model.logger;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 30/04/2015
 */
@Document(collection = "logger.Resource")

public class GenericResource extends AbstractResource {

}
