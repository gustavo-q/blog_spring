package cn.keovi.blog.service.consumer.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Menu;
import cn.keovi.blog.service.consumer.mapper.MenuMapper;
import cn.keovi.blog.service.consumer.service.MenuService;
/**
 * @ClassName MenuServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/18/16:08
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

}
