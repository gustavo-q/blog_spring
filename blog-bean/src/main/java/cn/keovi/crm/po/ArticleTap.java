package cn.keovi.crm.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName ArticleTap
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Data
@TableName(value = "article_tap")
public class ArticleTap {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签id
     */
    @TableField(value = "tap_id")
    private Long tapId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    public static final String COL_ID = "id";

    public static final String COL_TAP_ID = "tap_id";

    public static final String COL_USER_ID = "user_id";
}