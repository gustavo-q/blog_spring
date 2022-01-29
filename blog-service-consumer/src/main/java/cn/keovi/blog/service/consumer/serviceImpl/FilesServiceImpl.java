package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Files;
import cn.keovi.blog.service.consumer.mapper.FilesMapper;
import cn.keovi.blog.service.consumer.service.FilesService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FilesServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService{

    @Override
    public String saveFiles(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return "files/" + flag + "_" + originalFilename;  // 返回结果 url
    }
}
