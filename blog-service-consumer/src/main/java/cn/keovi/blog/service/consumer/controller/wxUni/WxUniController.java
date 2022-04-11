package cn.keovi.blog.service.consumer.controller.wxUni;

import cn.keovi.blog.service.consumer.service.LoginService;
import cn.keovi.constants.Result;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName WxUniController
 * @Description wx小程序
 * @Author gustavo
 * @Date 2022/04/10/21:03
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class WxUniController {

    @Autowired
    private LoginService loginService;

    //wx小程序登录
    @PostMapping("/login")
    public Object Login(@RequestBody JsonNode map) {
        try {
            Object result = loginService.wxlogin(map);
            return result;
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());

        }

    }

    //绑定邮箱
    @PostMapping("/bindEmail")
    public Object bindEmail(@RequestBody JsonNode map) {
        try {
            Object result = loginService.bindEmail(map);
            return result;
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


}
