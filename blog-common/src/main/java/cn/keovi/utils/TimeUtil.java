package cn.keovi.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    /**
     * 当前时间戳保留10位
     * @return
     */
    public static Integer currentStampKeep10(){
        return Integer.valueOf(String.valueOf(System.currentTimeMillis()).substring(0,10));
    }

    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s) throws Exception{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        String d = simpleDateFormat.format(lt);
        Date date=simpleDateFormat.parse(d);
        return date;
    }

    /*
     * 将String转换为Date
     */
    public static Date strToDate(String s) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=simpleDateFormat.parse(s);
        return date;
    }


    /*
     * 将毫秒转换为小时（保留一位）
     */
    public static Float msToFloat(long ms) throws Exception{
        double d = ms / 1000 / 60 / 60.0;
        DecimalFormat df = new DecimalFormat("#.0");
        String format = df.format(d);
        return  Float.valueOf(format);
    }

    /*
     * 将毫秒转换为分钟
     */
    public static int msToMint(long ms){
        long hours = ms / (1000 * 60 * 60);
//        long minutes = (ms-hours*(1000 * 60 * 60 ))/(1000* 60);
        long minutes = ms/1000/60;
        return (int)minutes;
    }

    /*
     * 将分钟转换为毫秒
     */
    public static long mintToMs(int mint) throws Exception{

        long ms = mint * 60 * 1000;

        return ms;
    }

    /*
     * 将毫秒转换为秒（保留一位）
     */
    public static Float msToSc(long ms) throws Exception{
        double d = ms / 1000.0;
        DecimalFormat df = new DecimalFormat("#.0");
        String format = df.format(d);
        return  Float.valueOf(format);
    }

    /**
     * 当前时间（时分秒）
     * @return
     */
    public static String currHms(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 获取日期时间中的时分秒
     * @param date
     * @return
     */
    public static String getHms(Date date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 比较两个时间（时分秒）大小
     * @param t1
     * @param t2
     * @return
     */
    public static boolean compareHmsLtet(String t1,String t2) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        try {
            Date dt1 = df.parse(t1);//将字符串转换为date类型
            Date dt2 = df.parse(t2);
            if(dt1.getTime()<=dt2.getTime())//比较时间大小,如果dt1大于dt2
            {
                return true;
            }
            else {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 两个时分秒相减转换成毫秒
     * @param t1
     * @param t2
     * @return
     */
    public static long twoHmsMinusToMs(String t1,String t2) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        try {
            Date dt1 = df.parse(t1);//将字符串转换为date类型
            Date dt2 = df.parse(t2);
            return dt1.getTime()-dt2.getTime();//返回毫秒数
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 两个时分秒相减转换成分钟
     * @param t1
     * @param t2
     * @return
     */
    public static int twoHmsMinusToMin(String t1,String t2) {
        boolean ltet = compareHmsLtet(t1, t2);
        if(ltet){
            t1=Integer.valueOf(t1.split(":")[0])+24+":"+t1.split(":")[1]+":"+t1.split(":")[2];
        }
        DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        try {
            Date dt1 = df.parse(t1);//将字符串转换为date类型
            Date dt2 = df.parse(t2);
            long ms=dt1.getTime()-dt2.getTime();//返回毫秒数
            return msToMint(ms);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        int i = twoHmsMinusToMin("01:01:00", "22:01:00");
        System.out.println(i);
    }

}
