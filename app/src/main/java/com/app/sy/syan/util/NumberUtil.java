package com.app.sy.syan.util;

import java.text.DecimalFormat;

/**
 * date 2019/3/2
 * version
 * describe
 *
 * @author hxd
 */
public class NumberUtil {
    /*
     * 如果是小数，保留两位，非小数，保留整数
     * @param number
     */
    public static String getDoubleString(double number) {
        String numberStr;
        if (((int) number * 1000) == (int) (number * 1000)) {
            //如果是一个整数
            numberStr = String.valueOf((int) number);
        } else {
            DecimalFormat df = new DecimalFormat("######0.00");
            numberStr = df.format(number);
        }
        return numberStr;
    }

}
