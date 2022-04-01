package cn.keovi.blog.service.consumer.serviceImpl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.keovi.blog.service.consumer.service.UserService;
import cn.keovi.blog.service.consumer.session.LoginManager;
import cn.keovi.crm.po.User;
import cn.keovi.exception.ServiceException;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.crm.po.Files;
import cn.keovi.blog.service.consumer.mapper.FilesMapper;
import cn.keovi.blog.service.consumer.service.FilesService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import static cn.keovi.constants.Minio.*;


/**
 * @ClassName FilesServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFiles(MultipartFile file,String source){

        String url2=null;
        try {
            if (loginManager.getUserSession() == null) throw new ServiceException("登录失效!");
            url2=uploadFile(file);
            System.out.println(url2);



            //保存file表
            //名
            String originalFilename = file.getOriginalFilename();
            //后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //文件大小
            Double size = Double.valueOf(file.getSize());
            Files files = Files.builder()
                    .createBy(loginManager.getUserId())
                    .createTime(new Date())
                    .fileUrl(url2)
                    .fileName(originalFilename)
                    .fileSize(size)
                    .extension(suffix)
                    .build();
            save(files);

        }catch (Exception e){
                e.printStackTrace();
            }
            return url2;
    }




    //上传到服务器
    public String uploadFile(MultipartFile file) throws IOException, Exception{
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
        return uploadInputStream(bucket, rootFilePath, file.getInputStream());
    }
}
