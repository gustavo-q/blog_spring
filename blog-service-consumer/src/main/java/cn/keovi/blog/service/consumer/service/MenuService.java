package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/18/16:08
 */
public interface MenuService extends IService<Menu>{


    void updateMenu(Menu menu);
}
