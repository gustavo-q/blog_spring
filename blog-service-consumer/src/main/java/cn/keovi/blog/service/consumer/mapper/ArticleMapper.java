package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> pageList(BaseDto baseDto);
}