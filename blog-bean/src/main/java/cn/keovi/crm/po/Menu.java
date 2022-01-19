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
 * @ClassName Menu
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/18/16:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 父类id
     */
    @TableField(value = "parent_id")
    private Long parentId;

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

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_PATH = "path";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_BY = "create_by";
}