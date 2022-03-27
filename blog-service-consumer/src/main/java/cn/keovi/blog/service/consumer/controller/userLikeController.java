package cn.keovi.blog.service.consumer.controller;

import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.UserLikeService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.UserLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



    //点赞数
    @GetMapping("/getBlogLikeCount/{id}")
    @IgnoreAuth
    public Result getBlogLikeCount(@PathVariable("id") Integer id) {
        try {
            Integer count = userLikeService.lambdaQuery().eq(UserLike::getArticleId, id).eq(UserLike::getIsDelete, 0).count();
            return Result.ok().data(200,count);
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }



    //点赞数
    @GetMapping("/isUserLike/{blogId}/{id}")
    @IgnoreAuth
    public Result isUserLike(@PathVariable("blogId") Integer blogId,@PathVariable("id") Integer id) {
        try {
            UserLike one = userLikeService.lambdaQuery().eq(UserLike::getArticleId, blogId).eq(UserLike::getCreateBy, id).one();

            return Result.ok().data(200,one.getLike());
        }catch (Exception e){
            log.error("博客显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }

}
