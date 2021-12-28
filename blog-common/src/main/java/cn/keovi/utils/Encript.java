package cn.keovi.utils;


public class Encript {
    public static final String DEFAULT_PASSWORD = "12345678";
	
	private static String encode(String password) throws Exception{
		return MD5Util.encrypt(password);
	}
	
	public static String encode(String password,String salt) throws Exception{
		return MD5Util.encrypt(password+salt);
	}
	
	public static String getSalt() throws Exception{
		return MD5Util.encrypt(String.valueOf(System.currentTimeMillis()));
	}

}
