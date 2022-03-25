package cn.keovi.blog.service.consumer.controller;

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
    public Result Login(@PathVariable("page") Integer page,@PathVariable("showCount") Integer showCount) {
        try {
            QueryWrapper<Article> queryWrapper= new QueryWrapper<Article>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount),queryWrapper );
            return Result.ok().data(200,page1.getRecords(),page1.getTotal());
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }
}
