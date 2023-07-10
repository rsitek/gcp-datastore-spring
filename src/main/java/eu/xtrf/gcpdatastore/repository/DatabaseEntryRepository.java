package eu.xtrf.gcpdatastore.repository;

import eu.xtrf.gcpdatastore.model.DatabaseEntry;
import eu.xtrf.gcpdatastore.model.OperationSystem;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DatabaseEntryRepository extends DatastoreRepository<DatabaseEntry, Long> {
    @Transactional
    List<DatabaseEntry> findByOperationSystem(OperationSystem operationSystem);

}
