package cn.keovi.utils;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUtils {

    //金额的精度，默认为保留2位小数
    private static final Integer SCALE = 2;

    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

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
