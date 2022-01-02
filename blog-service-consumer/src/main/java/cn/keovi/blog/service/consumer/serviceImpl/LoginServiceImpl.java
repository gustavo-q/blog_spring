package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.keovi.blog.service.consumer.service.LoginService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.UserSession;
import cn.keovi.constants.RedisCacheConstans;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static cn.keovi.constants.RedisCacheConstans.getTicket;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author gustavo
 * @Date 2021/12/28/17:53
 */
@Service
public class LoginServiceImpl implements LoginService {


    String CORTICKET = "corTicket";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    UserService userService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result login(JsonNode map) {
        //邮箱是否存在
        Long count = userService.lambdaQuery()
                .eq(User::getEmail, map.get("email").asText()).count();
        if (count == 0) {
            return Result.error(500, "邮箱不存在");
        }
        String password = SecureUtil.md5(SecureUtil.md5(map.get("password").asText()+map.get("email").asText()));
        //密码是否相同
        User us = userService.lambdaQuery().eq(User::getEmail, map.get("email").asText())
                .eq(User::getPassword, password).one();
        if (us == null) {
            return Result.error(500, "密码错误");
        }

        //将登录信息传入redis
        UserSession userSession = UserSession.builder()
                .id(us.getId())
                .roleId(us.getRoleId())
                .userName(us.getUsername())
                .email(us.getEmail())
                .qq(us.getQq())
                .wechat(us.getWechat())
                .mobile(us.getMobile())
                .build();

        //save
//        String s=  getTicket(String.valueOf(us.getId()));
//        httpServletRequest.getSession().setAttribute(CORTICKET, getTicket(String.valueOf(us.getId())));

        //cache
        redisTemplate.opsForValue().set(RedisCacheConstans.getSessionUserTicketKey(getTicket(String.valueOf(us.getId()))),
                JSONObject.toJSONString(userSession),
                365,
                TimeUnit.DAYS);

        return Result.ok(200, "登录成功！");

    }


    //注册
    @Override
    public Result register(UserDto userDto) {
        if (userService.lambdaQuery()
                .eq(User::getEmail, userDto.getEmail()).eq(User::getIsDelete, 0).count() > 0) {
            return Result.error(500, "邮箱存在");
        }
        if (redisTemplate.opsForValue().get(userDto.getEmail()) == null) {
            return Result.error("注册失败！");
        }
        if (userDto.getEmailCode()!=null && userDto.getEmailCode().equals(redisTemplate.opsForValue().get(userDto.getEmail()))) {
            User user = User.builder().username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(SecureUtil.md5(SecureUtil.md5(userDto.getPassword()+userDto.getEmail())))
                    .createTime(DateUtil.date())
                    .roleId(2)
                    .build();
            userService.save(user);
            return Result.ok("注册成功");
        }
        return Result.error();
    }


}
