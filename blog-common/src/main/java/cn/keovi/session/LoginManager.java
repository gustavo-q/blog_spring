package cn.keovi.session;

import cn.keovi.constants.RedisCacheConstans;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginManager
 * @Description
 * @Author gustavo
 * @Date 2021/12/26/22:15
 */
public class LoginManager {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static LoginManager loginManager;


    @PostConstruct
    public void init() {
        loginManager = this;
        loginManager.redisTemplate = this.redisTemplate;
    }

    public static String getTicket(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ticket = request.getHeader("corTicket");
        ticket = ticket != null ? ticket : (String)request.getAttribute("corTicket");

        if (ticket == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                for (Cookie cookie :cookies  ) {
                    if (cookie.getName().equals("gwticket")) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return ticket;
    }



    /**
     * 返回登录用户实体
     * @return
     * @see
     */
    public static UserSession getUserSession() {
        String json = loginManager.redisTemplate.opsForValue().get(RedisCacheConstans.getSessionUserTicketKey(getTicket()));
        return json==null?null: JSONObject.parseObject(json, UserSession.class);
    }

    /**
     * 清除用户实体缓存
     *
     * @author gustavo
     * @date
     * @see
     */
    public static void clearUserSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().removeAttribute("corTicket");
    }

    /**
     * 获取用户信息
     * @see
     */
    public static Long getUserId() {
        UserSession userSession = getUserSession();
        if(userSession != null){
            return userSession.getId();
        }
        return null;
    }
}
