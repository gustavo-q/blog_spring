package cn.keovi.blog.service.consumer.serviceImpl;


import cn.keovi.crm.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.User;
import cn.keovi.blog.service.consumer.mapper.UserMapper;
import cn.keovi.blog.service.consumer.service.UserService;

import java.util.List;



/**
 * @ClassName UserServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Autowired
    UserMapper userMapper;


    //用户列表
    @Override
    public List<User> findList(BaseDto baseDto) {
        return userMapper.findList(baseDto);

    }

    //详情
    @Override
    public User userDetails(long id) {
        User user = this.lambdaQuery().eq(User::getId, id).eq(User::getIsDelete,0).one();
        user.setPassword("");
        return user;
    }


}
