package eu.xtrf.gcpdatastore.service

import eu.xtrf.gcpdatastore.GcpDatastoreApplication
import eu.xtrf.gcpdatastore.configuration.BigDecimalToStringConverter
import eu.xtrf.gcpdatastore.configuration.StringToBigDecimalConverter
import eu.xtrf.gcpdatastore.model.DatabaseEntry
import eu.xtrf.gcpdatastore.model.OperationSystem
import eu.xtrf.gcpdatastore.repository.DatabaseEntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = GcpDatastoreApplication)
class DatastoreServiceTest extends Specification {

    @Autowired
    private DatastoreService datastoreService
    @Autowired
    private DatabaseEntryRepository databaseEntryRepository

    def "should get datastore entries"() {
        given:
        List<DatabaseEntry> entries = [
                new DatabaseEntry(
                        fullName: "Test Entry",
                        operationSystem: OperationSystem.DEBIAN,
                        yearsInXRTF: 15,
                        bambooGoalsDonePercent: new BigDecimal("65.566"),
                        bambooGoalsDoneInteger: 65
                ),
                new DatabaseEntry(
                        fullName: "Test Entry 2",
                        operationSystem: OperationSystem.WINDOWS,
                        yearsInXRTF: 12,
                        bambooGoalsDonePercent: new BigDecimal("23.009"),
                        bambooGoalsDoneInteger: 23
                )
        ]
        databaseEntryRepository.saveAll { entries.iterator() }
        when:
        Iterable<DatabaseEntry> resultEntries = datastoreService.getDatabaseEntries()
        then:
        noExceptionThrown()
        !resultEntries.empty
    }

    def "should create datastore entry"() {
        given:
        DatabaseEntry entry = new DatabaseEntry(
                fullName: "Test Entry create",
                operationSystem: OperationSystem.DEBIAN,
                yearsInXRTF: 2,
                bambooGoalsDonePercent: new BigDecimal("36.032"),
                bambooGoalsDoneInteger: 36
        )
        when:
        def datastoreEntry = datastoreService.createDatastoreEntry(entry)
        then:
        noExceptionThrown()
        datastoreEntry
        datastoreEntry.fullName == entry.fullName
        datastoreEntry.operationSystem == entry.operationSystem
        datastoreEntry.yearsInXRTF == entry.yearsInXRTF
        datastoreEntry.bambooGoalsDonePercent == entry.bambooGoalsDonePercent
        datastoreEntry.bambooGoalsDoneInteger == entry.bambooGoalsDoneInteger
    }

    def "should return filtered and sorted entries by operation system and bamboo goals done percent"() {
        given:
        def entry1 = new DatabaseEntry(operationSystem: OperationSystem.WINDOWS, bambooGoalsDoneInteger: 80)
        def entry2 = new DatabaseEntry(operationSystem: OperationSystem.DEBIAN, bambooGoalsDoneInteger: 60)
        def entry3 = new DatabaseEntry(operationSystem: OperationSystem.WINDOWS, bambooGoalsDoneInteger: 90)
        def entry4 = new DatabaseEntry(operationSystem: OperationSystem.WINDOWS, bambooGoalsDoneInteger: 60)
        def entryList = [entry1, entry2, entry3, entry4]
        databaseEntryRepository.saveAll { entryList.iterator() }
        when:
        def result = datastoreService.getDatabaseEntryByOperationSystemAndBambooGoalDonePercent(OperationSystem.WINDOWS, 65)

        then:
        noExceptionThrown()
        !result.empty
        result.size() >= 2
    }

    def "should convert BigDecimal to String with proper rounding and trailing zeros removed"() {
        given:
        def converter = new BigDecimalToStringConverter()
        expect:
        converter.convert(new BigDecimal("12.000")) == "12"
        converter.convert(new BigDecimal("65.566")) == "65.57"
        converter.convert(new BigDecimal("43.099")) == "43.1"
    }

    def "should convert String to BigDecimal with proper rounding"() {
        given:
        def converter = new StringToBigDecimalConverter()
        expect:
        converter.convert("10") == new BigDecimal("10.00")
        converter.convert("12.3") == new BigDecimal("12.30")
        converter.convert("23.009") == new BigDecimal("23.01")
    }
}
