package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.blog.service.consumer.service.TagsService;
import cn.keovi.crm.po.Tags;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.TagsMapper;

/**
 * @ClassName TagsServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

}
