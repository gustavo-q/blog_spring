package cn.keovi.utils;

import java.math.BigDecimal;

public class DecimalUtil {

    //金额的精度，默认为保留2位小数
    private static final Integer SCALE = 2;

    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;


    /**
     * 四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal round(BigDecimal value) {
        BigDecimal result = value.setScale(SCALE, ROUNDING_MODE);
        return result;
    }

    /**
     * 四舍五入,保留位数传入
     *
     * @param value
     * @return
     */
    public static BigDecimal round(BigDecimal value,int length) {
        if (value==null){
            value = new BigDecimal(0);
        }

        /* 2018-09-25 DMS质量版中暂时将金额默认设置为保留两位小数，不使用length的值 */
//        BigDecimal result = value.setScale(length, ROUNDING_MODE);
        BigDecimal result = value.setScale(SCALE, ROUNDING_MODE);
        return result;
    }

    /**
     * 返回x/y
     *
     * @param x
     * @param y
     * @return
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y) {
        return x.divide(y, SCALE, ROUNDING_MODE);
    }

    public static void main(String[] args) {
        System.out.println(round(new BigDecimal(123),4));
    }
}
