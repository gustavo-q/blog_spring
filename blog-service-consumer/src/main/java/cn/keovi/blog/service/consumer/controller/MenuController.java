package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.mapper.MenuMapper;
import cn.keovi.blog.service.consumer.service.MenuService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MenuController
 * @Description 菜单
 * @Author gustavo
 * @Date 2022/01/20/16:11
 */

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private MenuService menuService;


    //菜单列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<Menu> menuList = menuMapper.findList(baseDto);
            Long count = menuMapper.findListCount(baseDto);
            return Result.ok().data200(menuList,count);
        } catch (Exception e) {
            log.error("菜单列表!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //菜单列表更新
    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            menuService.updateMenu(menu);
            return Result.ok("更新成功");
        } catch (Exception e) {
            log.error("更新失败!", e);
            return Result.error(500, e.getMessage());

        }

    }



    //菜单列表新增
    @PostMapping("/saveMenu")
    public Result saveMenu(@RequestBody Menu menu) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            menu.setCreateTime(new Date());
            menu.setCreateBy(loginManager.getUserId());
            menu.setLastUpdateTime(new Date());
            menu.setLastUpdateBy(loginManager.getUserId());
            boolean save = menuService.save(menu);
            if (save) return Result.ok("新增成功");
            return Result.error("新增失败");
        } catch (Exception e) {
            log.error("新增失败!", e);
            return Result.error(500, e.getMessage());

        }

    }




    //菜单列表删除
    @GetMapping("/deleteMenu")
    public Result deleteMenu(@RequestParam Long id) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
           if (menuService.lambdaUpdate().set(Menu::getIsDelete,1)
                   .set(Menu::getLastUpdateBy,loginManager.getUserId())
                   .set(Menu::getLastUpdateTime,new Date())
                   .eq(Menu::getId,id).update()) return  Result.ok("删除成功");
            return Result.error("删除失败");
        } catch (Exception e) {
            log.error("新增失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


}
