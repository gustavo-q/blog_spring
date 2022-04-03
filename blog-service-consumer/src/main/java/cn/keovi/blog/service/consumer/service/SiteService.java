package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.po.Site;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SiteService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/04/03/14:42
 */
public interface SiteService extends IService<Site>{


        void visitor(HttpServletRequest request, HttpServletResponse response);
    }
