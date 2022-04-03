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
 * @ClassName Site
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/04/03/15:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "site")
public class Site {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 0 未删除  1已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}