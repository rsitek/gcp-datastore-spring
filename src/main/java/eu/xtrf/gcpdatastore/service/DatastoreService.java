package eu.xtrf.gcpdatastore.service;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import eu.xtrf.gcpdatastore.model.OperationSystem;
import eu.xtrf.gcpdatastore.repository.DatabaseEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatastoreService {

    private final DatabaseEntryRepository databaseEntryRepository;

    public Iterable<DatabaseEntry> getDatabaseEntries() {
        return databaseEntryRepository.findAll();
    }

    public DatabaseEntry createDatastoreEntry(DatabaseEntry databaseEntry) {
        return databaseEntryRepository.save(databaseEntry);
    }

    public List<DatabaseEntry> getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(OperationSystem operationSystem, Integer bambooGoalsDoneInteger) {
        List<DatabaseEntry> entryFoundByOperationSystem = databaseEntryRepository.findByOperationSystem(operationSystem);
        return entryFoundByOperationSystem.stream()
                .filter(data -> data.getBambooGoalsDoneInteger() > bambooGoalsDoneInteger)
                .sorted(Comparator.comparing(DatabaseEntry::getBambooGoalsDoneInteger))
                .collect(Collectors.toList());
    }

}
