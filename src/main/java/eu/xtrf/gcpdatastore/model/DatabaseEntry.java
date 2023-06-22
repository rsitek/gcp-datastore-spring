package eu.xtrf.gcpdatastore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Unindexed;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity(name = "rsitekDatabaseEntry")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseEntry {
    @Id
    @Unindexed
    private Long id;
    private String fullName;
    private OperationSystem operationSystem;
    private Integer yearsInXRTF;
    private BigDecimal bambooGoalsDonePercent;
}
