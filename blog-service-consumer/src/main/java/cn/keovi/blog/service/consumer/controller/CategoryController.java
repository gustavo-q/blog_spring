package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.mapper.ArticleCategoryMapper;
import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description 分类
 * @Author gustavo
 * @Date 2022/01/03/11:53
 */


@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private LoginManager loginManager;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;






    //新增修改分类
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody ArticleCategory articleCategory){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            if (articleCategory.getId()!=null){
                articleCategory.setLastUpdateBy(loginManager.getUserId());
                articleCategory.setLastUpdateTime(new Date());
            }else {
                articleCategory.setCreateBy(loginManager.getUserId());
                articleCategory.setCreateTime(new Date());
            }
            if (articleCategoryService.saveOrUpdate(articleCategory)){
                return Result.ok(200,"添加成功");
            }
            return Result.error();
        } catch (Exception e) {
            log.error("分类显示错误!", e);
            return Result.error(500, e.getMessage());

        }
    }

    //删除分类
    @GetMapping("/deleteCategory")
    public Result deleteCategory(@RequestParam Long id){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            if (articleCategoryService.lambdaUpdate().set(ArticleCategory::getIsDelete, 1).set(ArticleCategory::getLastUpdateTime,new Date())
                    .set(ArticleCategory::getLastUpdateBy,loginManager.getUserId()).eq(ArticleCategory::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        }catch (Exception e){
            log.error("分类删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }



    //分页
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<ArticleCategory> articleCategories=articleCategoryMapper.pageList(baseDto);
            long count=articleCategoryMapper.pageListCount(baseDto);
            return Result.ok().data(200,articleCategories,count);
        } catch (Exception e) {
            log.error("查询分类列表失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //根据用户id查询标签
    @GetMapping("/getAllCategory")
    public Result getAllCategory() {
        try {
            List<ArticleCategory> articleCategories=articleCategoryService.lambdaQuery().eq(ArticleCategory::getIsDelete,0)
                    .eq(ArticleCategory::getStatus,0).list();
            return Result.ok().data(200,articleCategories);
        } catch (Exception e) {
            log.error("查询分类列表失败!", e);
            return Result.error(500, e.getMessage());

        }

    }

}
