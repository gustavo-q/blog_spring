package cn.keovi.crm.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleDto
 * @Description
 * @Author gustavo
 * @Date 2022/01/25/22:59
 */

@Data
public class RoleDto {

    private Long id;


    private String name;

    private List<String> menus;

    private String description;

}
