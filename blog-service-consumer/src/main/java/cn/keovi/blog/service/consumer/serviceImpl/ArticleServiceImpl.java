package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
import cn.keovi.blog.service.consumer.service.ArticleTagsService;
import cn.keovi.blog.service.consumer.service.TagsService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.constants.Result;
import cn.keovi.crm.dto.ArticleDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.ArticleCategory;
import cn.keovi.crm.po.ArticleTags;
import cn.keovi.crm.po.Tags;
import cn.keovi.crm.vo.ArticleVo;
import cn.keovi.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Article;
import cn.keovi.blog.service.consumer.mapper.ArticleMapper;
import cn.keovi.blog.service.consumer.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName ArticleServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private LoginManager loginManager;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Autowired
    TagsService tagsService;


    @Override
    public List<Map<String, Object>> pageList(BaseDto baseDto) {
        if (loginManager.getUserSession() == null) throw new ServiceException("登录失效");
        if (loginManager.getUserSession().getRoleId() != 1) baseDto.setId(loginManager.getUserId());
        List<ArticleVo> articles = articleMapper.pageList(baseDto);
        if (CollectionUtil.isEmpty(articles)) return null;
        List<Map<String, Object>> mapList = new ArrayList<>();
        articles.forEach(article -> {
            Map<String, Object> map = MapUtil.newHashMap();
            ArticleCategory articleCategory = articleCategoryService.lambdaQuery().eq(ArticleCategory::getId, article.getCategoryId()).one();
            if (ObjectUtil.isNotEmpty(articleCategory)) {
                BeanUtil.copyProperties(article, map);
                map.put("categoryText", articleCategory.getType());
            }
            mapList.add(map);
        });
        return mapList;
    }

    @Override
    public long pageListCount(BaseDto baseDto) {
        if (loginManager.getUserSession() == null) throw new ServiceException("登录失效");
        if (loginManager.getUserSession().getRoleId() != 1) baseDto.setId(loginManager.getUserId());
        return articleMapper.pageListCount(baseDto);
    }


    //查询文章
    @Override
    public ArticleDto articleById(Long id) {
        Article article = lambdaQuery().eq(Article::getId, id).one();
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article, articleDto);
        if (article.getTop() != null && article.getTop() == 1) articleDto.setTop(true);
        if (article.getAppreciation() != null && article.getAppreciation() == 1) articleDto.setAppreciation(true);
        if (article.getCommentEnabled() != null && article.getCommentEnabled() == 1) articleDto.setCommentEnabled(true);
        List<ArticleTags> list = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, id).list();
        if (CollectionUtil.isNotEmpty(list))
            articleDto.setTagList(list.stream().map(ArticleTags::getTagId).collect(Collectors.toList()));
        return articleDto;
    }


    //add
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(ArticleDto articleDto) {
        if (loginManager.getUserId() == null) throw new ServiceException("登录失效");
        Article article = new Article();
        if (articleDto.getId() != null) {
            article.setId(articleDto.getId());
            article.setLastUpdateBy(loginManager.getUserId());
            article.setLastUpdateTime(new Date());
        } else {
            article.setCreateBy(loginManager.getUserId());
            article.setCreateTime(new Date());
        }
        article.setStatus(articleDto.getStatus());

        //设置置顶，评论和赞赏
        if (articleDto.getTop()==null) article.setTop(0);
        if (articleDto.getCommentEnabled()==null) article.setCommentEnabled(0);
        if (articleDto.getAppreciation()==null) article.setAppreciation(0);
        article.setTop(articleDto.getTop() ? 1 : 0);
        article.setCommentEnabled(articleDto.getCommentEnabled() ? 1 : 0);
        article.setAppreciation(articleDto.getAppreciation() ? 1 : 0);



        article.setTitle(articleDto.getTitle());
        article.setCategoryId(articleDto.getCategoryId());
        article.setContent(articleDto.getContent());
        boolean b = saveOrUpdate(article);
        if (!b) throw new ServiceException("添加失败");
        //添加tags
        articleTagsService.lambdaUpdate().eq(ArticleTags::getArticleId, article.getId()).remove();
        if (CollectionUtil.isNotEmpty(articleDto.getTagList())) {
            for (Object i : articleDto.getTagList()) {
                ArticleTags articleTags = new ArticleTags();
                if (i instanceof Integer) {
                    articleTags.setTagId(((Integer) i).longValue());
                } else {
                    Tags tags = Tags.builder()
                            .createBy(loginManager.getUserId())
                            .createTime(new Date())
                            .status(0)
                            .tag(i.toString())
                            .build();
                    tagsService.save(tags);
                    articleTags.setTagId(tags.getId());
                }
                articleTags.setArticleId(article.getId());
                articleTagsService.save(articleTags);
            }
        }
    }


    //文章归档
    @Override
    public List<Map<String, Object>> statisticalBlogByMonth() {
        List<Map<String, Object>> mapList = articleMapper.statisticalBlogByMonth();
        mapList.forEach(map -> {
            map.put("year", map.get("time").toString().split("-")[0]);
            map.put("month", map.get("time").toString().split("-")[1]);
        });
        return mapList;
    }
}
