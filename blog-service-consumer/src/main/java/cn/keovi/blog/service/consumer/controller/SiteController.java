package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.net.NetUtil;
import cn.keovi.annotation.AccessLimit;
import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.SiteService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Site;
import cn.keovi.crm.po.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SiteController
 * @Description 网站介绍
 * @Author gustavo
 * @Date 2022/03/26/23:59
 */

@Slf4j
@RestController
@RequestMapping("/site")
public class SiteController {


    @Autowired
    private UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    SiteService siteService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //博客显示
    @GetMapping
    public Result site() {
        try {
            User admin = userService.lambdaQuery().eq(User::getUsername, "admin").one();
            HashMap<String, Object> result = new HashMap<>();
            result.put("name", admin.getUsername());
            result.put("email", admin.getEmail());
            result.put("qq", admin.getQq());
            result.put("wechat", admin.getWechat());
            //访客
            result.put("visitor",siteService.getById(1).getVisitor());
            //文章
            result.put("article",articleService.lambdaQuery().eq(Article::getIsDelete,0)
                    .eq(Article::getStatus,1).count());

            return Result.ok().data(200,result);
        } catch (Exception e) {
            log.error("site失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //访问量
    @GetMapping("/visitor")
    public Result visitor() {
        String localhostStr = NetUtil.getLocalhostStr();
        System.out.println(localhostStr);

        if (redisTemplate.opsForValue().get(localhostStr) == null) {
            //在规定周期内第一次访问，存入redis
            redisTemplate.opsForValue().set(localhostStr, "1",
                    1,
                    TimeUnit.MINUTES);
            siteService.lambdaUpdate().set(Site::getVisitor,siteService.getById(1).getVisitor()+1).update();
        }else {
            redisTemplate.expire(localhostStr,1,TimeUnit.MINUTES);
        }
        return Result.ok();
    }

}
