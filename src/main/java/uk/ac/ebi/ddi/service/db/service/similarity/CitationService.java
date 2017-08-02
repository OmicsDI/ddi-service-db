package uk.ac.ebi.ddi.service.db.service.similarity;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.repo.similarity.ICitationRepo;

/**
 * Created by gaur on 20/07/17.
 */
public class CitationService implements ICitationService {

    @Autowired
    public ICitationRepo citationRepo;

    public void saveCitation(Citations citations){
        citationRepo.save(citations);
    }
}
