package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserService userService;

    @Autowired
    UserMapper userMapper;


    //用户列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<User> userList = userService.findList(baseDto);
            ArrayList<Map<String, Object>> maps=new ArrayList<>();
            userList.forEach(user -> {
                Map<String, Object> map = BeanUtil.beanToMap(user);
                map.put("article",5);
                maps.add(map);
            });
            long count = userService.lambdaQuery().like(User::getEmail, baseDto.getKeyword()).or().like(User::getUsername, baseDto.getKeyword()).count();
            return Result.ok().data200(maps, count);
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
            return Result.ok().data(userList);
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //删除用户
    @PutMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable long id) {
        try {
            if (userService.lambdaUpdate().set(User::getIsDelete, 1).eq(User::getId, id).update()) {
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
    @PutMapping("/editUser")
    public Result editUser(@RequestBody User user) {
        try {
            if (userService.lambdaUpdate()
                    .set(User::getUsername, user.getUsername())
                    .set(User::getPassword, SecureUtil.md5(SecureUtil.md5(user.getPassword() + user.getEmail())))
                    .set(User::getEmail, user.getEmail())
                    .set(User::getMobile, user.getMobile())
                    .set(User::getQq, user.getQq())
                    .set(User::getWechat, user.getWechat())
                    .set(User::getLastUpdateBy, "")
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


    //修改用户状态
    @PutMapping("/editStatus/{id}")
    public Result editStatus(@PathVariable Long id) {
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




}
