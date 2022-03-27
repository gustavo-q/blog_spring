package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.UserLikeMapper;
import cn.keovi.crm.po.UserLike;
import cn.keovi.blog.service.consumer.service.UserLikeService;
/**
 * @ClassName UserLikeServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/03/27/14:10
 */
@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike> implements UserLikeService{

}
