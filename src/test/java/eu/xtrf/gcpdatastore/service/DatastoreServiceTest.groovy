package eu.xtrf.gcpdatastore.service

import eu.xtrf.gcpdatastore.model.DatabaseEntry
import eu.xtrf.gcpdatastore.model.OperationSystem
import eu.xtrf.gcpdatastore.repository.DatabaseEntryRepository
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DatastoreServiceTest extends Specification {

    private final Random random = new Random()
    private DatastoreService datastoreService
    private DatabaseEntryRepository databaseEntryRepository

    def setup() {
        databaseEntryRepository = Mock(DatabaseEntryRepository)
        datastoreService = new DatastoreService(databaseEntryRepository)
    }

    def "should get datastore entries"() {
        given:
        List entries = [
                new DatabaseEntry(
                        id: random.nextLong(),
                        fullName: "Test Entry",
                        operationSystem: OperationSystem.DEBIAN,
                        yearsInXRTF: random.nextInt(),
                        bambooGoalsDonePercent: new BigDecimal(random.nextDouble().toString())
                ),
                new DatabaseEntry(
                        id: random.nextLong(),
                        fullName: "Test Entry 2",
                        operationSystem: OperationSystem.WINDOWS,
                        yearsInXRTF: random.nextInt(),
                        bambooGoalsDonePercent: new BigDecimal(random.nextDouble().toString())
                )
        ]
        databaseEntryRepository.findAll() >> entries
        when:
        Iterable<DatabaseEntry> resultEntries = datastoreService.getDatabaseEntries()
        then:
        noExceptionThrown()
        entries == resultEntries
    }

    def "should create datastore entry"() {
        given:
        DatabaseEntry entry = new DatabaseEntry(
                id: random.nextLong(),
                fullName: "Test Entry",
                operationSystem: OperationSystem.DEBIAN,
                yearsInXRTF: random.nextInt(),
                bambooGoalsDonePercent: new BigDecimal(random.nextDouble().toString())
        )
        when:
        datastoreService.createDatastoreEntry(entry)
        then:
        noExceptionThrown()
        1 * databaseEntryRepository.save(entry)
    }

    def "should get database entry by operation system and bamboo goal done percent"() {
        given:
        String operationSystem = "WINDOWS"
        String bambooGoalsDonePercent = "80"
        DatabaseEntry entry = new DatabaseEntry(
                id: random.nextLong(),
                fullName: "Test Entry",
                operationSystem: operationSystem,
                yearsInXRTF: random.nextInt(),
                bambooGoalsDonePercent: new BigDecimal("80")
        )
        databaseEntryRepository.findByOperationSystemAndBambooGoalsDonePercent(operationSystem, bambooGoalsDonePercent) >> entry
        when:
        DatabaseEntry resultEntry = datastoreService.getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(operationSystem, bambooGoalsDonePercent)
        then:
        noExceptionThrown()
        resultEntry == entry
    }
}
