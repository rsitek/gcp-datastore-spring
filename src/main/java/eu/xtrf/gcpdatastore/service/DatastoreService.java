package eu.xtrf.gcpdatastore.service;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import eu.xtrf.gcpdatastore.repository.DatabaseEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatastoreService {

    private final DatabaseEntryRepository databaseEntryRepository;

    public Iterable<DatabaseEntry> getDatabaseEntries() {
        return databaseEntryRepository.findAll();
    }

    public void createDatastoreEntry(DatabaseEntry databaseEntry) {
        databaseEntryRepository.save(databaseEntry);
    }

    public DatabaseEntry getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(String systemName, String bambooGoalsDonePercent) {
        return databaseEntryRepository.findByOperationSystemAndBambooGoalsDonePercent(systemName, bambooGoalsDonePercent);
    }
}
