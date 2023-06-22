package eu.xtrf.gcpdatastore.configuration;

import org.springframework.cloud.gcp.data.datastore.core.convert.DatastoreCustomConversions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    static class BigDecimalToStringConverter implements Converter<BigDecimal, String> {

        @Override
        public String convert(BigDecimal source) {
            return source.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();
        }

    }

    static class StringToBigDecimalConverter implements Converter<String, BigDecimal> {

        @Override
        public BigDecimal convert(String numberString) {
            return new BigDecimal(numberString).setScale(2, RoundingMode.HALF_UP);
        }

    }

}
