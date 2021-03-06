package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.RoleDto;
import cn.keovi.crm.po.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @ClassName RoleMenuService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/01/25/20:57
 */
public interface RoleMenuService extends IService<RoleMenu>{


        void updateRoles(RoleDto roledto);

        void saveRoles(RoleDto roledto);

        void deleteRoles(JsonNode id);
    }
