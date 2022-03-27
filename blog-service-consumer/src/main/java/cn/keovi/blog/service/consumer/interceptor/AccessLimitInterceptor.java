package cn.keovi.blog.service.consumer.interceptor;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.NetUtil;
import cn.keovi.annotation.AccessLimit;
import cn.keovi.constants.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author Ryan
 * @Description: 访问控制拦截器
 *
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            //方法上没有访问控制的注解，直接通过
            if (accessLimit == null) {
                return true;
            }
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String ip = NetUtil.getLocalhostStr();
                String method = request.getMethod();
                String requestURI = request.getRequestURI();
                String redisKey = ip + ":" + method + ":" + requestURI;

                if (redisTemplate.opsForValue().get(redisKey) == null) {
                    //在规定周期内第一次访问，存入redis
                    redisTemplate.opsForValue().set(redisKey, "1",
                            seconds,
                            TimeUnit.SECONDS);
                } else {
                    if (Integer.valueOf(redisTemplate.opsForValue().get(redisKey)) >= maxCount) {
                        //超出访问限制次数
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(accessLimit.msg());
                        out.flush();
                        out.close();
                        return false;
                    } else {
                        //没超出访问限制次数
                        redisTemplate.opsForValue().set(redisKey,
                                String.valueOf(Integer.valueOf(redisTemplate.opsForValue().get(redisKey))+1),
                                seconds,
                                TimeUnit.SECONDS);
                    }
                }

        }
        return true;
    }


}

