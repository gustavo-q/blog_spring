package cn.keovi.crm.dto;

import lombok.Data;

/**
 * @author TZJ
 * 分页参数
 * @description CrmReqGatherListDto
 * @date 2021-09-21 10:13:49
 */
@Data
public class PageDto {

    //条数
    private Integer pageSize = 10;

    //页码
    private Integer pageNum = 1;

    private Integer startIndex;

    public Integer getStartIndex() {
        if (pageNum == null || pageSize == null){
            return 0;
        }
        return (pageNum - 1) * pageSize;
    }
}
