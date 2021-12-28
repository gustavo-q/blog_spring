package cn.keovi.constants;

public class RedisCacheConstans {
    public static final String CORTIKET="corTicket";

    public final static String SESSION_USER_TICKET_PREFIX = "session:user:ticket:";

    public final static String USER_PHONE_PREFIX = "userPhone:forgetPassword:";

    public final static  String getSessionUserTicketKey(String ticket){
    	return SESSION_USER_TICKET_PREFIX+ticket;
    }
    public final static  String getUserPhoneKey(String phone){
        return USER_PHONE_PREFIX+phone;
    }

    public static String  getTicket(String userId) {
        return userId + "_" + System.currentTimeMillis();
    }
}
