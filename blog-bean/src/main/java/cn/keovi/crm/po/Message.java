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
 * @ClassName Message
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/28/0:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "message")
public class Message {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 留言
     */
    @TableField(value = "message")
    private String message;

    /**
     * 0未删除  1已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

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