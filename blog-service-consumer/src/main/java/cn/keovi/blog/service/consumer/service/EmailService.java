package cn.keovi.blog.service.consumer.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @ClassName EmailService
 * @Description 邮箱
 * @Author gustavo
 * @Date 2021/12/25/16:38
 */
public interface EmailService {

    /**
     * 发送最简单的文本邮件
     */
    public void sendSimpleMail(String to);

}
