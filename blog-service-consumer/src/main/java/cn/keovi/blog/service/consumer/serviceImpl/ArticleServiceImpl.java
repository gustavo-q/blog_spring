package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.keovi.blog.service.consumer.service.*;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.crm.dto.ArticleDto;
import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.*;
import cn.keovi.crm.vo.ArticleVo;
import cn.keovi.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.ArticleMapper;
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

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    @Autowired
    private UserLikeService userLikeService;


    @Override
    public List<Map<String, Object>> pageList(BaseDto baseDto) {
        if (loginManager.getUserSession() == null) throw new ServiceException("登录失效");
        if (loginManager.getUserSession().getRoleId() != 1 && loginManager.getUserSession().getRoleId() != 2)
            baseDto.setId(loginManager.getUserId());
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
        articleDto.setTop(article.getTop() == 1);
        articleDto.setAppreciation(article.getAppreciation() == 1);
        articleDto.setCommentEnabled(article.getCommentEnabled() == 1);
        //分类
        String type = articleCategoryService.lambdaQuery().eq(ArticleCategory::getId, article.getCategoryId()).one().getType();
        articleDto.setCategoryText(type);
        //tag
        List<ArticleTags> list = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, id).list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<Object> collect = list.stream().map(ArticleTags::getTagId).collect(Collectors.toList());
            articleDto.setTagList(collect);
            List<String> list1 = new ArrayList<>();
            collect.forEach(c -> {
                list1.add(tagsService.lambdaQuery().eq(Tags::getId, c).one().getTag());
            });
            articleDto.setTagListText(list1);
        }

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
        //发布前需要审核
        if (articleDto.getStatus() == 1) {  //发布
            article.setStatus(2);   //审核

        } else {
            article.setStatus(articleDto.getStatus());
        }
        //设置置顶，评论和赞赏
        if (articleDto.getTop() == null) article.setTop(0);
        if (articleDto.getCommentEnabled() == null) article.setCommentEnabled(0);
        if (articleDto.getAppreciation() == null) article.setAppreciation(0);
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

    @Override
    public List<Map> getMyLoveList(Integer page, Integer showCount) {
        //startIndex
        page = (page - 1) * showCount;
        if (loginManager.getUserId() == null) throw new ServiceException("登录失效");

        List<UserLike> list1 = userLikeService.lambdaQuery().eq(UserLike::getCreateBy, loginManager.getUserId())
                .eq(UserLike::getLike, 1).eq(UserLike::getIsDelete, 0).list();

        List<Long> collect = list1.stream().map(UserLike::getArticleId).collect(Collectors.toList());

        List<Article> articles = articleMapper.getMyLoveList(page, showCount, collect);


        ArrayList<Map> list = new ArrayList<>();
        articles.forEach(article -> {
            HashMap<String, Object> result = new HashMap<>();
            result.put("title", article.getTitle());
            result.put("content", article.getContent());
            result.put("blogViews", article.getViews());
            result.put("time", article.getCreateTime());
            result.put("id", article.getId());

            User user = userService.lambdaQuery().eq(User::getId, article.getCreateBy()).one();
            result.put("userName", user.getUsername());

            //评论数
            Integer count = commentService.lambdaQuery().eq(Comment::getTopicId, article.getId()).eq(Comment::getIsDelete, 0).count();
            result.put("discussCount", count);

            //标签
            List<ArticleTags> articleTags = articleTagsService.lambdaQuery().eq(ArticleTags::getArticleId, article.getId()).list();
            ArrayList<String> strings = new ArrayList<>();
            articleTags.forEach(articleTags1 -> {
                strings.add(tagsService.lambdaQuery().eq(Tags::getId, articleTags1.getTagId()).one().getTag());
            });
            result.put("tags", strings);
            list.add(result);
        });
        return list;
    }

    @Override
    public Integer getMyLoveCount() {
        if (loginManager.getUserId() == null) throw new ServiceException("登录失效");

        return userLikeService.lambdaQuery().eq(UserLike::getCreateBy, loginManager.getUserId())
                .eq(UserLike::getLike, 1).eq(UserLike::getIsDelete, 0).count();
    }
}
