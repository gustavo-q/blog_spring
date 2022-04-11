package cn.keovi.crm.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WxUser
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/04/10/23:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_user")
public class WxUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;
}