package tech.aowu.service;

import tech.aowu.entity.vo.ResponseResult;

/**
 * @Description: TODO
 * @ClassName: MailService
 * @Author: Aealen
 * @Date: 2023/2/19 21:49
 */
public interface MailService {

    /**
     * 发送纯文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content  内容
     * @return void
     **/
    Boolean sendTextMail(String to, String subject, String content);


    /**
     * 发送带HTML的邮件
     * @param to 收件人
     * @param subject   主题
     * @param content   内容
     * @return void
     **/
    Boolean sendHtmlMail(String to, String subject, String content);


    Boolean sendResetPasswordCode(String to, String code);
}
