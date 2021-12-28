package cn.keovi.utils;

import java.math.BigDecimal;

/**
 *
 */
public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static String getDistance(String lat1, String lng1, String lat2,
                                     String lng2) {
        double radLat1 = rad(Double.parseDouble(lat1));
        double radLat2 = rad(Double.parseDouble(lat2));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(lng1)) - rad(Double.parseDouble(lng2));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
//        double s2 = new BigDecimal(s).divide(new BigDecimal(100)).doubleValue();
        BigDecimal s2 = new BigDecimal(s).setScale(2,BigDecimal.ROUND_HALF_UP);
        return s2.toString();
    }
    /**
     * 毫秒数转时间
     * @param mss
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " 天 " + hours + " 小时 " + minutes + " 分钟 "
                + seconds + " 秒 ";
    }


}
