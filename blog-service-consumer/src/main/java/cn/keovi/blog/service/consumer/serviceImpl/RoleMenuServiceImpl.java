package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.crm.dto.RoleDto;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.RoleMenu;
import cn.keovi.blog.service.consumer.mapper.RoleMenuMapper;
import cn.keovi.blog.service.consumer.service.RoleMenuService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName RoleMenuServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/25/20:57
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService{


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoles(RoleDto roledto) {

        lambdaUpdate().eq(RoleMenu::getRoleId,roledto.getId()).remove();
        for (String menu:roledto.getMenus()){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRoleId(roledto.getId());
            save(roleMenu);
        }
    }
}
