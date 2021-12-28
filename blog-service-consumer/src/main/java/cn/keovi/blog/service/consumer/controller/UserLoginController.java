package cn.keovi.blog.service.consumer.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.keovi.blog.service.consumer.service.EmailService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.constants.RedisCacheConstans;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import cn.keovi.constants.Result;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static cn.keovi.constants.RedisCacheConstans.getTicket;


/**
 * @ClassName UserLoginController
 * @Description 用户
 * @Author gustavo
 * @Date 2021/12/24/22:05
 */

@RestController
@RequestMapping("/userLogin")
public class UserLoginController extends BaseController{

    protected Logger logger = LoggerFactory.getLogger(UserLoginController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    RedisTemplate redisTemplate;




    //邮箱登录
    @PostMapping("/login")
    public Result Login(@RequestBody JsonNode map) {
        try {
            return userService.login(map);
        }catch (Exception e){
            logger.error("登录失败!",e);
            return Result.error(500,e.getMessage());

        }

    }


    //账号注册
    @PostMapping("/register")
    public Object register(@RequestBody UserDto userDto){
        try{
            return userService.register(userDto);
        }catch (Exception e){
            logger.error("注册失败!",e);
            return Result.error(500,e.getMessage());
        }
    }


    //发送邮箱验证码
    @PostMapping("/sendEmail")
    public Object sendEmail(@RequestBody JsonNode map){
       try{
           String email = map.get("email").asText();
           if (Validator.isEmail(email)){
               emailService.sendSimpleMail(email);
           }else {
               return Result.error(500,"邮箱不正确！");
           }
           return Result.ok(200,"发送成功！");
       }catch (Exception e){
           logger.error("邮件发送失败！",e);
           return Result.error(500,e.getMessage());
       }
    }

    /**
    *@Author gustavo  2021/12/27 15:11
    * 生成验证码
    **/
    @GetMapping("/verification")
    public Object verification(HttpServletResponse response, HttpServletRequest request){
       try {
           request.getSession().removeAttribute("captchaCode");
           //定义图形验证码的长、宽、验证码字符数、干扰线宽度
           ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
           //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
           //图形验证码写出，可以写出到文件，也可以写出到流
           captcha.write(response.getOutputStream());
           //存入session
           request.getSession().setAttribute("captchaCode",captcha.getCode());
       }catch (Exception e){
           logger.error("验证码生成失败!",e);
           return Result.error(500,e.getMessage());
       }
        return null;
    }


    /**
     *@Author gustavo  2021/12/27 15:11
     * 获取验证码
     **/
    @GetMapping("/getVerification")
    public Object getVerification(@RequestParam String code){
        try {
            String captchaCode = request.getSession().getAttribute("captchaCode").toString();
            if(code.equals(captchaCode)){
                return Result.ok();
            }
            return Result.error(500,"验证码错误！");
        }catch (Exception e){
            logger.error("验证码错误!",e);
            return Result.error(500,e.getMessage());
        }
    }




}
