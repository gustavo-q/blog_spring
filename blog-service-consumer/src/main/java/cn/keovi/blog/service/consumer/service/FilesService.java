package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.po.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName FilesService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface FilesService extends IService<Files>{


        String saveFiles(MultipartFile files,String source) ;
    }
