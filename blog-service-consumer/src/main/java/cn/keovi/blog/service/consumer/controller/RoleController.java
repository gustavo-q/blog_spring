package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.keovi.blog.service.consumer.service.RoleMenuService;
import cn.keovi.blog.service.consumer.service.UserRoleService;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.RoleDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    RoleMenuService roleMenuService;


    //获取用户角色
    @GetMapping("/getRoles")
    public Object getRoles(){
        try{
            List<RoleDto> list = roleService.getRoles();
            return Result.ok().data(200,list);
        }catch (Exception e){
            log.error("获取角色失败",e);
            return Result.error(500,e.getMessage());
        }
    }


    //更新用户角色权限
    @PostMapping("/updateRoles")
    public Object updateRoles(@RequestBody RoleDto roledto){
        try{
            if (CollectionUtil.isEmpty(roledto.getMenus()))  return Result.ok("更新用户权限成功！");
            roleMenuService.updateRoles(roledto);
            return Result.ok("更新用户权限成功！");
        }catch (Exception e){
            log.error("更新用户权限失败",e);
            return Result.error(500,e.getMessage());
        }
    }

    //新增用户角色权限
    @PostMapping("/saveRoles")
    public Object saveRoles(@RequestBody RoleDto roledto){
        try{
            roleMenuService.saveRoles(roledto);
            return Result.ok("新增用户权限成功！");
        }catch (Exception e){
            log.error("新增用户权限失败",e);
            return Result.error(500,e.getMessage());
        }
    }

    //更新用户角色权限
    @GetMapping("/deleteRoles")
    public Object deleteRoles(@RequestBody JsonNode map){
        try{
            roleMenuService.deleteRoles(map.get("id"));
            return Result.ok("新增用户权限成功！");
        }catch (Exception e){
            log.error("新增用户权限失败",e);
            return Result.error(500,e.getMessage());
        }
    }
}
