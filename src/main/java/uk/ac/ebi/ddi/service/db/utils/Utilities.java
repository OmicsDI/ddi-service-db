package uk.ac.ebi.ddi.service.db.utils;

import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utilities {

    public static Dataset addAdditionalField(Dataset dataset, String key, String value) {
        Map<String, Set<String>> additional = dataset.getAdditional();
        if(additional == null)
            additional = new HashMap<>();
        if(key != null && value != null){
            Set<String> values = new HashSet<>();
            if(additional.containsKey(key))
                values = additional.get(key);
            values.add(value);
            additional.put(key, values);
            dataset.setAdditional(additional);
        }
        return dataset;
    }
}
