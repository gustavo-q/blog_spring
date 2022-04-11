package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.annotation.AccessLimit;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.*;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.crm.po.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.keovi.constants.Result;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private LoginManager loginManager;


    //博客显示
    @GetMapping("/home/{page}/{showCount}")
    @IgnoreAuth
    public Object home(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount) {
        try {

            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("status", 1);

            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);
                list.add(result);
            });
            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //根据分类查询文章
    @GetMapping("/homeByCategory/{page}/{showCount}/{categoryId}")
    @IgnoreAuth
    public Object homeByCategory(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount, @PathVariable("categoryId") Integer categoryId) {
        try {

            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("status", 1);
            if (categoryId != 0) {
                queryWrapper.eq("category_id", categoryId);
            }
            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);
                list.add(result);
            });
            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //根据分类查询文章
    @PostMapping("/homeByCategory")
    @IgnoreAuth
    public Object homeByCategory(@RequestBody JsonNode map) {
        try {
            Integer categoryId = map.get("categoryId").asInt();
            Integer page = map.get("page").asInt();
            Integer showCount = map.get("showCount").asInt();

            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("status", 1);
            if (categoryId != 0) {
                queryWrapper.eq("category_id", categoryId);
            }
            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);
                list.add(result);
            });
            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //热门文章
    @GetMapping("/hotBlog")
    @IgnoreAuth
    public Object hotBlog() {
        try {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("status", 1);
            queryWrapper.orderByDesc("views", "create_time");

            IPage<Article> page1 = articleService.page(new Page<>(1, 10), queryWrapper);
            return Result.ok().data(200, page1.getRecords());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //文章归档
    @GetMapping("/statisticalBlogByMonth")
    @IgnoreAuth
    public Object statisticalBlogByMonth() {
        try {
            List<Map<String, Object>> map = articleService.statisticalBlogByMonth();
            return Result.ok().data(200, map);
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
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
            result.put("commentEnabled", one.getCommentEnabled());

            User user = userService.lambdaQuery().eq(User::getId, one.getCreateBy()).one();
            result.put("userName", user.getUsername());
            result.put("avatar", user.getAvatar());

            //评论数
            Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, id).eq(Comment::getIsDelete, 0).count();
            result.put("discussCount", count);

            //标签
            List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, id).list();
            ArrayList<String> strings = new ArrayList<>();
            articleTags.forEach(articleTags1 -> {
                strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
            });
            result.put("tags", strings);

            //赞赏
            if (one.getAppreciation() == 1) {
                UserDonate userDonate = userDonateService.lambdaQuery().eq(UserDonate::getIsDelete, 0)
                        .eq(UserDonate::getCreateBy, one.getCreateBy()).one();
                if (ObjectUtil.isEmpty(userDonate)) {
                    result.put("userReward", "1");//赞赏二维码未设置
                } else {
                    result.put("userReward", userDonate.getDonateJson());
                }

            } else {
                result.put("userReward", "0");//不可以进行赞赏
            }

            return Result.ok().data(200, result);
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //修改访问量
    @GetMapping("/updateViews/{id}")
    @IgnoreAuth
    @AccessLimit(seconds = 30, maxCount = 1, msg = "30秒内只能增加一次访问量")
    public Object updateViews(@PathVariable("id") Integer id) {
        try {
            Article article = articleService.lambdaQuery().eq(Article::getId, id).one();
            article.setViews(article.getViews() + 1);
            articleService.updateById(article);
            return Result.ok();
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //我的博客
    @GetMapping("/myblog/{page}/{showCount}")
    public Object myblog(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("status", 1);
            queryWrapper.eq("create_by", loginManager.getUserId());
            queryWrapper.orderByDesc("top", "create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);

//                //赞赏
//                if (article.getAppreciation()==1){
//                    UserDonate userDonate = userDonateService.lambdaQuery().eq(UserDonate::getIsDelete, 0)
//                            .eq(UserDonate::getCreateBy, article.getCreateBy()).one();
//                    if (ObjectUtil.isEmpty(userDonate)){
//                        result.put("userReward","1");//赞赏二维码未设置
//                    }else {
//                        result.put("userReward",userDonate.getDonateJson());
//                    }
//
//                }else {
//                    result.put("userReward","0");//不可以进行赞赏
//                }
                list.add(result);
            });


            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //我点赞的博客
    @GetMapping("/getMyLoveList/{page}/{showCount}")
    public Object getMyLoveList(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount) {
        try {
            List<Map> list = articleService.getMyLoveList(page, showCount);
            Integer count = articleService.getMyLoveCount();

            return Result.ok().data(200, list, count.longValue());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //wx我点赞的博客
    @PostMapping("/getMyLoveWxList")
    public Object getMyLoveWxList(@RequestBody JsonNode map) {
        try {
            Integer page = map.get("page").asInt();
            Integer showCount = map.get("showCount").asInt();
            List<Map> list = articleService.getMyLoveList(page, showCount);
            Integer count = articleService.getMyLoveCount();

            return Result.ok().data(200, list, count.longValue());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //博客显示
    @GetMapping("/searchBlog/{page}/{showCount}")
    @IgnoreAuth
    public Object searchBlog(@PathVariable("page") Integer page,
                             @PathVariable("showCount") Integer showCount,
                             @RequestParam("search") String searchTxt) {
        try {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0).eq("status", 1).like("title", searchTxt);
            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);

//                //赞赏
//                if (article.getAppreciation()==1){
//                    UserDonate userDonate = userDonateService.lambdaQuery().eq(UserDonate::getIsDelete, 0)
//                            .eq(UserDonate::getCreateBy, article.getCreateBy()).one();
//                    if (ObjectUtil.isEmpty(userDonate)){
//                        result.put("userReward","1");//赞赏二维码未设置
//                    }else {
//                        result.put("userReward",userDonate.getDonateJson());
//                    }
//
//                }else {
//                    result.put("userReward","0");//不可以进行赞赏
//                }
                list.add(result);
            });
            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //根据tags我的博客
    @GetMapping("/getMyBlogByTag/{page}/{showCount}/{tagId}")
    @IgnoreAuth
    public Object getMyBlogByTag(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount, @PathVariable("tagId") Integer tagId) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            List<Long> articles = articleTagsService.lambdaQuery().eq(ArticleTags::getTagId, tagId).list().stream().map(ArticleTags::getArticleId).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(articles)) return Result.ok();
            QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
            queryWrapper.eq("is_delete", 0).eq("status", 1).in("id", articles);
            queryWrapper.orderByDesc("create_time");

            IPage<Article> page1 = articleService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<Map> list = new ArrayList<>();
            page1.getRecords().forEach(article -> {
                HashMap<String, Object> result = new HashMap<>();
                result.put("title", article.getTitle());
                result.put("content", article.getContent());
                result.put("blogViews", article.getViews());
                result.put("time", article.getCreateTime());
                result.put("id", article.getId());

                User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
                result.put("userName", user.getUsername());

                //评论数
                Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
                result.put("discussCount", count);

                //标签
                List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
                ArrayList<String> strings = new ArrayList<>();
                articleTags.forEach(articleTags1 -> {
                    strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
                });
                result.put("tags", strings);

//                //赞赏
//                if (article.getAppreciation()==1){
//                    UserDonate userDonate = userDonateService.lambdaQuery().eq(UserDonate::getIsDelete, 0)
//                            .eq(UserDonate::getCreateBy, article.getCreateBy()).one();
//                    if (ObjectUtil.isEmpty(userDonate)){
//                        result.put("userReward","1");//赞赏二维码未设置
//                    }else {
//                        result.put("userReward",userDonate.getDonateJson());
//                    }
//
//                }else {
//                    result.put("userReward","0");//不可以进行赞赏
//                }
                list.add(result);
            });


            return Result.ok().data(200, list, page1.getTotal());
        } catch (Exception e) {
            log.error("博客显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


}
