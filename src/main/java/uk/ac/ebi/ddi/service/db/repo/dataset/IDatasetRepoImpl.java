package uk.ac.ebi.ddi.service.db.repo.dataset;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.service.db.model.dataset.MergeCandidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Override
    public List<MergeCandidate> getMergeCandidates(int start, int size){

        /*****************************
        db.mergeCandidates.aggregate([
         {$group:{
            "_id":{
                 "database":"$database"
                ,"accession":"$accession"
            },"Similars":{
                $push:
                {
                    "database":"$database1"
                  , "accession":"$accession1"
                }
            }}
         }
         ,{$project:{
                "_id":0
                , "accession":"$_id.accession"
                , "database":"$_id.database"
                , "Similars":"$Similars"
            }
         }
         ,{$sort:{
                "database":1
               ,"accession":1
            }
         }
         ,{$limit:100}
         ,{$skip:10}
         ]).pretty()
        **************************/

        Aggregation agg = newAggregation(
                group("database","accession").push(new BasicDBObject
                        ("accession", "$accession1").append
                        ("database", "$database1")).as("Similars"),
                project().and("_id.accession").as("accession").and("_id.database").as("database").and("Similars").as("Similars"),
                sort(Sort.Direction.DESC, "database").and(Sort.Direction.DESC, "accession"),
                limit(start+size),
                skip(start)
        );

        AggregationResults<MergeCandidate> aggregationResults = mongoOperations.aggregate(agg,"mergeCandidates",MergeCandidate.class);

        List<MergeCandidate> results = aggregationResults.getMappedResults();

        return results;

        /****MergeCandidate candidate = new MergeCandidate();
        candidate.setName("Some name");
        List<MergeCandidate> dummy = new ArrayList<>();
        dummy.add(candidate);return dummy;*******/


    }

    @Override
    public Integer getMergeCandidateCount(){
        return (int) mongoOperations.count(new BasicQuery("{}"),"mergeCandidates");
    }

    @Override
    public void mergeDataset(MergeCandidate mergeData){
        mongoOperations.insert(mergeData,"mergedDatasets");
    }

    @Override
    public void deleteMergeCandidte(String database, String accession){
        mongoOperations.remove(new BasicQuery("{ accession : '"+ accession +"', database : '"+ database +"' }"),"mergeCandidates");

        mongoOperations.remove(new BasicQuery("{ accession1 : '"+ accession +"', database1 : '"+ database +"' }"),"mergeCandidates");
    }


    @Override
    public void delete(String database, String accession){
        mongoOperations.remove(new BasicQuery("{ accession : '"+ accession +"', database : '"+ database +"' }"),"datasets.dataset");
    }

    @Override
    public void addSecondaryAccession(String database, String accession, String secondaryAccession){

        Update update = new Update();

        update.push("additional.secondary_accession",secondaryAccession);

        mongoOperations.updateFirst(new BasicQuery("{ accession : '"+ accession +"', database : '"+ database +"' }"),update, "datasets.dataset");
    }
}

