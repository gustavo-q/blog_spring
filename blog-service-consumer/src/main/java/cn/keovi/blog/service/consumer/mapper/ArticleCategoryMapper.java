package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.ArticleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName ArticleCategoryMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {
    List<ArticleCategory> pageList(BaseDto baseDto);

    long pageListCount(BaseDto baseDto);
}