package eu.xtrf.gcpdatastore.configuration;

import org.springframework.cloud.gcp.data.datastore.core.convert.DatastoreCustomConversions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatastoreConfiguration {

    @Bean
    public DatastoreCustomConversions datastoreCustomConversions() {
        return new DatastoreCustomConversions(Arrays.asList(
                new BigDecimalToStringConverter(),
                new StringToBigDecimalConverter()
        ));
    }

}
