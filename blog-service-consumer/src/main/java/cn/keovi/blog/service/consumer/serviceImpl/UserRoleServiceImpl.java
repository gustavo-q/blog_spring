package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.keovi.blog.service.consumer.service.RoleMenuService;
import cn.keovi.crm.dto.RoleDto;
import cn.keovi.crm.po.RoleMenu;
import cn.keovi.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.UserRole;
import cn.keovi.blog.service.consumer.mapper.UserRoleMapper;
import cn.keovi.blog.service.consumer.service.UserRoleService;
/**
 * @ClassName UserRoleServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{


    @Autowired
    RoleMenuService roleMenuService;


    //获取角色权限
    @Override
    public List<RoleDto> getRoles() {
        //获取role列表
        List<UserRole> list = lambdaQuery().eq(UserRole::getIsDelete, 0).list();
        if (CollectionUtil.isEmpty(list)) throw new ServiceException("获取失败");
        List<RoleDto> roleDtoList = new ArrayList<>();
        //循环加入menus
        for (UserRole userRole:list){
            RoleDto roleDto = new RoleDto();
            roleDto.setId(userRole.getId());
            roleDto.setName(userRole.getName());
            roleDto.setDescription(userRole.getDescription());
            List<RoleMenu> roleMenuList = roleMenuService.lambdaQuery().eq(RoleMenu::getRoleId, userRole.getId()).list();
            if (CollectionUtil.isEmpty(roleMenuList)){
                roleDtoList.add(roleDto);
                continue;
            }
            roleDto.setMenus(roleMenuList.stream().map(RoleMenu::getMenu).collect(Collectors.toList()));
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}
