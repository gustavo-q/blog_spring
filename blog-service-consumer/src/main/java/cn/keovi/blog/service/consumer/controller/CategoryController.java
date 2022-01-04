package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
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


    //新增修改标签
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody ArticleCategory articleCategory){
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            articleCategory.setCreateBy(loginManager.getUserId());
            articleCategory.setCreateTime(new Date());
            if (articleCategoryService.saveOrUpdate(articleCategory)){
                return Result.ok();
            }
            return Result.error();
        } catch (Exception e) {
            log.error("分类显示错误!", e);
            return Result.error(500, e.getMessage());

        }
    }

    //删除分类
    @DeleteMapping("/deleteCategory/{id}")
    public Result deleteCategory(@PathVariable Long id){
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
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



    //根据用户id查询标签
    @PostMapping("/pageList")
    public Result pageList() {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            List<ArticleCategory> articleCategories=articleCategoryService.lambdaQuery().eq(ArticleCategory::getCreateBy,loginManager.getUserId())
                    .eq(ArticleCategory::getIsDelete,0).list();
            return Result.ok().data(articleCategories);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }

}
