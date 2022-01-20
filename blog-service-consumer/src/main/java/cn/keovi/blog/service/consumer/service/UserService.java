package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.dto.CurrentUserInfoDto;
import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import cn.keovi.constants.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * @ClassName UserService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface UserService extends IService<User>{




    Result findList(BaseDto baseDto);

    User userDetails(long id);

    CurrentUserInfoDto currentUserInfo();

    void resetPas(int id);
}
