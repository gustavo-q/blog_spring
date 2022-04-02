package cn.keovi.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ArticleVo
 * @Description
 * @Author gustavo
 * @Date 2022/04/02/23:35
 */

@Data
public class ArticleVo {

    private Long id;
    private String title;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
    private Long categoryId;
    private String username;

}
