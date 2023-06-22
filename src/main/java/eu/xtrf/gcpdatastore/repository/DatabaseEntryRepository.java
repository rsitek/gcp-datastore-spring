package eu.xtrf.gcpdatastore.repository;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DatabaseEntryRepository extends DatastoreRepository<DatabaseEntry, Long> {
    @Transactional
    DatabaseEntry findByOperationSystemAndBambooGoalsDonePercent(String operationSystem, String bambooGoalsDonePercent);
}
