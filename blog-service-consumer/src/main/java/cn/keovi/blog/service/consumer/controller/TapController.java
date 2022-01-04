package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.TapService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Tap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TapController
 * @Description 标签
 * @Author gustavo
 * @Date 2022/01/02/21:54
 */
@Slf4j
@RestController
@RequestMapping("/tap")
public class TapController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private TapService tapService;

    //新增修改标签
    @PostMapping("/addTap")
    public Result addTap(@RequestBody Tap tap){
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (tap.getId()!=null){
                tap.setLastUpdateBy(loginManager.getUserId());
                tap.setLastUpdateTime(new Date());
            }else {
                tap.setCreateBy(loginManager.getUserId());
                tap.setCreateTime(new Date());
            }
            if (tapService.saveOrUpdate(tap)){
                return Result.ok();
            }
            return Result.error();
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }
    }

    //删除标签
    @DeleteMapping("/deleteTap/{id}")
    public Result deleteTap(@PathVariable Long id){
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (tapService.lambdaUpdate().set(Tap::getIsDelete, 1).set(Tap::getLastUpdateTime,new Date())
                    .set(Tap::getLastUpdateBy,loginManager.getUserId()).eq(Tap::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        }catch (Exception e){
            log.error("文章删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //根据用户id查询标签
    @PostMapping("/pageList")
    public Result pageList() {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            List<Tap> tapList=tapService.lambdaQuery().eq(Tap::getCreateBy,loginManager.getUserId())
                    .eq(Tap::getIsDelete,0).list();
            return Result.ok().data(tapList);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }



}
