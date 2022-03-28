package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.keovi.blog.service.consumer.service.LoginService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.blog.service.consumer.session.UserSession;
import cn.keovi.constants.RedisCacheConstans;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import cn.keovi.exception.ServiceException;
import cn.keovi.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
        //密码是否相同
        User us = userService.lambdaQuery().eq(User::getEmail, map.get("username").asText()).or()
                .eq(User::getUsername,map.get("username").asText()).one();
        if (us == null) {
            return Result.error(500, "邮箱或者用户名不存在");
        }
        if (!MD5Util.checkedPassword(map.get("password").asText(),us.getPassword())) return Result.error("密码错误");
        if (us.getStatus()==1)   return Result.error(500, "账号被禁用,请联系管理员");

        //将登录信息传入redis
        UserSession userSession = UserSession.builder()
                .id(us.getId())
                .roleId(us.getRoleId())
                .userName(us.getUsername())
                .email(us.getEmail())
                .qq(us.getQq())
                .sex(us.getSex())
                .wechat(us.getWechat())
                .mobile(us.getMobile())
                .intro(us.getIntro())
                .avatar(us.getAvatar())
                .build();

        //save
//        String s=  getTicket(String.valueOf(us.getId()));
        String corTicket = getTicket(String.valueOf(us.getId()));

        //session
        httpServletRequest.getSession().setAttribute(CORTICKET, corTicket);
        //cache
        redisTemplate.opsForValue().set(RedisCacheConstans.getSessionUserTicketKey(corTicket),
                JSONObject.toJSONString(userSession),
                1,
                TimeUnit.DAYS);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("corTicket",corTicket);
        return Result.ok().data(200,hashMap);

    }


    //注册
    @Override
    public Result register(UserDto userDto) {
        if (userService.lambdaQuery()
                .eq(User::getEmail, userDto.getEmail())
                .eq(User::getUsername,userDto.getUsername())
                .eq(User::getIsDelete, 0).count() > 0) {
            return Result.error(500, "邮箱或者账号存在");
        }
        if (redisTemplate.opsForValue().get(userDto.getEmail()) == null) {
            return Result.error("注册失败！");
        }
        if (userDto.getEmailCode()!=null && userDto.getEmailCode().equals(redisTemplate.opsForValue().get(userDto.getEmail()))) {
            User user = User.builder().username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(MD5Util.encrypt(userDto.getPassword()))
                    .createTime(DateUtil.date())
                    .roleId(2)
                    .build();
            userService.save(user);
            return Result.ok("注册成功");
        }
        return Result.error();
    }

    @Override
    public void logout() {
        String corTicket = LoginManager.getTicket();
        httpServletRequest.getSession().removeAttribute(CORTICKET);
        //cache
        if (StringUtils.isBlank(RedisCacheConstans.getSessionUserTicketKey(corTicket))) throw new ServiceException("token不存在");
        if (!redisTemplate.delete(RedisCacheConstans.getSessionUserTicketKey(corTicket))) throw new ServiceException("退出失败");
    }


}
