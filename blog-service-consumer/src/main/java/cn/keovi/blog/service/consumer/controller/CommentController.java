package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.CommentService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Comment;
import cn.keovi.crm.po.User;
import cn.keovi.crm.vo.CommentVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentController
 * @Description 评论
 * @Author gustavo
 * @Date 2022/01/02/23:04
 */

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private LoginManager loginManager;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;


    //评论列表
    @PostMapping("/pageList")
    public Object pageList(@RequestBody CommentDto commentDto) {
        try {
            List<CommentVo> comments = commentService.pageList(commentDto);
            long count = commentService.pageListCount(commentDto);
            return Result.ok().data200(comments, count);
        } catch (Exception e) {
            log.error("评论显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //新增评论
    @PostMapping("/addComment")
    public Result addComment(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            Comment comment = new Comment();
            comment.setCreateBy(loginManager.getUserId());
            comment.setCreateTime(new Date());
            comment.setContent(map.get("discussBody").asText());
            comment.setTopicId(map.get("blogId").asText());
            comment.setParentId(map.get("num").asInt());
            if (commentService.save(comment)) {
                return Result.ok();
            }
            return Result.error();
        } catch (Exception e) {
            log.error("评论添加失败!", e);
            return Result.error(500, e.getMessage());

        }
    }


    //删除评论
    @DeleteMapping("/delComment/{id}")
    public Result deleteTap(@PathVariable Long id) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (commentService.lambdaUpdate().set(Comment::getIsDelete, 1).set(Comment::getLastUpdateTime, new Date())
                    .set(Comment::getLastUpdateBy, loginManager.getUserId()).eq(Comment::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            log.error("评论删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //最近评论
    @GetMapping("/NewDiscuss")
    public Object NewDiscuss() {
        try {
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.orderByDesc("create_time");
            IPage<Comment> page1 = commentService.page(new Page<>(1, 10), queryWrapper);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            page1.getRecords().forEach(comment -> {
                JSONObject tableProps = JSON.parseObject(JSON.toJSONString(comment));
//                System.out.println(message);
                tableProps.put("name", userService.lambdaQuery().eq(User::getId, comment.getCreateBy()).one().getUsername());
                tableProps.put("title", articleService.lambdaQuery().eq(Article::getId, comment.getTopicId()).one().getTitle());
                jsonObjects.add(tableProps);
            });
            return Result.ok().data(200, jsonObjects);
        } catch (Exception e) {
            log.error("评论显示错误!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //用户最近评论
    @GetMapping("/userNewDiscuss")
    public Object userNewDiscuss() {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.eq("create_by", loginManager.getUserId());
            queryWrapper.orderByDesc("create_time");

            IPage<Comment> page1 = commentService.page(new Page<>(1, 10), queryWrapper);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            page1.getRecords().forEach(comment -> {
                JSONObject tableProps = JSON.parseObject(JSON.toJSONString(comment));
//                System.out.println(message);
                tableProps.put("name", userService.lambdaQuery().eq(User::getId, comment.getCreateBy()).one().getUsername());
                tableProps.put("title", articleService.lambdaQuery().eq(Article::getId, comment.getTopicId()).one().getTitle());
                jsonObjects.add(tableProps);
            });
            return Result.ok().data(200, jsonObjects);
        } catch (Exception e) {
            log.error("评论显示错误!", e);
            return Result.error(500, e.getMessage());
        }
    }



}
