package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.UserDonate;
import cn.keovi.blog.service.consumer.mapper.UserDonateMapper;
import cn.keovi.blog.service.consumer.service.UserDonateService;
/**
 * @ClassName UserDonateServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class UserDonateServiceImpl extends ServiceImpl<UserDonateMapper, UserDonate> implements UserDonateService{

}
