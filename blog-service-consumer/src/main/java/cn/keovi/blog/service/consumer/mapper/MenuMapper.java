package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName MenuMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/18/16:08
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectMenuByRoleId(Integer roleId);

    List<Menu> findList(BaseDto baseDto);

    Long findListCount(BaseDto baseDto);
}