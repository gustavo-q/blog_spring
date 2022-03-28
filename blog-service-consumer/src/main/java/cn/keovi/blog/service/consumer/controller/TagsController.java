package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.mapper.TagsMapper;
import cn.keovi.blog.service.consumer.service.TagsService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.ArticleCategory;
import cn.keovi.crm.po.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TagsController
 * @Description 标签
 * @Author gustavo
 * @Date 2022/01/02/21:54
 */
@Slf4j
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagsMapper tagsMapper;

    //新增修改标签
    @PostMapping("/addTags")
    public Result addTags(@RequestBody Tags tags){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            if (tags.getId()!=null){
                tags.setLastUpdateBy(loginManager.getUserId());
                tags.setLastUpdateTime(new Date());
            }else {
                tags.setCreateBy(loginManager.getUserId());
                tags.setCreateTime(new Date());
            }
            if (tagsService.saveOrUpdate(tags)){
                return Result.ok();
            }
            return Result.error();
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }
    }

    //删除标签
    @DeleteMapping("/deleteTags/{id}")
    public Result deleteTags(@PathVariable Long id){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            if (tagsService.lambdaUpdate().set(Tags::getIsDelete, 1).set(Tags::getLastUpdateTime,new Date())
                    .set(Tags::getLastUpdateBy,loginManager.getUserId()).eq(Tags::getId, id).update()) {
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
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            baseDto.setId(loginManager.getUserId());
            System.out.println(baseDto);
            List<Tags> TagsList=tagsMapper.pageList(baseDto);
            Long count = tagsMapper.pageListCount(baseDto);
            return Result.ok().data(200,TagsList,count);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //根据用户id查询标签
    @GetMapping("/getAllTags")
    public Result getAllTags() {
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            List<Tags> tagsList=tagsService.lambdaQuery().eq(Tags::getIsDelete,0)
                    .eq(Tags::getStatus,0).eq(Tags::getCreateBy,loginManager.getUserId()).list();
            return Result.ok().data(200,tagsList);
        } catch (Exception e) {
            log.error("查询分类列表失败!", e);
            return Result.error(500, e.getMessage());

        }

    }



}
