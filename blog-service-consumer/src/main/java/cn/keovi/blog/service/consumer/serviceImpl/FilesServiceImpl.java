package cn.keovi.blog.service.consumer.serviceImpl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import io.minio.MinioClient;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Files;
import cn.keovi.blog.service.consumer.mapper.FilesMapper;
import cn.keovi.blog.service.consumer.service.FilesService;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

import static cn.keovi.constants.Minio.*;


/**
 * @ClassName FilesServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {

    @Override
    public String saveFiles(MultipartFile file){
        String url2=null;
            try {
                String bucket = "public";
                // 参数为：图床，账号，密码
                // 创建桶
                makeBucket(bucket);
                setBucketPolicy(bucket, "read-write");
                //参数为：文件夹，要存成的名字，要存的文件
                String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
                // 定义文件的唯一标识（前缀）
                String flag = IdUtil.fastSimpleUUID();
                String rootFilePath =flag + "_" + originalFilename;  // 获取上传的路径
                 url2 = uploadInputStream(bucket, rootFilePath, file.getInputStream());
                System.out.println(url2);
            }catch (Exception e){
                e.printStackTrace();
            }
            return url2;
    }
}
