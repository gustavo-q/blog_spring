package cn.keovi.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName CurrentUserInfoDto
 * @Description
 * @Author gustavo
 * @Date 2022/01/18/15:27
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CurrentUserInfoDto {

    private Long roleId;
    private String username;
    private String avatar;
    private String intro;
    private Long qq;
    private String wechat;
    private String email;
    private Long mobile;
    private List<String> menus;
    private Integer sex;
    private Long id;

}
