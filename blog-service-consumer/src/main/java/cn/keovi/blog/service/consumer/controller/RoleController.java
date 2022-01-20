package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.UserRoleService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RoleController
 * @Description 角色
 * @Author gustavo
 * @Date 2022/01/20/13:26
 */

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    UserRoleService roleService;


    //获取用户角色
    @GetMapping("/getRoles")
    public Result getRoles(){
        try{
            List<UserRole> list = roleService.lambdaQuery().eq(UserRole::getIsDelete, 0).list();
            return Result.ok().data(200,list);
        }catch (Exception e){
            log.error("获取角色失败",e);
            return Result.error(500,e.getMessage());
        }
    }
}
