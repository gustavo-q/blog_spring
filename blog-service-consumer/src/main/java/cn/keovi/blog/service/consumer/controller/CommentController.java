package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.CommentService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Comment;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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




    //文章列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody CommentDto commentDto) {
        try {
            List<Comment> comments=commentService.pageList(commentDto);
            long count = commentService.lambdaQuery().eq(Comment::getIsDelete,0).eq(Comment::getParentId,0).count();
            return Result.ok().data200(comments,count);
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //新增评论
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
                comment.setCreateBy(loginManager.getUserId());
                comment.setCreateTime(new Date());
            if (commentService.save(comment)){
                return Result.ok();
            }
            return Result.error();
        } catch (Exception e) {
            log.error("评论添加失败!", e);
            return Result.error(500, e.getMessage());

        }
    }


    //删除评论
    @DeleteMapping("/deleteTap/{id}")
    public Result deleteTap(@PathVariable Long id){
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
            if (commentService.lambdaUpdate().set(Comment::getIsDelete, 1).set(Comment::getLastUpdateTime,new Date())
                    .set(Comment::getLastUpdateBy,loginManager.getUserId()).eq(Comment::getId, id).update()) {
                log.info("删除成功,id{}", id);
                return Result.ok("删除成功！");
            }
            return Result.error("删除失败！");
        }catch (Exception e){
            log.error("文章删除失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //用户最近评论
    @GetMapping("/userNewDiscuss")
    public Result userNewDiscuss() {
        try {
            if (loginManager.getUserId()==null) return Result.error(401,"登录失效！");
             QueryWrapper<Comment> queryWrapper= new QueryWrapper<Comment>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.eq("create_by",loginManager.getUserId());
            queryWrapper.orderByDesc("create_time");

            IPage<Comment> page1 = commentService.page(new Page<>(1, 10),queryWrapper );
            return Result.ok().data(200,page1.getRecords());
        } catch (Exception e) {
            log.error("文章显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }

}
