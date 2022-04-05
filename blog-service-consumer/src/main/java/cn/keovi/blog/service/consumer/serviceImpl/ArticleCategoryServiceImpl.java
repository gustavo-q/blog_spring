package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.map.MapUtil;
import cn.keovi.blog.service.consumer.service.ArticleService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.crm.po.Article;
import cn.keovi.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.ArticleCategoryMapper;
import cn.keovi.crm.po.ArticleCategory;
import cn.keovi.blog.service.consumer.service.ArticleCategoryService;
/**
 * @ClassName ArticleCategoryServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService{

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LoginManager loginManager;


    @Override
    public List<Map> getCategoryByArticleCount() {
        if (loginManager.getUserSession()==null) throw new ServiceException("登录失效");
        List<ArticleCategory> list = lambdaQuery().eq(ArticleCategory::getIsDelete, 0).list();
        List<Map> list1 = new ArrayList<>();
        list.forEach(articleCategory -> {
            if (loginManager.getUserSession().getRoleId() == 1 || loginManager.getUserSession().getRoleId() == 2) {
                Integer count = articleService.lambdaQuery().eq(Article::getIsDelete, 0).eq(Article::getStatus, 1)
                        .eq(Article::getCategoryId, articleCategory.getId()).count();
                if (count > 0) {
                    Map<String, Object> map = MapUtil.newHashMap();
                    map.put("name", articleCategory.getType());
                    map.put("value", count);
                    list1.add(map);
                }
            }else {
                Integer count = articleService.lambdaQuery().eq(Article::getIsDelete, 0).eq(Article::getStatus, 1)
                        .eq(Article::getCategoryId, articleCategory.getId()).eq(Article::getCreateBy,loginManager.getUserId()).count();
                if (count > 0) {
                    Map<String, Object> map = MapUtil.newHashMap();
                    map.put("name", articleCategory.getType());
                    map.put("value", count);
                    list1.add(map);
                }
            }
        });
        return list1;
    }
}
