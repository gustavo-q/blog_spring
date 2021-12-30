package cn.keovi.session;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.io.Serializable;

/**
 * 用户登入之后 缓存用户数据
 */
@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final String USER_SESSION_KEY = "user";
  public static final String USER_ROLE_SESSION_KEY = "user_role_interface";
  public static final String NO_LOGIN_MSG = "会话失效，请重新登录！";

  private Long id;
  private String userName;
  private Integer roleId;
  private String email;
  private Integer qq;
  private String wechat;
  private Integer mobile;

  private String avatar;


}
