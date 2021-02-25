package com.tanx.music.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
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
 *  复杂邮件
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午1:00
 */
@Service
public class MailSendComplexImpl implements IMailService,Callable<String>{

    private static String username;
    private static String password;
    private static String host;
    private static String returnMessage;
    private static String encoding;
    private static String FromUsername = null;
    private static String Text = null;
    private static String Subject = null;
    private static JavaMailSenderImpl sender;

    private static JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

    private MailSendComplexImpl() throws IOException {
        Properties properties = new Properties();
        InputStream stream = new ClassPathResource("configure.properties").getInputStream();
        properties.load(stream);
        username = properties.getProperty("mail.username");
        password = properties.getProperty("mail.password");
        encoding = properties.getProperty("mail.encoding");
        host = properties.getProperty("mail.host");
        if (sender == null) {
            sender = javaMailSender();
        }
    }

    static class MailSend{
        private static MailSendComplexImpl  MAIL_SEND_COMPLEX;

        static {
            try {
                MAIL_SEND_COMPLEX = new MailSendComplexImpl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String call() throws Exception {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(new InternetAddress(username,"music",encoding));
        mimeMessageHelper.setTo(FromUsername);
        mimeMessageHelper.setSubject(Subject);
        mimeMessageHelper.setText(Text,true);
        sender.send(mimeMessage);
        return returnMessage;
    }

    @Override
    public String send() throws Exception {
        return call();
    }

    public static MailSendComplexImpl getInstance(String fromUsername,String text,String subject){
        FromUsername = fromUsername;
        Text = text;
        Subject = subject;
        return MailSend.MAIL_SEND_COMPLEX;
    }

    public static MailSendComplexImpl getInstance(String fromUsername,String text,String message,String subject){
        FromUsername = fromUsername;
        Text = text;
        returnMessage = message;
        Subject = subject;
        return MailSend.MAIL_SEND_COMPLEX;
    }
}
