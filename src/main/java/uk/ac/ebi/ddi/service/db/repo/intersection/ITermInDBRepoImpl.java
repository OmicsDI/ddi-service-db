package uk.ac.ebi.ddi.service.db.repo.intersection;

import org.springframework.stereotype.Component;

/**
 * @author mingze
 * @date 05/09/2015
 */

@Component
public class ITermInDBRepoImpl {

//   @Autowired
//    MongoTemplate mongoTemplate;
//
//
//    public List<ResourceStatVisit> getHttpEventByDatasetResource(int size) {
//        Aggregation aggregation = Aggregation.newAggregation(HttpEvent.class,
//                Aggregation.match(Criteria.where("abstractResource.category").is(CategoryType.DATASET_RESOURCE.getCategory())),
//                Aggregation.group("abstractResource").count().as("total"),
//                Aggregation.project("total").and("abstractResource").previousOperation(),
//                Aggregation.sort(Sort.Direction.DESC, "total"), Aggregation.limit(size));
//
//        AggregationResults<ResourceStatVisit> groupResults = mongoTemplate.aggregate(aggregation, HttpEvent.class, ResourceStatVisit.class);
//
//        return groupResults.getMappedResults();
//    }

}
