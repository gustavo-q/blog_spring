package cn.keovi.blog.service.consumer.session;

import cn.keovi.constants.RedisCacheConstans;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public final class LoginManager {
    private static final Logger logger = LoggerFactory.getLogger(LoginManager.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private LoginManager loginManager;

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
    public UserSession getUserSession() {
        String json = loginManager.redisTemplate.opsForValue().get(RedisCacheConstans.getSessionUserTicketKey(getTicket()));
        return json==null?null:JSONObject.parseObject(json, UserSession.class);
    }

    /**
     * 清除用户实体缓存
     *
     * @author jianglingfeng
     * @date 2007-11-9
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
    public Long getUserId() {
        UserSession userSession = getUserSession();
        if(userSession != null){
            return userSession.getId();
        }
        return null;
    }



}
