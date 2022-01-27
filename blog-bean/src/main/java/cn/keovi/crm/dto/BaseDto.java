package cn.keovi.crm.dto;

import lombok.Data;

/**
 * @ClassName BaseDto
 * @Description
 * @Author gustavo
 * @Date 2021/12/27/23:01
 */
@Data
public class BaseDto extends PageDto{

    private String keyword;

    private Long id;

    private Integer status;

    private Integer category;

}
