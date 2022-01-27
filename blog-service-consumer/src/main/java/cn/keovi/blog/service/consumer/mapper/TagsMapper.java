package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName TagsMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface TagsMapper extends BaseMapper<Tags> {
    List<Tags> pageList(BaseDto baseDto);

    Long pageListCount(BaseDto baseDto);
}