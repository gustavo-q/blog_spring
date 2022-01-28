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

    private Long id;

    private String title;

    private Integer status;

    private Integer categoryId;

    private List<Object> tagList;

    private String content;

    //置顶
    private Boolean top;

    //评论
    private Boolean commentEnabled;

    //赞赏
    private Boolean appreciation;



}
