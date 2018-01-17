package uk.ac.ebi.ddi.service.db.repo.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Created by azorin on 11/01/2018.
 */
@Component
public class IDatasetRepoImpl implements IDatasetRepoExtension {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Set<String> getSecondaryAccessions(){
        //using mongoOperations create the query and execute. Return the property values from document

        //db["datasets.dataset"].aggregate([{$unwind:"$additional.additional_accession"},{$project:{"_id":0,"accession":"$additional.additional_accession"}},{$sort:{"accession":1}}])
        Aggregation agg = newAggregation(
                unwind("additional.additional_accession"),
                project().and("additional.additional_accession").as("accession"),
                sort(Sort.Direction.DESC, "accession")
        );

        AggregationResults<SimpleAccessionObject> results = mongoOperations.aggregate(agg,"datasets.dataset",SimpleAccessionObject.class);

        //TODO: upgrade language level return results.getMappedResults().stream().map(x -> x.accession);
        Set<String> result = new HashSet<String>();
        for(SimpleAccessionObject o : results.getMappedResults()){
            result.add(o.accession);
        }

        System.out.print(String.format("getSecondaryAccessions returns %d entries \n", result.size()));

        return result;
    }
}

