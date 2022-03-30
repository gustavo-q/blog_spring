package cn.keovi.blog.service.consumer.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.keovi.blog.service.consumer.service.AnnouncementService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.AnnouncementDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Announcement;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnnouncementController
 * @Description 公告
 * @Author gustavo
 * @Date 2022/03/30/14:39
 */


@Slf4j
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {


    @Resource
    private LoginManager loginManager;

    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private UserService userService;

    //添加编辑公告
    @PostMapping("/addAnnouncement")
    public Result addAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");

            Announcement announcement = new Announcement();
            announcement.setContent(announcementDto.getContent());
            announcement.setTitle(announcementDto.getTitle());
            if (announcementDto.getId() == null) {
                announcement.setCreateBy(loginManager.getUserId());
                announcement.setCreateTime(new Date());
            } else {
                announcement.setId(announcementDto.getId());
                announcement.setLastUpdateBy(loginManager.getUserId());
                announcement.setLastUpdateTime(new Date());
            }
            announcementService.saveOrUpdate(announcement);
            return Result.ok("添加成功");
        } catch (Exception e) {
            log.error("公告列表添加编辑错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //公告列表
    @PostMapping("/pageList")
    public Result pageList(@RequestBody BaseDto baseDto) {
        try {
            QueryWrapper<Announcement> queryWrapper = new QueryWrapper<Announcement>();
            queryWrapper.eq("is_delete", 0);
            if (StringUtils.isNotBlank(baseDto.getKeyword())) {
                queryWrapper.and(i -> i.like("title", baseDto.getKeyword()).or().like("content", baseDto.getKeyword()));
            }
            queryWrapper.orderByDesc("top", "create_time");

            IPage<Announcement> page1 = announcementService.page(new Page<>(baseDto.getCurrentPage(), baseDto.getPageSize()), queryWrapper);
            ArrayList<Map> jsonObjects = new ArrayList<>();
            page1.getRecords().forEach(comment -> {
                Map<String, Object> map = MapUtil.newHashMap();
                BeanUtil.copyProperties(comment, map);
                map.put("name", userService.lambdaQuery().eq(User::getId, comment.getCreateBy()).one().getUsername());
                jsonObjects.add(map);
            });
            return Result.ok().data200(jsonObjects, page1.getTotal());
        } catch (Exception e) {
            log.error("公告列表显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //公告置顶
    @GetMapping("/onTop")
    public Result onTop(Long id, Integer top) {
        try {
            if (top == 1) {
                announcementService.lambdaUpdate().set(Announcement::getTop, 0).eq(Announcement::getId, id).update();
            } else {
                announcementService.lambdaUpdate().set(Announcement::getTop, 1).eq(Announcement::getId, id).update();
            }
            return Result.ok("置顶成功");
        } catch (Exception e) {
            log.error("公告列表显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }


    //公告删除
    @GetMapping("/delAnnouncement")
    public Result delAnnouncement(Long id) {
        try {
            announcementService.lambdaUpdate().set(Announcement::getIsDelete, 1).eq(Announcement::getId, id).update();
            return Result.ok("置顶成功");
        } catch (Exception e) {
            log.error("公告列表显示错误!", e);
            return Result.error(500, e.getMessage());

        }

    }

}
