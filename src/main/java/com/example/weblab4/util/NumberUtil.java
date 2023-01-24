package com.example.weblab4.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
    public static double round(double value) {
        return new BigDecimal(value)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
