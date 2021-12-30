package cn.keovi.blog.service.consumer;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.keovi.blog.service.consumer.service.EmailService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class BlogServiceConsumerApplicationTests   {

    @Autowired
    private EmailService emailService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    //邮箱验证测试
    @Test
    void contextLoads() {
//        MailUtil.send("hutool@foxmail.com", "测试", "<h1>邮件来自Hutool测试</h1>", true, FileUtil.file("d:/aaa.xml"));
        emailService.sendSimpleMail("1574802397@qq.com");
    }

    //session测试
    @Test
    void sessionTests(){
//        httpServletRequest.getSession().setAttribute("123", "22123");
//        redisTemplate.opsForValue().set("test",
//                "123",
//                10,
//                TimeUnit.SECONDS);

//        SessionTemplate sessionTemplate = new SessionTemplate();
//        sessionTemplate.saveUserSession("123", UserSession.builder().id(12L ).build());
//        saveUserSession("123", UserSession.builder().id(12L).build());
    }

    @Test
    void password(){
        String password = "123";
        byte[] key = password.getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
        String macHex1 = mac.digestHex(password);
        System.out.println(macHex1);
    }

}
