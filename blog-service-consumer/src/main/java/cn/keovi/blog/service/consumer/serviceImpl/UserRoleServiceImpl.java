package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.UserRole;
import cn.keovi.blog.service.consumer.mapper.UserRoleMapper;
import cn.keovi.blog.service.consumer.service.UserRoleService;
/**
 * @ClassName UserRoleServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

}
