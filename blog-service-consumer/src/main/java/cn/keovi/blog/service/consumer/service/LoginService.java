package cn.keovi.blog.service.consumer.service;

import cn.keovi.constants.Result;
import cn.keovi.crm.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * @ClassName LoginService
 * @Description
 * @Author gustavo
 * @Date 2021/12/28/17:52
 */
public interface LoginService {

    Object login(JsonNode map);

    Result register(UserDto userDto);

    void logout();

    Object wxlogin(JsonNode map);

    Object bindEmail(JsonNode map);
}
