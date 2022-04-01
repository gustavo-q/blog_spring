package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.UserDonateService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.UserDonate;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @ClassName DonateController
 * @Description 赞赏码
 * @Author gustavo
 * @Date 2022/04/01/19:45
 */

@Slf4j
@RestController
@RequestMapping("/donate")
public class DonateController {

    @Autowired
    UserDonateService userDonateService;

    @Autowired
    private LoginManager loginManager;

    //新增或修改赞赏码
    @PostMapping("/editDonateCode")
    public Object editDonateCode(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserSession() == null) return Result.error(401, "登录失效！");
            //用户头像
            UserDonate userDonate = new UserDonate();
            userDonate.setDonateJson(map.get("url").asText());
            UserDonate donate = userDonateService.lambdaQuery().eq(UserDonate::getCreateBy, loginManager.getUserId()).one();
            if (ObjectUtil.isNotEmpty(donate)) {
                userDonate.setLastUpdateBy(loginManager.getUserId());
                userDonate.setLastUpdateTime(new Date());
                userDonate.setId(donate.getId());
            } else {
                userDonate.setCreateBy(loginManager.getUserId());
                userDonate.setCreateTime(new Date());
            }
            userDonateService.saveOrUpdate(userDonate);
            return Result.ok(200, "赞赏码更新成功");
        } catch (Exception e) {
            log.error("赞赏码更新失败", e);
            return Result.error(500, e.getMessage());
        }
    }


    //新增或修改赞赏码
    @GetMapping("/getDonateCode")
    public Object getDonateCode() {
        try {
            if (loginManager.getUserSession() == null) return Result.error(401, "登录失效！");
            //用户头像
            UserDonate donate = userDonateService.lambdaQuery().eq(UserDonate::getCreateBy, loginManager.getUserId()).one();
            if (ObjectUtil.isEmpty(donate))     return Result.ok();
            return Result.ok().data(200,donate);
        } catch (Exception e) {
            log.error("赞赏码更新失败", e);
            return Result.error(500, e.getMessage());
        }
    }


}
