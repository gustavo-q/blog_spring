package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Site;
import cn.keovi.blog.service.consumer.mapper.SiteMapper;
import cn.keovi.blog.service.consumer.service.SiteService;
/**
 * @ClassName SiteServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/27/0:15
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService{

}
