package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.MenuService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.blog.service.consumer.session.UserSession;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.dto.CurrentUserInfoDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.User;

import cn.keovi.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


    //用户列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<User> userList = userService.findList(baseDto);
//            ArrayList<Map<String, Object>> maps=new ArrayList<>();
//            userList.forEach(user -> {
//                Map<String, Object> map = BeanUtil.beanToMap(user);
//                map.put("article",articleService.lambdaQuery().eq(Article::getIsDelete,0).count());
//                maps.add(map);
//            });
            long count = userService.lambdaQuery().like(User::getEmail, baseDto.getKeyword()).or().like(User::getUsername, baseDto.getKeyword()).count();
            return Result.ok().data200(userList, count);
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
            return Result.ok().data(200,userList);
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //删除用户
    @PutMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable long id) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (userService.lambdaUpdate().set(User::getIsDelete, 1).set(User::getLastUpdateTime,new Date())
                    .set(User::getLastUpdateBy,loginManager.getUserId()).eq(User::getId, id).update()) {
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
    public Result editUser(@RequestBody User user) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (userService.lambdaUpdate()
                    .set(User::getUsername, user.getUsername())
                    .set(User::getPassword, SecureUtil.md5(SecureUtil.md5(user.getPassword() + user.getEmail())))
                    .set(User::getEmail, user.getEmail())
                    .set(User::getMobile, user.getMobile())
                    .set(User::getQq, user.getQq())
                    .set(User::getSex,user.getSex())
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



    //获取该用户的信息
    @PostMapping("/getCurrentUserInfo")
    public Result getCurrentUserInfo(){
        try{
            CurrentUserInfoDto currentUserInfoDto = userService.currentUserInfo();
            return Result.ok().data(200,currentUserInfoDto);
        }catch (Exception e){
            log.error("获取该用户的信息失败",e);
            return Result.error(500,e.getMessage());
        }

    }


    //修改用户基本信息
    @PostMapping("/editPersonal")
    public Result editPersonal(@RequestBody JsonNode map){
        try{
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (userService.lambdaUpdate()
                    .set(User::getUsername, map.get("username").asText())
                    .set(User::getMobile, map.get("mobile").asInt())
                    .set(User::getQq,map.get("qq").asInt())
                    .set(User::getSex,map.get("sex").asInt())
                    .set(User::getWechat, map.get("wechat").asText())
                    .set(User::getIntro,map.get("intro").asText())
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


 



}
