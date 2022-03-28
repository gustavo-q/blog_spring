package cn.keovi.blog.service.consumer.serviceImpl;


import cn.keovi.blog.service.consumer.mapper.MenuMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.dto.CurrentUserInfoDto;
import cn.keovi.crm.po.Menu;
import cn.keovi.exception.ServiceException;
import cn.keovi.utils.MD5Util;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.User;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @ClassName UserServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;


    @Autowired
    private LoginManager loginManager;

    @Autowired
    private UserService userService;


    @Autowired
    ArticleService articleService;

    @Autowired
    MenuMapper menuMapper;


    //用户列表
    @Override
    public Result findList(BaseDto baseDto) {

        List<User> list = userMapper.findList(baseDto);
        long count = userMapper.findListCount(baseDto);
        return Result.ok().data200(list,count);
    }

    //详情
    @Override
    public User userDetails(long id) {
        User user = this.lambdaQuery().eq(User::getId, id).eq(User::getIsDelete, 0).one();
        user.setPassword("");
        return user;
    }

    @Override
    public CurrentUserInfoDto currentUserInfo() {
        if (loginManager.getUserSession() == null) throw new ServiceException("登录失效!");

        User user = userService.lambdaQuery().eq(User::getId, loginManager.getUserId()).one();
        if (user == null) throw new ServiceException("该用户不存在");

        //获取menu
        List<Menu> menus = menuMapper.selectMenuByRoleId(loginManager.getUserSession().getRoleId());
        //menu名字
        List<String> menusName = menus.stream().map(Menu::getName).collect(Collectors.toList());

        CurrentUserInfoDto currentUserInfoDto = CurrentUserInfoDto.builder()
                .username(user.getUsername())
                .mobile(user.getMobile())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .intro(user.getIntro())
                .qq(user.getQq())
                .wechat(user.getWechat())
                .roleId(user.getRoleId())
                .menus(menusName)
                .sex(user.getSex())
                .id(loginManager.getUserId())
                .build();

        return currentUserInfoDto;
    }


    //重置密码
    @Override
    @Transactional
    public void resetPas(int id) {
        if (loginManager.getUserSession() == null) throw new ServiceException("登录失效!");
        boolean update = this.lambdaUpdate().set(User::getPassword, MD5Util.encrypt("123456"))
                .set(User::getLastUpdateBy, loginManager.getUserId())
                .set(User::getLastUpdateTime, new Date())
                .eq(User::getId, id).eq(User::getIsDelete, 0).update();
        if (!update) throw new ServiceException("重置密码失败");
    }






}
