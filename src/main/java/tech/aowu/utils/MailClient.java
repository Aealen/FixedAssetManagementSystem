package tech.aowu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {
    //用于打印日志
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);//用当前类做参数表明打印日志时，将使用当前类名作为开头

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from; // 发件方的邮箱

    //参数依次为：收件方的邮箱、邮件主题、邮件内容
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            //用来设置mimemessage中的内容
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败："+e.getMessage());
        }
    }
}
