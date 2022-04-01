package cn.keovi.blog.service.consumer.controller;

import cn.keovi.blog.service.consumer.service.FilesService;
import cn.keovi.constants.Result;
import cn.keovi.crm.po.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName FilesController
 * @Description
 * @Author gustavo
 * @Date 2022/01/29/15:25
 */

@Slf4j
@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    FilesService filesService;


    //上传文件
    @PostMapping("/saveFiles")
        public Result saveFiles(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "source")String source) {
        try {
            String filesPath = filesService.saveFiles(file,source);
            return Result.ok().data(200,filesPath);
        } catch (Exception e) {
            log.error("文件保存失败!", e);
            return Result.error(500, e.getMessage());

        }
    }

}
