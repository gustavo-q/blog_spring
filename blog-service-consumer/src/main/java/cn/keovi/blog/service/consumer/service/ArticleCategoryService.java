package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.po.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleCategoryService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleCategoryService extends IService<ArticleCategory>{


        List<Map> getCategoryByArticleCount();

    }
