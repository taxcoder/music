package com.tanx.music.service.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 *
 * 简单邮件
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午1:00
 */
@Service
public class MailSendSimpleImpl implements IMailService, Callable<String> {

    private static JavaMailSenderImpl sender;

    private static JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

    private static String username;
    private static String password;
    private static String host;
    private static String FromUsername = null;
    private static String Text = null;
    private static String Subject = null;



    private MailSendSimpleImpl() throws IOException {
        Properties properties = new Properties();
        InputStream stream = new ClassPathResource("configure.properties").getInputStream();
        properties.load(stream);
        username = properties.getProperty("mail.username");
        password = properties.getProperty("mail.password");
        host = properties.getProperty("mail.host");
    }

    static class MailSend{
        private static MailSendSimpleImpl  mailSendSimple;

        static {
            try {
                mailSendSimple = new MailSendSimpleImpl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String call() throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(FromUsername);
        mailMessage.setSubject(Subject);
        mailMessage.setText(Text);
        sender.send(mailMessage);
        return "邮件发送成功，30分钟内有效！";
    }

    @Override
    public String send() throws Exception {
        return call();
    }

    public static MailSendSimpleImpl getInstance(String fromUsername,String text,String subject){
        FromUsername = fromUsername;
        Text = text;
        Subject = subject;
        if (sender == null) {
            sender = javaMailSender();
        }
        return MailSendSimpleImpl.MailSend.mailSendSimple;
    }
}
