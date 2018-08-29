package com.util;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * 读取配置文件
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class SystemUtils {
    /**
     * 从当前系统的配置文件中获取配置信息
     */
    public static final int MONEY = Integer.parseInt(ResourceBundle.getBundle(
            "data/bank").getString("money"));

    /**
     * 获取当前类所在的路径
     *
     * @param directory
     * @param clazz
     * @return
     * @throws IOException
     */
    public static String getCurrentPath(final File directory, Class clazz) throws IOException {
        return directory.getCanonicalPath() + "/src/" + clazz.getPackage().getName().replace(".", "/");
    }

    public static String formatDate() {
        String dateStr = "2018-07-31 11:51:51.678";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        try {
            return sdf.format(sdf.parse(dateStr.trim()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String formatNumber(int minimumDigits, int maximumDigits, boolean groupingUsed, int source) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(minimumDigits);
        numberFormat.setMaximumIntegerDigits(maximumDigits);
        numberFormat.setGroupingUsed(groupingUsed);
        return numberFormat.format(source);
    }

}
