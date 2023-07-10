package eu.xtrf.gcpdatastore.configuration;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalToStringConverter implements Converter<BigDecimal, String> {

    @Override
    public String convert(BigDecimal source) {
        return source.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();
    }

}
