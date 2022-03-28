package cn.keovi.blog.service.consumer.controller;

import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.MessageService;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Message;
import cn.keovi.crm.po.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName MessageController
 * @Description 留言控制层
 * @Author gustavo
 * @Date 2022/03/28/0:46
 */


@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private UserService userService;

    //留言显示
    @GetMapping("/{page}/{showCount}")
    @IgnoreAuth
    public Object pageList(@PathVariable("page") Integer page, @PathVariable("showCount") Integer showCount) {
        try {
            QueryWrapper<Message> queryWrapper = new QueryWrapper<Message>();
            queryWrapper.eq("is_delete", 0);
            queryWrapper.orderByDesc("create_time");

            IPage<Message> page1 = messageService.page(new Page<>(page, showCount), queryWrapper);
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            page1.getRecords().forEach(message -> {
                JSONObject tableProps = JSON.parseObject(JSON.toJSONString(message));
                System.out.println(message);
                tableProps.put("name", userService.lambdaQuery().eq(User::getId, message.getCreateBy()).one().getUsername());
                jsonObjects.add(tableProps);
            });
            return Result.ok().data(200, jsonObjects, page1.getTotal());
        } catch (Exception e) {
            log.error("留言显示失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //添加留言
    @PostMapping("/addMessage")
    public Object addMessage(String messageBody) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            Message message = Message.builder()
                    .message(messageBody)
                    .createBy(loginManager.getUserId())
                    .createTime(new Date())
                    .build();
            if (messageService.save(message)) return Result.ok(200, "添加留言成功");
            return Result.error(500, "添加留言失败");
        } catch (Exception e) {
            log.error("留言添加失败!", e);
            return Result.error(500, e.getMessage());
        }
    }


    //添加留言
    @DeleteMapping("/delMessage")
    public Object delMessage(@RequestParam Long messageId) {
        try {
            if (loginManager.getUserId() == null) return Result.error(401, "登录失效！");
            if (messageService.lambdaUpdate().set(Message::getIsDelete, 1).eq(Message::getId, messageId).update())
                return Result.ok(200, "删除留言成功");
            return Result.error(500, "添加留言失败");
        } catch (Exception e) {
            log.error("留言添加失败!", e);
            return Result.error(500, e.getMessage());
        }
    }

}
