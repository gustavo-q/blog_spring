package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.*;
import cn.keovi.crm.po.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.keovi.constants.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private UserDonateService userDonateService;



    //博客显示
    @GetMapping("/home/{page}/{showCount}")
    @IgnoreAuth
    public Object Login(@PathVariable("page") Integer page,@PathVariable("showCount") Integer showCount) {
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
    public Object hotBlog() {
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
    public Object statisticalBlogByMonth() {
        try {
            List<Map<String,Object>> map = articleService.statisticalBlogByMonth();
            return Result.ok().data(200,map);
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }



    //获取blog文章
    @GetMapping("/getBlogById/{id}")
    @IgnoreAuth
    public Object getBlogById(@PathVariable("id") Integer id) {
        try {
            Article one = articleService.lambdaQuery().eq(Article::getId, id).one();
            HashMap<String, Object> result = new HashMap<>();
            result.put("title", one.getTitle());
            result.put("content", one.getContent());
            result.put("blogViews", one.getViews());
            result.put("time", one.getCreateTime());

            User user = userService.lambdaQuery().eq(User::getId, one.getCreateBy()).one();
            result.put("userName",user.getUsername());

            //评论数
            Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, id).eq(Comment::getIsDelete, 0).count();
            result.put("discussCount",count);

            //标签
            List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, id).list();
            ArrayList<String> strings = new ArrayList<>();
            articleTags.forEach(articleTags1 -> {
                strings.add(tagsService.lambdaQuery().eq(Tags::getId,articleTags1.getTagId()).one().getTag());
            });
            result.put("tags",strings);

            //赞赏
            if (one.getAppreciation()==1){
                UserDonate userDonate = userDonateService.lambdaQuery().eq(UserDonate::getIsDelete, 0)
                        .eq(UserDonate::getCreateBy, one.getCreateBy()).one();
                if (ObjectUtil.isEmpty(userDonate)){
                    result.put("userReward","1");//赞赏二维码未设置
                }else {
                    result.put("userReward",userDonate.getDonateJson());
                }

            }else {
                result.put("userReward","0");//不可以进行赞赏
            }

            return Result.ok().data(200,result);
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }


}
