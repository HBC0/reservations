package com.callray.reservation.utils;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailClient {
    // 获取发件人邮箱
    @Value("${spring.mail.username}")
    private String sender;

    // 获取发件人昵称
    @Value("${spring.mail.jndi-name}")
    private String name;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Resource
    private JavaMailSender mailSender;


    /**
     * @param to      收件人Email
     * @param subject 标题
     * @param content 内容
     */
    public void sendMail(String to, String subject, String content) throws Exception {
        logger.info("发送邮件");
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            throw new Exception("发送邮件失败:");
        }
    }

    /**
     * @param toEmail 收件人Email
     * @param code    验证码
     */
    public void sendCode(String toEmail, String code) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(name + '<' + sender + '>');
            helper.setTo(toEmail);
            helper.setSubject("验证码");
            String content = "【验证码】验证码为：" + code + " 。 验证码60s内有效，逾期作废。";
            helper.setText(content);
            mailSender.send(helper.getMimeMessage());
            logger.info("【验证码】验证码为：" + code);
        } catch (MessagingException e) {
            throw new Exception("发送邮件失败:");
        }

    }

}
