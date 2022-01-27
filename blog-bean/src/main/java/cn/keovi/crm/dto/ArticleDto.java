package cn.keovi.crm.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ArticleDto
 * @Description
 * @Author gustavo
 * @Date 2022/01/28/0:23
 */
@Data
public class ArticleDto {

    private String title;


    private Integer categoryId;

    private List<Integer> tagList;

    private String content;

}
