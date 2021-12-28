package cn.keovi.crm.dto;

import cn.keovi.crm.po.User;
import lombok.Data;

/**
 * @ClassName UserDto
 * @Description 注册dto
 * @Author gustavo
 * @Date 2021/12/27/16:05
 */

@Data
public class UserDto extends User{

    //验证码
    private String emailCode;




}
