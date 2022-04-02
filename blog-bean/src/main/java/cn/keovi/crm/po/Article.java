package cn.keovi.crm.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Article
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Data
@TableName(value = "article")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;


    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Integer views;


    /**
     * 0：草稿   1：上线
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 0：未删除   1：删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 更新人
     */
    @TableField(value = "last_update_by")
    private Long lastUpdateBy;

    //置顶0否 1是
    private Integer top;

    //评论0否 1是
    private Integer commentEnabled;

    //赞赏0否 1是
    private Integer appreciation;



    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_BRIEF_CONTENT = "brief_content";

    public static final String COL_CONTENT = "content";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_VIEWS = "views";

    public static final String COL_COVER_IMAGE = "cover_image";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}