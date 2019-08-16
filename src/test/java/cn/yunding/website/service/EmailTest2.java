package cn.yunding.website.service;

import cn.yunding.website.BaseTest;
import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author superhui
 * 邮件实现
 */
public class EmailTest2 extends BaseTest {
    @Test
    public void test() throws Exception{
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol","SMTP");
        properties.setProperty("mail.smtp.host","smtp.qq.com");
        //properties.setProperty("mail.smtp.port","465");
        properties.setProperty("mail.stmp.auto","true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //properties.put("mail.properties.smtp.starttls.enable","true");

        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.port", "465");

        properties.setProperty("mail.user","724899612@qq.com");
        properties.setProperty("mail.password","yydbusdvlwtobdeg");
        Authenticator authenticator=new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                String userName=properties.getProperty("mail.user");
                String password=properties.getProperty("mail.password");
                return new PasswordAuthentication(userName,password);
            }
        };
        //使用环境属性和授权信息。创建邮件会话
        Session mailSession=Session.getInstance(properties,authenticator);
        MimeMessage message=new MimeMessage(mailSession);
        mailSession.setDebug(true);
        InternetAddress from=new InternetAddress(
                properties.getProperty("mail.user"));
        message.setFrom(from);
        InternetAddress to=new InternetAddress("458480963@qq.com");
        message.setRecipient(Message.RecipientType.TO,to);
        message.setSubject("测试邮件");
        message.setContent("这是第一封测试邮件","text/html;charset=UTF-8");
        Transport.send(message);
    }
}
