package cn.keovi.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentVo
 * @Description
 * @Author gustavo
 * @Date 2022/03/29/20:41
 */
@Data
public class CommentVo {

        private Long id;


        private String content;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        private String userName;

        private String avatar;

        private Integer isDelete;


        private List<Map> child;



}
