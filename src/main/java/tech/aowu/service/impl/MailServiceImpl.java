package tech.aowu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName: MailServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/19 21:49
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public Boolean sendTextMail(String to, String subject, String content) {

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
         return false;
        }
    }

    @Override
    public Boolean sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            //邮件接收人,设置多个收件人地址
            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressTo);
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 发送重置密码的验证码
     * @param to
     * @param code
     * @return
     */
    @Override
    public Boolean sendResetPasswordCode(String to,String code){

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            //邮件接收人,设置多个收件人地址
            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressTo);
            message.setSubject("城科固定资产维修系统");
            //邮件内容，html格式
            String content="<h2>验证码:</h2>"+"<h1>"+code+"</h1> 验证码将在5min后过期,请尽快使用!";

            messageHelper.setText(content, true);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
