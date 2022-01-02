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
 * @ClassName Comment
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Data
@TableName(value = "`comment`")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父类id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 文章id
     */
    @TableField(value = "topic_id")
    private String topicId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 0：未删除   1：删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long createBy;

    /**
     * 更新人
     */
    @TableField(value = "last_update_by")
    private Long lastUpdateBy;

    public static final String COL_ID = "id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_TOPIC_ID = "topic_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}