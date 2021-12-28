package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.User;
import cn.keovi.utils.PageHelper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户
 * @Author gustavo
 * @Date 2021/12/27/22:33
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;

    @Autowired
    UserMapper userMapper;


    //邮箱登录
    @PostMapping("/pageList")
    public Result Login(@RequestBody BaseDto baseDto) {
        try {
            List<User> userList = userService.list();
            long count = userService.count();
            return Result.ok().data(userList);
        }catch (Exception e){
            logger.error("登录失败!",e);
            return Result.error(500,e.getMessage());

        }

    }


}
