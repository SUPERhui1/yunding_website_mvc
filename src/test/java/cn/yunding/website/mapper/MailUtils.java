package cn.yunding.website.mapper;

/**
 * @author superhui
 */
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtils {
    /**
     * 发送邮件
     *
     * @param email    目标邮件地址
     * @param title    邮件标题
     * @param emailMsg 邮件正文
     * @return 是否发送成功
     */
    public static boolean sendMail(String email, String title, String emailMsg) {
        try {
            // 1.创建一个程序与邮件服务器会话对象 Session

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "SMTP");
            props.setProperty("mail.host", "smtp.mxhichina.com");
            props.setProperty("mail.smtp.auth", "true");// 指定验证为true

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.port", "465");

            // 创建验证器
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("yunding_bbs@qiyubing.cn", "Yubing.0616");
                }
            };

            Session session = Session.getInstance(props, auth);
            session.setDebug(true);

            // 2.创建一个Message，它相当于是邮件内容
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("yunding_bbs@qiyubing.cn", "云顶论坛官方邮箱账户")); // 设置发送者

            message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

            message.setSubject(title);
            message.setContent(emailMsg, "text/html;charset=UTF-8");

            // 3.创建 Transport用于将邮件发送
            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}