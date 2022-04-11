package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.WxUserMapper;
import cn.keovi.crm.po.WxUser;
import cn.keovi.blog.service.consumer.service.WxUserService;
/**
 * @ClassName WxUserServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/04/10/23:20
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService{

}
