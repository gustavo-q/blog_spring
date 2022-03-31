package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.mapper.ArticleCategoryMapper;
import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ArticleService articleService;


    //新增修改分类
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody ArticleCategory articleCategory) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");

            if (articleCategory.getId() != null) {
                articleCategory.setLastUpdateBy(loginManager.getUserId());
                articleCategory.setLastUpdateTime(new Date());
                if (!articleCategoryService.lambdaQuery().eq(ArticleCategory::getId, articleCategory.getId()).one().getType().equals(articleCategory.getType())) {
                    if (articleCategoryService.lambdaQuery().eq(ArticleCategory::getType, articleCategory.getType())
                            .eq(ArticleCategory::getIsDelete, 0).count() > 0) return Result.error(500, "该分类已存在");
                }
            } else {
                articleCategory.setCreateBy(loginManager.getUserId());
                articleCategory.setCreateTime(new Date());
                if (articleCategoryService.lambdaQuery().eq(ArticleCategory::getType, articleCategory.getType())
                        .eq(ArticleCategory::getIsDelete, 0).count() > 0)
                    return Result.error(500, "该分类已存在");

            }
            if (articleCategoryService.saveOrUpdate(articleCategory)) {
                return Result.ok(200, "添加成功");
            }
            return Result.error();
        } catch (Exception e) {
            log.error("分类显示错误!", e);
            return Result.error(500, e.getMessage());

        }
    }

    //删除分类
    @GetMapping("/deleteCategory")
    public Result deleteCategory(@RequestParam Long id) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (articleCategoryService.lambdaUpdate().set(ArticleCategory::getIsDelete, 1).set(ArticleCategory::getLastUpdateTime, new Date())
                    .set(ArticleCategory::getLastUpdateBy, loginManager.getUserId()).eq(ArticleCategory::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            log.error("分类删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //分页
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<ArticleCategory> articleCategories = articleCategoryMapper.pageList(baseDto);
            long count = articleCategoryMapper.pageListCount(baseDto);
            return Result.ok().data(200, articleCategories, count);
        } catch (Exception e) {
            log.error("查询分类列表失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //获取所以分类
    @GetMapping("/getAllCategory")
    @IgnoreAuth
    public Result getAllCategory() {
        try {
            List<ArticleCategory> articleCategories = articleCategoryService.lambdaQuery().eq(ArticleCategory::getIsDelete, 0)
                    .eq(ArticleCategory::getStatus, 0).list();
            List<Map<String, Object>> mapList = new ArrayList<>();
            articleCategories.forEach(category -> {
                Map<String, Object> map = MapUtil.newHashMap();
                Integer count = articleService.lambdaQuery().eq(Article::getCategoryId, category.getId())
                        .eq(Article::getStatus,1).eq(Article::getIsDelete, 0).count();
                BeanUtil.copyProperties(category, map);
                if (count!=null) map.put("articleNum", count);
                mapList.add(map);
            });
            return Result.ok().data(200, mapList);
        } catch (Exception e) {
            log.error("查询分类列表失败!", e);
            return Result.error(500, e.getMessage());

        }

    }

}
