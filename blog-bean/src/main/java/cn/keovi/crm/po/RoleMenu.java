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
 * @ClassName RoleMenu
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/25/20:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role_menu")
public class RoleMenu {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * role_id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单
     */
    @TableField(value = "menu")
    private String menu;
}