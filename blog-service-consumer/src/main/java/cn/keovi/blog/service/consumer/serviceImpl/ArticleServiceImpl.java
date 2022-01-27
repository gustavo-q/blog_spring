package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.ArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Article;
import cn.keovi.blog.service.consumer.mapper.ArticleMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
/**
 * @ClassName ArticleServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    ArticleCategoryService articleCategoryService;


    @Override
    public List<Article> pageList(BaseDto baseDto) {
        List<Article> articles = articleMapper.pageList(baseDto);
        if (CollectionUtil.isEmpty(articles)) return null;
        articles.forEach(article -> {
            ArticleCategory articleCategory = articleCategoryService.lambdaQuery().eq(ArticleCategory::getId, article.getCategoryId()).one();
            if (ObjectUtil.isNotEmpty(articleCategory))             article.setCategoryText(articleCategory.getType());
        });
        return articles;
    }

    @Override
    public long pageListCount(BaseDto baseDto) {
        return articleMapper.pageListCount(baseDto);
    }
}
