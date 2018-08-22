package uk.ac.ebi.ddi.service.db.repo.dataset;


import org.bson.types.ObjectId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.ddi.service.db.model.dataset.Dataset;


import java.util.List;
import java.util.Set;

/**
 * The Access Repository it give information about the access to any resource in the database and the system.
 *
 * @author Mingze
 */
@Repository
public interface IDatasetRepo extends MongoRepository<Dataset,ObjectId>, IDatasetRepoExtension{

    @Query("{'$and':[{accession : ?0}, {database : ?1},{'additional.isPrivateg':{'$ne':true}}]}")
    Dataset findByAccessionDatabaseQuery(String acc, String database);

    @Query(value="{ database : ?0 }", fields ="{database : 1, accession : 1, hashCode: 1, currentStatus: 1}")
    List<Dataset> findByDatabase(String name);

    @Query("{_id: ?0}")
    Dataset findByIdDatabaseQuery(ObjectId _id);

    @Query("{accession: ?0}")
    List<Dataset> findByAccession(String accession);

    @Query("{crossReferences.pubmed:?0}")
    List<Dataset> findByCrossReferencesPubmed(String pubmedId);

    @Query("{'$and':[{database : ?0}, {crossReferences.biomodels__db:{'$exists':true}}]}")
    List<Dataset> findByDatabaseBioModels(String database);

    @Query("{additional.full_dataset_link:?0}")
    Dataset findByFullDatasetLink(String url);

    @Query("{additional.secondary_accession:?0}")
    List<Dataset> getBySecondaryAccession(String url);

    @Query("{database:?0 ,'$where':'this.accession==this.name'}")
    List<Dataset> getPrivateByDatabase(String database);

    @Query("{additional.search_domains:{'$exists':false}}")
    Page<Dataset> getByDatasetWithoutSearchDomain(Pageable pageable);
}
