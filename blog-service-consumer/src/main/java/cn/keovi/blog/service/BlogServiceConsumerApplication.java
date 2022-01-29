package cn.keovi.blog.service;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("cn.keovi.blog.service.consumer.mapper")
public class BlogServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceConsumerApplication.class, args);
        System.out.println("启动成功！！");
    }

}
