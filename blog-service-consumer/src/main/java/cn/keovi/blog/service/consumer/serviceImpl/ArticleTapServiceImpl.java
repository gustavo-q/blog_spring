package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.ArticleTapMapper;
import cn.keovi.crm.po.ArticleTap;
import cn.keovi.blog.service.consumer.service.ArticleTapService;
/**
 * @ClassName ArticleTapServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class ArticleTapServiceImpl extends ServiceImpl<ArticleTapMapper, ArticleTap> implements ArticleTapService{

}
