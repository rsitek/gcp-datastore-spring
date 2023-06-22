package eu.xtrf.gcpdatastore.controller;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import eu.xtrf.gcpdatastore.service.DatastoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/datastore")
public class DatastoreController {

    private final DatastoreService datastoreService;

    @GetMapping
    public Iterable<DatabaseEntry> getDatabaseEntries() {
        return datastoreService.getDatabaseEntries();
    }

    @GetMapping("/{operationSystem}/{bambooGoalsDonePercent}")
    public DatabaseEntry getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(@PathVariable String operationSystem, @PathVariable String bambooGoalsDonePercent) {
        return datastoreService.getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(operationSystem, bambooGoalsDonePercent);
    }

    @PostMapping
    public void createDatastoreEntry(@RequestBody DatabaseEntry databaseEntry) {
        datastoreService.createDatastoreEntry(databaseEntry);
    }

}
