package cn.keovi.blog.service.consumer.controller;

import cn.keovi.annotation.IgnoreAuth;
import cn.keovi.blog.service.consumer.service.MessageService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.po.Message;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    //博客显示
    @GetMapping("/{page}/{showCount}")
    @IgnoreAuth
    public Object pageList(@PathVariable("page") Integer page,@PathVariable("showCount") Integer showCount) {
        try {
            QueryWrapper<Message> queryWrapper= new QueryWrapper<Message>();
            queryWrapper.eq("is_delete",0);
            queryWrapper.orderByDesc("create_time");

            IPage<Message> page1 = messageService.page(new Page<>(page, showCount),queryWrapper );
            return Result.ok().data(200,page1.getRecords(),page1.getTotal());
        }catch (Exception e){
            log.error("留言显示失败!",e);
            return Result.error(500,e.getMessage());
        }
    }

}
