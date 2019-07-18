package uk.ac.ebi.ddi.service.db.service.dataset;

import org.springframework.stereotype.Service;
import uk.ac.ebi.ddi.service.db.model.dataset.DatasetFile;
import uk.ac.ebi.ddi.service.db.repo.dataset.IDatasetFilesRepo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatasetFileService {

    @Resource
    private IDatasetFilesRepo datasetFilesRepo;

    public DatasetFile save(DatasetFile datasetFile) {
        return datasetFilesRepo.save(datasetFile);
    }

    public void saveAll(Iterable<DatasetFile> datasetFiles) {
        datasetFilesRepo.saveAll(datasetFiles);
    }

    public List<String> getFiles(String accession, String database) {
        return datasetFilesRepo.findByAccessionAndDatabase(accession, database)
                .stream()
                .map(DatasetFile::getFileUrl)
                .collect(Collectors.toList());
    }

    public List<String> getFiles(String accession, String database, String from) {
        return datasetFilesRepo.findByAccessionAndDatabaseAndFrom(accession, database, from)
                .stream()
                .map(DatasetFile::getFileUrl)
                .collect(Collectors.toList());
    }

    /**
     * Delete all files that belong to a specific dataset
     * @param accession dataset's accession
     * @param database dataset's database
     * @return total items were deleted
     */
    public long deleteAll(String accession, String database) {
        return datasetFilesRepo.deleteByAccessionAndDatabase(accession, database);
    }

    /**
     * Delete all files from specific pipeline that belong to a specific dataset
     * @param accession dataset's accession
     * @param database dataset's database
     * @param from from which pipeline
     * @return total items were deleted
     */
    public long deleteAll(String accession, String database, String from) {
        return datasetFilesRepo.deleteByAccessionAndDatabaseAndFrom(accession, database, from);
    }
}
