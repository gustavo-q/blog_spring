package cn.keovi.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName UserSession
 * @Description 用户登入，缓存用户数据
 * @Author gustavo
 * @Date 2021/12/26/22:16
 */
@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

    public static final String NO_LOGIN_MSG = "会话失效，请重新登录！";

    private Long id; //id
    private Integer roleId; //角色编号
    private String username; //用户名
    private String email; //邮箱
    private Integer qq; //qq
    private String wechat; //wechat
    private Integer mobile; //电话


    private String  avatarUrl; //头像地址



}
