package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.RoleDto;
import cn.keovi.crm.po.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName UserRoleService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface UserRoleService extends IService<UserRole>{


        List<RoleDto> getRoles();
    }
