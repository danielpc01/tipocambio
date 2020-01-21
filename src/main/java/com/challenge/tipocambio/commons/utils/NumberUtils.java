package com.challenge.tipocambio.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author epena
 */
public final class NumberUtils {
    private static NumberUtils ourInstance = new NumberUtils();
    private static final int DEFAULT_ROUND_SCALE = 2;
    private static final int MINIMUM_FRACTION_DIGITS = 0;

    private NumberUtils() {
    }

    public static NumberUtils getInstance() {
        return ourInstance;
    }


    public BigDecimal roundDown(BigDecimal number) {
        if (number == null) {
            return null;
        }
        return number.setScale(DEFAULT_ROUND_SCALE,BigDecimal.ROUND_DOWN);
    }


    public BigDecimal roundDown(String number) {


        if (StringUtils.isBlank(number)) {
            return null;
        }

        if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(number)) {
            return null;
        }

        return roundDown(new BigDecimal(number));
    }

    public String format(BigDecimal number) {
        if (number == null) {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(DEFAULT_ROUND_SCALE);
        decimalFormat.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);

        return decimalFormat.format(number);
    }
}
