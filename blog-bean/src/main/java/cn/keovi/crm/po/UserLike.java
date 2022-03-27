package cn.keovi.crm.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserLike
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/27/14:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_like")
public class UserLike {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 未点赞：0，点赞：1
     */
    @TableField(value = "like")
    private Integer like;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 0:未删除   1:删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

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

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;
}