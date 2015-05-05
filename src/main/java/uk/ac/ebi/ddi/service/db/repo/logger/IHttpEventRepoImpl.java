package uk.ac.ebi.ddi.service.db.repo.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.model.logger.ResourceStatVisit;

import java.util.List;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 05/05/2015
 */

@Component
public class IHttpEventRepoImpl {

   @Autowired
    MongoTemplate mongoTemplate;


    public List<ResourceStatVisit> getHttpEventByResource() {
        Aggregation aggregation = Aggregation.newAggregation(HttpEvent.class,
                Aggregation.group("abstractResource").count().as("total"),
                Aggregation.project("total").and("abstractResource").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "total"));

        AggregationResults<ResourceStatVisit> groupResults = mongoTemplate.aggregate(aggregation, HttpEvent.class, ResourceStatVisit.class);

        return groupResults.getMappedResults();
    }

}
