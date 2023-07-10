package eu.xtrf.gcpdatastore.controller;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import eu.xtrf.gcpdatastore.model.OperationSystem;
import eu.xtrf.gcpdatastore.service.DatastoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/datastore")
public class DatastoreController {

    private final DatastoreService datastoreService;

    @GetMapping
    public Iterable<DatabaseEntry> getDatabaseEntries() {
        return datastoreService.getDatabaseEntries();
    }

    @GetMapping("/{operationSystem}/{bambooGoalsDoneInteger}")
    public List<DatabaseEntry> getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(
            @PathVariable OperationSystem operationSystem, @PathVariable Integer bambooGoalsDoneInteger) {
        return datastoreService.getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(operationSystem, bambooGoalsDoneInteger);
    }

    @PostMapping
    public DatabaseEntry createDatastoreEntry(@RequestBody DatabaseEntry databaseEntry) {
        return datastoreService.createDatastoreEntry(databaseEntry);
    }

}
