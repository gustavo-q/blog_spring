package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.keovi.blog.service.consumer.mapper.ArticleMapper;
import cn.keovi.blog.service.consumer.mapper.CommentMapper;
import cn.keovi.blog.service.consumer.mapper.SiteMapper;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.service.SiteService;
import cn.keovi.blog.service.consumer.service.TagsService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Site;
import cn.keovi.crm.po.Tags;
import cn.keovi.crm.po.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    SiteMapper siteMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    SiteService siteService;

    @Autowired
    TagsService tagsService;

    @Autowired
    CommentMapper commentMapper;


    @Autowired
    private LoginManager loginManager;

    //名片
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
            result.put("visitor", siteService.lambdaQuery().eq(Site::getIsDelete, 0).count());
            //文章
            result.put("article", articleService.lambdaQuery().eq(Article::getIsDelete, 0)
                    .eq(Article::getStatus, 1).count());

            return Result.ok().data(200, result);
        } catch (Exception e) {
            log.error("site失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

    //访问量
    @GetMapping("/visitor")
    public Result visitor(HttpServletRequest request, HttpServletResponse response) {

        siteService.visitor(request, response);
        return Result.ok();
    }


    //首页展示
    @GetMapping("/getCardsData")
    public Result getCardsData() {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");

            User user = userService.lambdaQuery().eq(User::getId, loginManager.getUserId()).one();
            HashMap<String, Object> result = new HashMap<>();
            if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                result.put("visitor", siteService.lambdaQuery().eq(Site::getIsDelete, 0).count());
                result.put("articleTotal", articleService.lambdaQuery().eq(Article::getIsDelete, 0)
                        .eq(Article::getStatus, 1).count());
                result.put("userTotal", userService.lambdaQuery().eq(User::getIsDelete, 0).eq(User::getStatus, 0).count());
            } else {
                result.put("myTag",tagsService.lambdaQuery().eq(Tags::getIsDelete,0).eq(Tags::getCreateBy,loginManager.getUserId()).count());
            }
            result.put("myArticle", articleService.lambdaQuery().eq(Article::getIsDelete, 0)
                    .eq(Article::getStatus, 1).eq(Article::getCreateBy, loginManager.getUserId()).count());

            return Result.ok().data(200, result);
        } catch (Exception e) {
            log.error("getCardsData失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //曲线图数据
    @GetMapping("/getLineData")
    public Result getLineData() {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");

            HashMap<String, Object> result = new HashMap<>();
            List<String> monData = getMonData(DateUtil.today());
            if (loginManager.getUserSession().getRoleId() == 1 || loginManager.getUserSession().getRoleId() == 2) {

                //访问量
                List<Map> mapList = siteMapper.getLineData();
                //文章总数
                List<Map> mapList1 = articleMapper.getLineData();
                //用户
                List<Map> mapList2 = userMapper.getLineData();
                //评论
                List<Map> mapList3 = commentMapper.getLineData();

                result.put("visitor", compareData(monData, mapList));
                result.put("articleTotal", compareData(monData, mapList1));
                result.put("userTotal", compareData(monData, mapList2));
                result.put("commentTotal", compareData(monData, mapList3));
            } else {
                //文章总数
                List<Map> mapList1 = articleMapper.getMyLineData(loginManager.getUserId());
                result.put("articleTotal", compareData(monData, mapList1));
            }
            result.put("monData", monData);
            return Result.ok().data(200, result);
        } catch (Exception e) {
            log.error("getCardsData失败!", e);
            return Result.error(500, e.getMessage());
        }
    }



    //获取前六个月的日期 yyyy-mm   date:yyyy-mm-dd
    private List<String> getMonData(String date) {
        List<String> list = new ArrayList<>();
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        for (int i = 5; i >= 0; i--) {
            if (month > 6) {
                if (month - i >= 10) {
                    list.add(year + "-" + String.valueOf(month - i));
                } else {
                    list.add(year + "-0" + String.valueOf(month - i));
                }
            } else {
                if (month - i <= 0) {
                    if (month - i + 12 >= 10) {
                        list.add(String.valueOf(year - 1) + "-" + String.valueOf(month - i + 12));
                    } else {
                        list.add(String.valueOf(year - 1) + "-0" + String.valueOf(month - i + 12));

                    }
                } else {
                    if (month - i >= 10) {
                        list.add(String.valueOf(year) + "-" + String.valueOf(month - i));
                    } else {
                        list.add(String.valueOf(year) + "-0" + String.valueOf(month - i));

                    }
                }
            }
        }
        return list;
    }

    private List<Integer> compareData(List<String> monData, List<Map> mapList) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < monData.size(); i++) {
            int num = 0;
            for (int j = 0; j < mapList.size(); j++) {
                if (mapList.get(j).containsValue(monData.get(i))) {
                    num++;
                    Number nu = (Number) mapList.get(j).get("count");
                    integerList.add(nu.intValue());
                }
            }
            if (num == 0) {
                integerList.add(0);
            }
        }
        return integerList;
    }



    //日志列表
    @PostMapping("/pageList")
    public Object pageList(@RequestBody BaseDto baseDto) {
        try {
            QueryWrapper<Site> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.orderByDesc("create_time");
            if (StringUtils.isNotBlank(baseDto.getKeyword())){
                queryWrapper.like("ip",baseDto.getKeyword());
            }
            Page<Site> page = siteService.page(new Page<>(baseDto.getCurrentPage(), baseDto.getPageSize()), queryWrapper);
            return Result.ok().data(200,page.getRecords(),page.getTotal());
        } catch (Exception e) {
            log.error("登录失败!", e);
            return Result.error(500, e.getMessage());

        }

    }


}
