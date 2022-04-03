package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.ArticleDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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

    @Autowired
    private UserService userService;


    //文章列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            List<Map<String, Object>> articles = articleService.pageList(baseDto);
            long count = articleService.pageListCount(baseDto);
            return Result.ok().data200(articles, count);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //文章新增修改
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody ArticleDto articleDto) {
        try {
            articleService.addArticle(articleDto);
            return Result.ok();
        } catch (Exception e) {
            log.error("文章新增失败!", e);
            return Result.error(500, e.getMessage());

        }
    }


    //删除文章
    @GetMapping("/deleteArticle")
    public Result deleteArticle(@RequestParam Long id) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (articleService.lambdaUpdate().set(Article::getIsDelete, 1).set(Article::getLastUpdateTime, new Date())
                    .set(Article::getLastUpdateBy, loginManager.getUserId()).eq(Article::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            log.error("文章删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //根据id查询文章
    @GetMapping("/articleById")
    public Result articleById(@RequestParam Long id) {
        try {
            ArticleDto articleDto = articleService.articleById(id);
            return Result.ok().data(200, articleDto);
        } catch (Exception e) {
            log.error("文章查询失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //审核文章列表
    @GetMapping("/getTableList")
    public Result getTableList() {
        try {
            List<Map> mapList = new ArrayList<>();

            QueryWrapper<Article> queryWrapper = new QueryWrapper();
            queryWrapper.eq("status", 2).eq("is_delete", 0);

            IPage<Article> articles = articleService.page(new Page<>(1, 5), queryWrapper);
            articles.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("id", article.getId());
                result.put("title", article.getTitle());
                result.put("username", userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one().getUsername());
                result.put("createTime", article.getCreateTime());
                mapList.add(result);
            });
            return Result.ok().data(200, mapList);
        } catch (Exception e) {
            log.error("文章查询失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //文章审核成功
    @PostMapping("/audit")
    public Result auditSucess(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            Article article=new Article();
            //通过
            if (map.get("audit").asInt()==0) {
                article.setStatus(1);
            }else {
                article.setStatus(2);
            }
            article.setLastUpdateTime(new Date());
            article.setLastUpdateBy(loginManager.getUserId());
            article.setId(map.get("id").asLong());
            articleService.updateById(article);
            return Result.ok();
        } catch (Exception e) {
            log.error("文章新增失败!", e);
            return Result.error(500, e.getMessage());

        }
    }





}
