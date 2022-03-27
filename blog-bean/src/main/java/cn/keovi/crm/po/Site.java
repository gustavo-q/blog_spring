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
 * @ClassName Site
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/27/0:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "site")
public class Site {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 访客
     */
    @TableField(value = "visitor")
    private Long visitor;
}