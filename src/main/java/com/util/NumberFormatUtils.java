package com.util;


import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author Tyler Yin
 * @create 2018-08-10 15:18
 * @description
 **/
public class NumberFormatUtils {

    public static Number parse(int minimumDigits, int maximumDigits, boolean groupingUsed, String source) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(minimumDigits);
        numberFormat.setMaximumIntegerDigits(maximumDigits);
        numberFormat.setGroupingUsed(groupingUsed);
        return numberFormat.parse(source);
    }

    public static String format(int minimumDigits, int maximumDigits, boolean groupingUsed, int source) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(minimumDigits);
        numberFormat.setMaximumIntegerDigits(maximumDigits);
        numberFormat.setGroupingUsed(groupingUsed);
        return numberFormat.format(source);
    }
}
