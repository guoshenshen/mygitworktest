package com.elearning.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 17:11
 */
public class DoubleUtil {
    private DoubleUtil (){

    }
    public static double getTwoDigitalDoubleData(double d){
        BigDecimal bg = new BigDecimal(d);
        /**
         * 参数：
         newScale - 要返回的 BigDecimal 值的标度。
         roundingMode - 要应用的舍入模式。
         返回：
         一个 BigDecimal，其标度为指定值，其非标度值可以通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定。
         */
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
