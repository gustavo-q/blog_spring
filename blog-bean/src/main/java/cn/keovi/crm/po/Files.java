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
 * @ClassName Files
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Data
@TableName(value = "files")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Files {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名字
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件地址
     */
    @TableField(value = "file_url")
    private String fileUrl;

    /**
     * 文件大小
     */
    @TableField(value = "file_size")
    private Double fileSize;

    /**
     * 文件后缀  .jpg
     */
    @TableField(value = "extension")
    private String extension;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

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

    public static final String COL_ID = "id";

    public static final String COL_FILE_NAME = "file_name";

    public static final String COL_FILE_URL = "file_url";

    public static final String COL_FILE_SIZE = "file_size";

    public static final String COL_EXTENSION = "extension";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LAST_UPDATE_TIME = "last_update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_LAST_UPDATE_BY = "last_update_by";
}