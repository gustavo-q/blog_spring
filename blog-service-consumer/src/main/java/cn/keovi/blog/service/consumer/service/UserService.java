package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.UserDto;
import cn.keovi.crm.po.User;
import cn.keovi.constants.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @ClassName UserService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface UserService extends IService<User>{


    Result login(JsonNode map);

    Result register(UserDto userDto);
}
