package cn.keovi.blog.service.consumer.controller;

import cn.keovi.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogController
 * @Description 前台博客
 * @Author gustavo
 * @Date 2022/03/25/20:09
 */


@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private ArticleService articleService;


    //博客显示
    @GetMapping("/home/{page}/{showCount}")
    @IgnoreAuth
    public Result Login(@PathVariable("page") Integer page,@PathVariable("showCount") Integer showCount) {
        try {
            QueryWrapper<Article> queryWrapper= new QueryWrapper<Article>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.eq("status",1);
            queryWrapper.orderByDesc("top","create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount),queryWrapper );
            return Result.ok().data(200,page1.getRecords(),page1.getTotal());
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }


    //热门文章
    @GetMapping("/hotBlog")
    @IgnoreAuth
    public Result hotBlog() {
        try {
            QueryWrapper<Article> queryWrapper= new QueryWrapper<>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.eq("status",1);
            queryWrapper.orderByDesc("views","create_time");

            IPage<Article> page1 = articleService.page(new Page<>(1, 10),queryWrapper );
            return Result.ok().data(200,page1.getRecords());
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }



    //文章归档
    @GetMapping("/statisticalBlogByMonth")
    @IgnoreAuth
    public Result statisticalBlogByMonth() {
        try {
            List<Map<String,Object>> map = articleService.statisticalBlogByMonth();
            return Result.ok().data(200,map);
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }

    //文章查看
    @GetMapping("/getBlogLikeCount/{id}")
    @IgnoreAuth
    public Result getBlogLikeCount(@PathVariable("id") Integer id) {
        try {
            Article one = articleService.lambdaQuery().eq(Article::getId, id).one();
            return Result.ok().data(200,one);
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }


}
