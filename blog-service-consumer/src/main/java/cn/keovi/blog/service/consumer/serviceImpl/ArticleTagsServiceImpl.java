package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.blog.service.consumer.service.ArticleTagsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.ArticleTagsMapper;
import cn.keovi.crm.po.ArticleTags;

/**
 * @ClassName ArticleTagsServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTags> implements ArticleTagsService {

}
