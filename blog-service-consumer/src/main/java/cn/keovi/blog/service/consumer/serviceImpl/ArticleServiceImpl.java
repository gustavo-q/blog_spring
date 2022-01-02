package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.crm.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
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

    @Override
    public List<Article> pageList(BaseDto baseDto) {
        return  articleMapper.pageList(baseDto);

    }
}
