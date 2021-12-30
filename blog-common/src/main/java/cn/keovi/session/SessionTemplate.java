package cn.keovi.session;

import cn.keovi.constants.RedisCacheConstans;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class SessionTemplate {
	
	@Resource
	private  RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private  HttpServletRequest httpServletRequest;
	   
	String CORTICKET = "corTicket";
	/*
	public String getTicket(){
		String corTicket = (corTicket = httpServletRequest.getHeader(CORTICKET)) == null ?
				httpServletRequest.getParameter(CORTICKET):corTicket;
        return corTicket= corTicket != null ? corTicket
                : (String)httpServletRequest.getSession().getAttribute(CORTICKET);
	}*/
	
	public String getTicket(){
		String corTicket = (corTicket = httpServletRequest.getHeader(CORTICKET)) == null ?
				httpServletRequest.getParameter(CORTICKET):corTicket;
	    corTicket= (corTicket != null ? corTicket
		                : (String)httpServletRequest.getSession().getAttribute(CORTICKET));
	    
	    if (corTicket==null) {
	    	Cookie[] cookies = httpServletRequest.getCookies();
	    	if (cookies!=null) {
	    		for (Cookie cookie :cookies  ) {
		    		if (cookie.getName().equals("gwticket")) {
		    			return cookie.getValue();
		    		}
		    	}
	    	}
	    }	
        return corTicket;
	}

	public UserSession getUserSession(){
		String json = redisTemplate.opsForValue().get(RedisCacheConstans.getSessionUserTicketKey(getTicket()));
		return json==null?null:JSONObject.parseObject(json,UserSession.class);
	}


	public UserSession getUserSessionByTicket(String ticket){
		String json = redisTemplate.opsForValue().get(RedisCacheConstans.getSessionUserTicketKey(ticket));
		return json==null?null:JSONObject.parseObject(json, UserSession.class);
	}


	public void saveUserSession(String ticket, UserSession userSession){
		
		//save
		httpServletRequest.getSession().setAttribute(CORTICKET, ticket);
		
		//cache
		redisTemplate.opsForValue().set(RedisCacheConstans.getSessionUserTicketKey(ticket), 
				JSONObject.toJSONString(userSession),
				365,
				TimeUnit.DAYS);
	}

	public void deleteUserSession(String ticket){
		String redisKey = RedisCacheConstans.getSessionUserTicketKey(ticket);
		//save
//		httpServletRequest.getSession().setAttribute(CORTICKET, "");
//		redisTemplate.boundHashOps(ticket).delete(ticket);
		//cache
//		redisTemplate.delete(redisKey);
		redisTemplate.delete("session:user:ticket:" + ticket);
	}

	public void deleteTicket(String userId){
		redisTemplate.boundHashOps(RedisCacheConstans.CORTIKET).delete(userId);//删除
	}

	public Long getUserId(){
		return getUserSession().getId();
	}
}



