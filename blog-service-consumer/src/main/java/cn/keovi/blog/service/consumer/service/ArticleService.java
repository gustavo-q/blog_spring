package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.ArticleDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleService extends IService<Article> {


    List<Map<String, Object>> pageList(BaseDto baseDto);

    long pageListCount(BaseDto baseDto);

    ArticleDto articleById(Long id);

    void addArticle(ArticleDto articleDto);

    List<Map<String,Object>> statisticalBlogByMonth();

    List<Map> getMyLoveList(Integer page, Integer showCount);

    Integer getMyLoveCount();

}
