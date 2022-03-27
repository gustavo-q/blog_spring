package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.MessageMapper;
import cn.keovi.crm.po.Message;
import cn.keovi.blog.service.consumer.service.MessageService;
/**
 * @ClassName MessageServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/28/0:46
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService{

}
