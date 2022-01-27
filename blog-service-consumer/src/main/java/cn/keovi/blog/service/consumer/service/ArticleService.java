package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleService extends IService<Article>{


        List<Article> pageList(BaseDto baseDto);

    long pageListCount(BaseDto baseDto);
}
