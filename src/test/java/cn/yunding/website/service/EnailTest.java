package cn.yunding.website.service;


import cn.yunding.website.BaseTest;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author supperhui
 */
public class EnailTest extends BaseTest {
    public static final String SMTPSERVER="smtp.qq.com";
    public static final String SMTPPORT = "465";
    public static final String ACCOUT = "724899612@qq.com";
    public static final String PWD = "nhechgfdpybvbfbf";

    @Test
    public void test() throws Exception {
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol","smtp");//(Java Mail规范要求)
        properties.setProperty("mail.smtp.host",SMTPSERVER);//发件人的邮件的SMTP服务器地址
        properties.setProperty("mail.smtp.port",SMTPPORT);
        properties.setProperty("mail.stmp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.stmp.auth","true");//需要请求认证
        properties.setProperty("mail.stmp.ssl.enable","true");//开启ssl
        //根据邮件配置建立会话，注意session别导错包
        Session session=Session.getDefaultInstance(properties);
        //开启debug模式，可以看到很多详细的输入日志
        session.setDebug(true);
        //建立邮件
        MimeMessage mimeMessage=createEmail(session);
        //获得传输通道
        Transport transport=session.getTransport();
        transport.connect(SMTPSERVER,ACCOUT,PWD);
        //连接，并发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }
    public MimeMessage createEmail(Session session) throws Exception {
        //根据会话创建邮件
        MimeMessage mimeMessage=new MimeMessage(session);
        //address邮件地址，presonal邮件昵称，charset编码方式
        InternetAddress receiveAddress=new InternetAddress(ACCOUT,"458480963@qq.com","utf-8");
        //设置邮件接收方
        mimeMessage.setRecipient(Message.RecipientType.TO,receiveAddress);
        //设置邮件标题
        mimeMessage.setSubject("邮件标题","utf-8");
        mimeMessage.setText("我是嘎嘎辉");
        mimeMessage.setSentDate(new Date());
        //保存设置
        mimeMessage.saveChanges();
        return mimeMessage;
    }
}
