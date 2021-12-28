package cn.keovi.utils;

public class IntegerUtil {
    public static Integer currentStampKeep10(){
        return Integer.valueOf(String.valueOf(System.currentTimeMillis()).substring(0,10));
    }
}
