package cn.keovi.blog.service.consumer.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.MenuService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.dto.CurrentUserInfoDto;
import cn.keovi.crm.po.User;

import cn.keovi.exception.ServiceException;
import cn.keovi.utils.MD5Util;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName UserController
 * @Description 用户
 * @Author gustavo
 * @Date 2021/12/27/22:33
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    MenuService menuService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    //用户列表
    @PostMapping("/pageList")
    public Object pageList(@RequestBody BaseDto baseDto) {
        try {
            Result result = userService.findList(baseDto);
            return result;
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //详情编辑页面
    @GetMapping("/userDetails/{id}")
    public Object userDetails(@PathVariable long id) {
        try {
            User userList = userService.userDetails(id);
            return Result.ok().data(200, userList);
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //删除用户
    @GetMapping("/deleteUser")
    public Object deleteUser(@RequestParam long id) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (userService.lambdaUpdate().set(User::getIsDelete, 1).set(User::getLastUpdateTime, new Date())
                    .set(User::getLastUpdateBy, loginManager.getUserId()).eq(User::getId, id).update()) {
                log.info("删除成功,用户{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            log.error("删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //修改用户
    @PostMapping("/editUser")
    public Object editUser(@RequestBody User user) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            User user1 = userService.lambdaQuery().eq(User::getId, user.getId()).one();
            if (!user1.getUsername().equals(user.getUsername()) || !user.getEmail().equals(user1.getEmail())) {
                if (userService.lambdaQuery().eq(User::getUsername, user.getUsername()).or()
                        .eq(User::getEmail, user.getEmail()).eq(User::getIsDelete, 0).count() > 0) {
                    return Result.error("用户名或者邮箱已存在");
                }
            }
            if (user.getUsername().isEmpty() || user.getEmail().isEmpty()) {
                return Result.error("用户名或者邮箱不能为空");
            }
            if (userService.lambdaUpdate()
                    .set(User::getUsername, user.getUsername())
                    .set(User::getEmail, user.getEmail())
                    .set(User::getRoleId, user.getRoleId())
                    .set(User::getMobile, user.getMobile())
                    .set(User::getQq, user.getQq())
                    .set(User::getSex, user.getSex())
                    .set(User::getIntro, user.getIntro())
                    .set(User::getStatus, user.getStatus())
                    .set(User::getWechat, user.getWechat())
                    .set(User::getLastUpdateBy, loginManager.getUserId())
                    .eq(User::getIsDelete, 0).eq(User::getId, user.getId()).update()) {
                log.info("更新成功,用户{}", user);
                return Result.ok("更新成功！");
            }
            return Result.error("更新失败！");
        } catch (Exception e) {
            log.error("更新失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //新增用户
    @PostMapping("/saveUser")
    public Object saveUser(@RequestBody User user) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (userService.lambdaQuery().eq(User::getUsername, user.getUsername()).or()
                    .eq(User::getEmail, user.getEmail()).eq(User::getIsDelete, 0).count() > 0) {
                return Result.error("用户名或者邮箱已存在");
            }

            user.setCreateTime(new Date());
            //默认密码
            user.setPassword(MD5Util.encrypt("123456"));
            if (userService.save(user)) {
                log.info("更新成功,用户{}", user);
                return Result.ok("更新成功！");
            }
            return Result.error("更新失败！");
        } catch (Exception e) {
            log.error("更新失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //修改用户状态
    @PutMapping("/editStatus/{id}")
    public Object editStatus(@PathVariable Long id) {
        try {
            User user = userService.lambdaQuery().eq(User::getId, id).one();
            if (userService.lambdaUpdate()
                    .set(User::getStatus, user.getStatus() == 1 ? 2 : 1)
                    .eq(User::getId, user.getId()).update()) {
                log.info("更新状态成功,用户{}", user);
                return Result.ok("更新状态成功！");
            }
            return Result.error("更新状态失败！");
        } catch (Exception e) {
            log.error("更新状态失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //获取该用户的信息
    @PostMapping("/getCurrentUserInfo")
    public Object getCurrentUserInfo() {
        try {
            CurrentUserInfoDto currentUserInfoDto = userService.currentUserInfo();
            return Result.ok().data(200, currentUserInfoDto);
        } catch (Exception e) {
            log.error("获取该用户的信息失败", e);
            return Result.error(500, e.getMessage());
        }

    }


    //修改用户基本信息
    @PostMapping("/editPersonal")
    public Object editPersonal(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (userService.lambdaUpdate()
                    .set(User::getMobile, map.get("mobile").asInt())
                    .set(User::getQq, map.get("qq").asInt())
                    .set(User::getSex, map.get("sex").asInt())
                    .set(User::getWechat, map.get("wechat").asText())
                    .set(User::getIntro, map.get("intro").asText())
                    .set(User::getLastUpdateTime, new Date())
                    .set(User::getLastUpdateBy, loginManager.getUserId())
                    .eq(User::getIsDelete, 0).eq(User::getId, loginManager.getUserId()).update()) {
                return Result.ok("更新成功！");
            }
            return Result.error("更新失败！");
        } catch (Exception e) {
            log.error("更新失败!", e);
            return Result.error(500, e.getMessage());
        }

    }


    /**
     * @Author gustavo  2022/1/20 11:10
     **/
    @GetMapping("/resetPas")
    public Object resetPas(@RequestParam int id) {
        try {
            userService.resetPas(id);
            return Result.ok(200, "重置密码成功");
        } catch (Exception e) {
            log.error("重置密码失败", e);
            return Result.error(500, e.getMessage());
        }
    }


    //修改密码
    @PostMapping("/editPass")
    public Object editPass(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserSession() == null) return Result.error(401, "登录失效！");
            if (!map.get("newPass").asText().equals(map.get("agreePass").asText()))
                throw new ServiceException("两次输入新密码不一致!");
            User user = userService.lambdaQuery().eq(User::getId, loginManager.getUserId()).eq(User::getIsDelete, 0).one();
            if (ObjectUtil.isEmpty(user)) throw new ServiceException("未知错误!");
            if (!MD5Util.checkedPassword(map.get("oldPass").asText(), user.getPassword()))
                return Result.error("旧密码错误!");
            boolean update = userService.lambdaUpdate().set(User::getPassword, MD5Util.encrypt(map.get("newPass").asText()))
                    .set(User::getLastUpdateBy, loginManager.getUserId())
                    .set(User::getLastUpdateTime, new Date())
                    .eq(User::getId, loginManager.getUserId()).update();
            if (!update) return Result.error("修改密码失败!");
            return Result.ok(200, "修改密码成功!");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return Result.error(500, e.getMessage());
        }
    }

    //验证username
    @GetMapping("/verifyname")
    public Object verifyname(@RequestParam String username) {
        try {
            System.out.println(username);
            if (userService.lambdaQuery().eq(User::getUsername, username).count() > 0) {
                return Result.error(500, "用户名已存在");
            }
            return Result.ok(200, "验证成功");
        } catch (Exception e) {
            log.error("验证用户名失败", e);
            return Result.error(500, e.getMessage());
        }
    }

    //验证email
    @GetMapping("/verifyemail")
    public Object verifyemail(@RequestParam String email) {
        try {
            System.out.println(email);
            if (userService.lambdaQuery().eq(User::getEmail, email).count() > 0) {
                return Result.error(500, "邮箱已存在");
            }
            return Result.ok(200, "验证成功");
        } catch (Exception e) {
            log.error("验证邮箱失败", e);
            return Result.error(500, e.getMessage());
        }
    }


    //修改密码
    @PostMapping("/forgetPassword")
    @IgnoreAuth
    public Object forgetPassword(@RequestBody JsonNode map) {
        try {
            User user = userService.lambdaQuery().eq(User::getEmail, map.get("email").asText())
                    .eq(User::getIsDelete, 0)
                    .eq(User::getUsername, map.get("userName").asText()).eq(User::getIsDelete, 0).one();
            if (ObjectUtil.isEmpty(user)) return Result.error(500, "用户名或者邮箱错误!");
            String code = redisTemplate.opsForValue().get(user.getEmail());
            if (StringUtils.isBlank(code)) return Result.error(500, "验证码无效!");
            if (!code.equals(map.get("mailCode").asText())) return Result.error(500, "验证码错误!");
            user.setPassword(MD5Util.encrypt(map.get("newPassword").asText()));
            boolean update = userService.updateById(user);
            if (!update) return Result.error("修改密码失败!");
            return Result.ok(200, "修改密码成功!");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return Result.error(500, e.getMessage());
        }
    }


}
