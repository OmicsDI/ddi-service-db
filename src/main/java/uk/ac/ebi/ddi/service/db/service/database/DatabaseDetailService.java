package uk.ac.ebi.ddi.service.db.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.database.DatabaseDetail;
import uk.ac.ebi.ddi.service.db.repo.database.DatabaseDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16.05.17.
 */
@Service
public class DatabaseDetailService {

    DatabaseDetailRepository databaseDetailRepository;

    Map<String,String> nameToSource;
    Map<String,String> sourceToName;

    @Autowired
    public DatabaseDetailService(DatabaseDetailRepository databaseDetailRepository) {
        this.databaseDetailRepository = databaseDetailRepository;

        this.getDatabaseList();
    }
    public List<DatabaseDetail> getDatabaseList() {
        List<DatabaseDetail> databaseDetailList = new ArrayList<>();
        Iterable<DatabaseDetail> iterable = databaseDetailRepository.findAll();
        for(DatabaseDetail databaseDetail : iterable){
            databaseDetail.setImage(null);
            databaseDetailList.add(databaseDetail);
        }
        computeResolvers(databaseDetailList);
        return databaseDetailList;
    }

    private void  computeResolvers(List<DatabaseDetail> databaseDetails){
        Map<String,String> nameToSource1 = new HashMap<String,String>();
        Map<String,String> sourceToName1 = new HashMap<String,String>();
        for (DatabaseDetail databaseDetail : databaseDetails) {
            nameToSource1.put(databaseDetail.getDatabaseName(), databaseDetail.getSource());
            sourceToName1.put(databaseDetail.getSource(),databaseDetail.getDatabaseName());
        }
        nameToSource = nameToSource1;
        sourceToName = sourceToName1;
    }

    public DatabaseDetail findDatabaseByName(String databaseName) {

        DatabaseDetail databaseDetail = databaseDetailRepository.findDatabaseByName(databaseName);
        return databaseDetail == null ? new DatabaseDetail() : databaseDetail;
    }

    public void saveDatabase(DatabaseDetail databaseDetail) {
        databaseDetailRepository.save(databaseDetail);
    }


    //** source -> name
    public String retriveAnchorName(String value){
        return sourceToName.get(value);
    }

    //** source -> name
    public String retriveSolrName(String value){
        return nameToSource.get(value);
    }


}
