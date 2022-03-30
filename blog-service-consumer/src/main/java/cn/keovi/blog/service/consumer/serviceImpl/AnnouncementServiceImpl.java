package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.AnnouncementMapper;
import cn.keovi.crm.po.Announcement;
import cn.keovi.blog.service.consumer.service.AnnouncementService;
/**
 * @ClassName AnnouncementServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/30/14:37
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService{

}
