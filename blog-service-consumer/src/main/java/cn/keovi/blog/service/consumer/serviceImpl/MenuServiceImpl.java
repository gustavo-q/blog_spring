package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.keovi.blog.service.consumer.service.RoleMenuService;
import cn.keovi.blog.service.consumer.service.UserRoleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.RoleMenu;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Menu;
import cn.keovi.blog.service.consumer.mapper.MenuMapper;
import cn.keovi.blog.service.consumer.service.MenuService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName MenuServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/18/16:08
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{


    @Autowired
    private LoginManager loginManager;

    @Autowired
    RoleMenuService roleMenuService;




    //更新菜单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(Menu menu) {
        if (lambdaUpdate().set(Menu::getName,menu.getName())
                .set(Menu::getParentId,menu.getParentId())
                .set(Menu::getPath,menu.getPath())
                .set(Menu::getDescription,menu.getDescription())
                .set(Menu::getStatus,menu.getStatus())
                .set(Menu::getLastUpdateTime,new Date())
                .set(Menu::getLastUpdateBy,loginManager.getUserId())
                .eq(Menu::getId,menu.getId()).update()){
            //菜单禁用
            if (menu.getStatus()==1 && menu.getParentId()==0){
                roleMenuService.lambdaUpdate().eq(RoleMenu::getMenu,menu.getName()).remove();
                List<Menu> list = lambdaQuery().eq(Menu::getParentId, menu.getId()).list();
                if (CollectionUtil.isNotEmpty(list)) {
                    //菜单状态改变
                    list.forEach(me-> {
                        me.setStatus(1);
                        updateById(me);
                    });
                    //权限删除
                    List<String> collect = list.stream().map(Menu::getName).collect(Collectors.toList());
                    for (String name : collect) {
                        roleMenuService.lambdaUpdate().eq(RoleMenu::getMenu, name).remove();
                    }
                }
            }

            //菜单启用
            if (menu.getParentId()!=0 && menu.getStatus()==0){
                Menu menu1 = lambdaQuery().eq(Menu::getId, menu.getParentId()).one();
                if (menu1.getStatus()==1){
                    menu1.setStatus(0);
                   updateById(menu1);
                }
            }
        }
    }
}
