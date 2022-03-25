package cn.keovi.blog.service.consumer.interceptor;

import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.session.UserSession;
import cn.keovi.constants.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    public static final String LOGIN_TOKEN_KEY = "corTicket";


	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);

        /**
         * 不需要验证权限的方法直接放过
         */
        if(annotation!=null) {
        	return true;
        }

        if(StringUtils.isNotBlank(token)) {
            return true;
        }

		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
		    writer = response.getWriter();
		    writer.print(JSONObject.toJSONString(Result.error(401, "请先登录")));
		} finally {
		    if(writer != null){
		        writer.close();
		    }
		}
//				throw new EIException("请先登录", 401);
		return false;
    }
}
