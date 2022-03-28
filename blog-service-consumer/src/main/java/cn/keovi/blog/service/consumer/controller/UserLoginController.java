package cn.keovi.blog.service.consumer.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.lang.Validator;
import cn.keovi.blog.service.consumer.service.EmailService;
import cn.keovi.blog.service.consumer.service.LoginService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.constants.Result;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @ClassName UserLoginController
 * @Description 用户
 * @Author gustavo
 * @Date 2021/12/24/22:05
 */

@Slf4j
@RestController
@RequestMapping("/userLogin")
public class UserLoginController extends BaseController{





    @Autowired
    private EmailService emailService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LoginService loginService;




    //邮箱登录
    @PostMapping("/login")
    public Object Login(@RequestBody JsonNode map) {
        try {
            return loginService.login(map);
        }catch (Exception e){
            log.error("登录失败!",e);
            return Result.error(500,e.getMessage());

        }

    }



    //邮箱登录
    @GetMapping("/logout")
    public Object logout() {
        try {
            loginService.logout();
            return Result.ok();
        }catch (Exception e){
            log.error("登出失败!",e);
            return Result.error(500,e.getMessage());

        }

    }


    //账号注册
    @PostMapping("/register")
    public Object register(@RequestBody UserDto userDto){
        try{
            return loginService.register(userDto);
        }catch (Exception e){
            log.error("注册失败!",e);
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
           log.error("邮件发送失败！",e);
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
           log.error("验证码生成失败!",e);
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
            log.error("验证码错误!",e);
            return Result.error(500,e.getMessage());
        }
    }




}
