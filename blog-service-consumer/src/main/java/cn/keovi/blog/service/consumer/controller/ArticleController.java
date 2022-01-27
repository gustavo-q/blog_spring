package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.ArticleDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Description 文章
 * @Author gustavo
 * @Date 2022/01/02/10:25
 */

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

//
    @Resource
    private LoginManager loginManager;

    @Autowired
    private ArticleService articleService;


    //文章列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<Article> articles=articleService.pageList(baseDto);
            long count = articleService.pageListCount(baseDto);
            return Result.ok().data200(articles,count);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //文章新增修改
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody ArticleDto articleDto) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            Article article = new Article();
            if (article.getId()!=null){
                article.setLastUpdateBy(loginManager.getUserId());
                article.setLastUpdateTime(new Date());
            }else {
                article.setCreateBy(loginManager.getUserId());
                article.setCreateTime(new Date());
            }
            article.setTitle(articleDto.getTitle());
            article.setCategoryId(articleDto.getCategoryId());
            article.setContent(articleDto.getContent());
            if (articleService.saveOrUpdate(article)){
                return Result.ok(200,"添加成功");
            }
            return Result.error();
        } catch (Exception e) {
            log.error("文章新增失败!", e);
            return Result.error(500, e.getMessage());

        }
    }


    //删除文章
    @DeleteMapping("/deleteArticle/{id}")
    public Result deleteArticle(@PathVariable Long id) {
        try {
            if (loginManager.getUserId()==null) return Result.error("登录失效！");
            if (articleService.lambdaUpdate().set(Article::getIsDelete, 1).set(Article::getLastUpdateTime,new Date())
                    .set(Article::getLastUpdateBy,loginManager.getUserId()).eq(Article::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        }catch (Exception e){
            log.error("文章删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }





}
