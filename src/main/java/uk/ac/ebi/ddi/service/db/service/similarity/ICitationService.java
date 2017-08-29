package uk.ac.ebi.ddi.service.db.service.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.repo.similarity.ICitationRepo;

import java.util.List;

/**
 * Created by gaur on 20/07/17.
 */
public interface ICitationService {

    void saveCitation(Citations citations);


    Citations read(String accession,String database);
}
