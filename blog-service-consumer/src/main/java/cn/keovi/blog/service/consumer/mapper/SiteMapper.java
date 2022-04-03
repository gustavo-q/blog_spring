package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.po.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SiteMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/27/0:15
 */
public interface SiteMapper extends BaseMapper<Site> {
    List<Map> getLineData();
}