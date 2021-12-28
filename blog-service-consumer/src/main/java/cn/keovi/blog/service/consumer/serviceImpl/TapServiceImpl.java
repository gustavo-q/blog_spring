package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Tap;
import cn.keovi.blog.service.consumer.mapper.TapMapper;
import cn.keovi.blog.service.consumer.service.TapService;
/**
 * @ClassName TapServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class TapServiceImpl extends ServiceImpl<TapMapper, Tap> implements TapService{

}
