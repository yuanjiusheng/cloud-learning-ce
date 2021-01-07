package com.yjs.cloud.learning.learn.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数字工具
 *
 * @author Bill.lai
 * @since 2019-11-15
 */
public class NumberUtils {

    /**
     * 两个数相加
     * @param addend 被加数
     * @param augend 加数
     * @return 相加后的结果
     */
    public static Double add(Double addend, Double augend){
        if(addend == null){
            addend = 0D;
        }
        if(augend == null){
            augend = 0D;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(addend);
        return bigDecimal.add(BigDecimal.valueOf(augend)).doubleValue();
    }

    /**
     * 两个数相加
     * @param addend 被加数
     * @param augend 加数
     * @return 相加后的结果
     */
    public static Long add(Long addend, Long augend){
        if(addend == null){
            addend = 0L;
        }
        if(augend == null){
            augend = 0L;
        }
        addend += augend;
        return addend;
    }

    /**
     * 两个数相除
     * @param dividend 被除数
     * @param divisor 除数
     * @return 相除后的结果
     */
    public static Double divide(Double dividend, Double divisor, int... scale){
        if(dividend == null){
            dividend = 0D;
        }
        if(divisor == null || divisor == 0){
            return null;
        }
        if (scale == null) {
            scale = new int[]{4};
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(dividend);
        return bigDecimal.divide(BigDecimal.valueOf(divisor), scale[0], RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 两个数相乘
     * @param dividend 被乘数
     * @param divisor 乘数
     * @return 相乘后的结果
     */
    public static Double multiply(Double dividend, Double divisor){
        if(dividend == null){
            dividend = 0D;
        }
        if(divisor == null){
            divisor = 0D;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(dividend);
        return bigDecimal.multiply(BigDecimal.valueOf(divisor)).doubleValue();
    }

}
