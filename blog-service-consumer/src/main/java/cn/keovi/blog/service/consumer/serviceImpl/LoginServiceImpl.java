package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.LoginService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.service.WxUserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.blog.service.consumer.session.UserSession;
import cn.keovi.constants.RedisCacheConstans;
import cn.keovi.constants.Result;
import cn.keovi.blog.service.consumer.converter.WxMappingJackson2HttpMessageConverter;
import cn.keovi.crm.dto.CurrentUserInfoDto;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import cn.keovi.crm.po.WxUser;
import cn.keovi.exception.ServiceException;
import cn.keovi.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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


    private final String AppSecret = "5b0c083b5c790efed8aebb34503707ac";
    private final String AppID = "wx45e193b0f51dbcaa";
    final String CORTICKET = "corTicket";
    final String avatar = "http://180.163.101.78:9000/public/8a2962fe1eaf4f4fb96c93c13c55210f_photo_2021-04-20_14-34-58.jpg";

    @Resource
    private RestTemplate restTemplate;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    UserService userService;

    @Autowired
    WxUserService wxUserService;


    @Autowired
    private LoginManager loginManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object login(JsonNode map) {
        //密码是否相同
        User us = userService.lambdaQuery().eq(User::getEmail, map.get("username").asText()).or()
                .eq(User::getUsername, map.get("username").asText()).one();
        if (us == null) {
            return Result.error(500, "邮箱或者用户名不存在");
        }
        if (!MD5Util.checkedPassword(map.get("password").asText(), us.getPassword())) return Result.error("密码错误");
        if (us.getStatus() == 1) return Result.error(500, "账号被禁用,请联系管理员");

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
                TimeUnit.HOURS);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("corTicket", corTicket);
        return Result.ok().data(200, hashMap);

    }


    //注册
    @Override
    public Result register(UserDto userDto) {
        if (userService.lambdaQuery()
                .eq(User::getEmail, userDto.getEmail()).or()
                .eq(User::getUsername, userDto.getUsername())
                .eq(User::getIsDelete, 0).count() > 0) {
            return Result.error(500, "邮箱或者账号存在");
        }
        System.out.println(redisTemplate.opsForValue().get(userDto.getEmail()));
        if (StringUtils.isBlank(redisTemplate.opsForValue().get(userDto.getEmail()))) return Result.error("验证码错误！");
        if (userDto.getEmailCode() != null && userDto.getEmailCode().equals(redisTemplate.opsForValue().get(userDto.getEmail()))) {
            User user = User.builder().username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(MD5Util.encrypt(userDto.getPassword()))
                    .createTime(DateUtil.date())
                    .roleId(3L)
                    .avatar(avatar)
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
        if (StringUtils.isBlank(RedisCacheConstans.getSessionUserTicketKey(corTicket)))
            throw new ServiceException("token不存在");
        if (!redisTemplate.delete(RedisCacheConstans.getSessionUserTicketKey(corTicket)))
            throw new ServiceException("退出失败");
    }


    @Override
    public Object wxlogin(JsonNode map) {
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        ResponseEntity<Object> objectResponseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/sns/jscode2session?appid={AppId}&secret={secret}&js_code={code}&grant_type=authorization_code",
                Object.class, AppID, AppSecret, map.get("code").asText());
        System.out.println(objectResponseEntity.getBody());
        Map<String, Object> objectMap = (Map<String, Object>) objectResponseEntity.getBody();
        Map<String, Object> result = new HashMap<>();
        if (ObjectUtil.isNotEmpty(objectMap.get("openid"))) {
            if (wxUserService.lambdaQuery().eq(WxUser::getOpenid, String.valueOf(objectMap.get("openid"))).count() > 0) {
                Long userId = wxUserService.lambdaQuery().eq(WxUser::getOpenid, objectMap.get("openid")).one().getUserId();
                if (userId == null) {
                    result.put("openid", String.valueOf(objectMap.get("openid")));
                    return Result.ok().data(200, result);
                }
                CurrentUserInfoDto currentUserInfoDto = userService.currentWxUserInfo(userId);
                BeanUtil.copyProperties(currentUserInfoDto, result);
                //save
//        String s=  getTicket(String.valueOf(us.getId()));
                String corTicket = getTicket(String.valueOf(userId));

                //session
                httpServletRequest.getSession().setAttribute(CORTICKET, corTicket);
                //cache
                redisTemplate.opsForValue().set(RedisCacheConstans.getSessionUserTicketKey(corTicket),
                        JSONObject.toJSONString(currentUserInfoDto),
                        1,
                        TimeUnit.HOURS);
                result.put("corTicket", corTicket);
                result.put("openid", String.valueOf(objectMap.get("openid")));
            } else {
                WxUser wxUser = new WxUser();
                wxUser.setOpenid(String.valueOf(objectMap.get("openid")));
                wxUserService.save(wxUser);
                result.put("openid", String.valueOf(objectMap.get("openid")));
                return Result.ok().data(200, result);
            }
        } else {
            return Result.error();
        }
        return Result.ok().data(200, result);
    }


    @Override
    @Transactional
    public Object bindEmail(JsonNode map) {


        String email = map.get("email").asText();
        String code = map.get("code").asText();
        String openid = map.get("openid").asText();


        if (!redisTemplate.opsForValue().get(email).equals(code)) return Result.error(500, "验证码错误");
        if (userService.lambdaQuery().eq(User::getEmail, email).eq(User::getIsDelete, 0).count() > 0) {
            wxUserService.lambdaUpdate().set(WxUser::getUserId, userService.lambdaQuery().eq(User::getEmail, email).eq(User::getIsDelete, 0).one().getId())
                    .eq(WxUser::getOpenid, openid).update();
        } else {
            User user = new User();
            user.setPassword(MD5Util.encrypt("123456"));
            user.setCreateTime(new Date());
            user.setEmail(email);
            userService.save(user);

            wxUserService.lambdaUpdate().set(WxUser::getUserId, user.getId()).eq(WxUser::getOpenid, openid).update();
        }
        return Result.ok(200, "绑定成功");
    }


}
