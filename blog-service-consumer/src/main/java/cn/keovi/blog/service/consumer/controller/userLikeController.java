package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.UserLikeService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.User;
import cn.keovi.crm.po.UserLike;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName userLikeController
 * @Description 点赞控制器
 * @Author gustavo
 * @Date 2022/03/27/15:21
 */


@Slf4j
@RestController
@RequestMapping("/userLike")
public class userLikeController {


    @Autowired
    UserLikeService userLikeService;
    @Autowired
    private LoginManager loginManager;


    //点赞总数
    @GetMapping("/getBlogLikeCount/{id}")
    @IgnoreAuth
    public Result getBlogLikeCount(@PathVariable("id") Integer id) {
        try {
            Integer count = userLikeService.lambdaQuery().eq(UserLike::getArticleId, id)
                    .eq(UserLike::getLike,1)
                    .eq(UserLike::getIsDelete, 0).count();
            return Result.ok().data(200, count);
        } catch (Exception e) {
            log.error("显示点赞总数失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //是否点赞
    @GetMapping("/isUserLike/{blogId}/{id}")
    @IgnoreAuth
    public Object isUserLike(@PathVariable("blogId") Integer blogId, @PathVariable("id") Integer id) {
        try {
            if (userLikeService.lambdaQuery().eq(UserLike::getArticleId, blogId).eq(UserLike::getCreateBy, id).count() > 0) {
                return Result.ok().data(200, userLikeService.lambdaQuery()
                        .eq(UserLike::getArticleId, blogId)
                        .eq(UserLike::getCreateBy, id).one().getLike());
            }
            return Result.ok().data(200, "0");
        } catch (Exception e) {
            log.error("显示点赞数失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //保存点赞
    @PostMapping("/saveUserLike")
    @Transactional
    public Object saveUserLike(@RequestBody JsonNode map) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            //判断是否存在
            if (userLikeService.lambdaQuery().eq(UserLike::getArticleId, map.get("bolgId").asLong())
                    .eq(UserLike::getCreateBy, loginManager.getUserId()).eq(UserLike::getIsDelete,0).count() > 0) {
                userLikeService.lambdaUpdate().set(UserLike::getLike,map.get("status").asInt())
                        .set(UserLike::getLastUpdateBy,loginManager.getUserId())
                        .set(UserLike::getLastUpdateTime,new Date())
                        .eq(UserLike::getArticleId, map.get("bolgId").asLong())
                        .eq(UserLike::getCreateBy, loginManager.getUserId()).update();
            }else {
                UserLike userLike = UserLike.builder()
                        .articleId(map.get("bolgId").asLong())
                        .createBy(loginManager.getUserId())
                        .createTime(new Date())
                        .like(map.get("status").asInt())
                        .build();
                userLikeService.save(userLike);
            }

            return Result.ok();
        } catch (Exception e) {
            log.error("点赞失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


}
