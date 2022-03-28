package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.blog.service.consumer.service.UserRoleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.crm.dto.RoleDto;
import cn.keovi.crm.po.UserRole;
import cn.keovi.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.RoleMenu;
import cn.keovi.blog.service.consumer.mapper.RoleMenuMapper;
import cn.keovi.blog.service.consumer.service.RoleMenuService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName RoleMenuServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/25/20:57
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private UserRoleService userRoleService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoles(RoleDto roledto) {
        if (loginManager.getUserId()==null) throw new ServiceException("登录失效");
        UserRole userRole = UserRole.builder()
                .id(roledto.getId())
                .name(roledto.getName())
                .description(roledto.getDescription())
                .lastUpdateBy(loginManager.getUserId())
                .lastUpdateTime(new Date())
                .build();
        userRoleService.updateById(userRole);
        lambdaUpdate().eq(RoleMenu::getRoleId, roledto.getId()).remove();
        for (String menu : roledto.getMenus()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRoleId(roledto.getId());
            save(roleMenu);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoles(RoleDto roledto) {
        if (loginManager.getUserId()==null) throw new ServiceException("登录失效");
        UserRole userRole =UserRole.builder()
                .name(roledto.getName())
                .description(roledto.getDescription())
                .createTime(new Date())
                .createBy(loginManager.getUserId())
                .build();
        userRoleService.save(userRole);
        for (String menu : roledto.getMenus()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRoleId(roledto.getId());
            save(roleMenu);
        }
    }


    @Override
    public void deleteRoles(JsonNode id) {
        userRoleService.lambdaUpdate().set(UserRole::getIsDelete,1).eq(UserRole::getId,id).update();
        lambdaUpdate().eq(RoleMenu::getRoleId, id).remove();

    }
}
