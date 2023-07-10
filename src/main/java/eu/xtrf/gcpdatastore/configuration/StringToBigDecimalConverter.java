package eu.xtrf.gcpdatastore.configuration;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String numberString) {
        return new BigDecimal(numberString).setScale(2, RoundingMode.HALF_UP);
    }

}
