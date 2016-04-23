package com.baidu.dsp.common.utils.email;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.config.ApplicationPropertyConfig;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件发送公共类
 *
 * @author modi
 * @version 1.0.0
 */
@Service
public class MailBean implements InitializingBean {

    protected static final Logger LOG = LoggerFactory.getLogger(MailBean.class);

    @Autowired
    private ApplicationPropertyConfig emailProperties;

    // mail sender
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    /**
     * 发送html邮件
     *
     * @throws MessagingException
     * @throws AddressException
     * @throws GeneralSecurityException 
     */
    public void sendHtmlMail(String from, String[] to, String title, String text)
            throws AddressException, MessagingException, GeneralSecurityException {

        long start = System.currentTimeMillis();

        //暂时注释，换个可以发邮件的
        /***
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "GBK");

        InternetAddress[] toArray = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toArray[i] = new InternetAddress(to[i]);
        }

        messageHelper.setFrom(new InternetAddress(from));
        messageHelper.setTo(toArray);
        messageHelper.setSubject(title);
        messageHelper.setText(text, true);
        mimeMessage = messageHelper.getMimeMessage();
        mailSender.send(mimeMessage);
        **/
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
     
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
     
        Session session = Session.getInstance(props);
     
        Message msg = new MimeMessage(session);
        msg.setSubject(title);
        msg.setText(text);
        msg.setFrom(new InternetAddress("709597311@qq.com"));
     
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "709597311@qq.com", "zzjaynlodnnbbbhg");
     
        transport.sendMessage(msg, new Address[] { new InternetAddress("709597311@qq.com") });
        transport.close();
        long end = System.currentTimeMillis();
        LOG.info("send mail start:" + start + " end :" + end);
    }

    /**
     * 设置邮箱host，用户名和密码
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        mailSender.setHost(emailProperties.getEmailHost());

        if (!StringUtils.isEmpty(emailProperties.getEmailUser())) {
            mailSender.setUsername(emailProperties.getEmailUser());
        }

        if (!StringUtils.isEmpty(emailProperties.getEmailPassword())) {
            mailSender.setPassword(emailProperties.getEmailPassword());
        }

        if (!StringUtils.isEmpty(emailProperties.getEmailPort())) {

            try {

                Integer port = Integer.parseInt(emailProperties.getEmailPort());
                mailSender.setPort(port);
            } catch (Exception e) {
                LOG.error(e.toString());
            }
        }
    }
    
    public static void main(String[] args) throws MessagingException, GeneralSecurityException {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
     
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
     
        Session session = Session.getInstance(props);
     
        Message msg = new MimeMessage(session);
        msg.setSubject("seenews 错误");
        StringBuilder builder = new StringBuilder();
        builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
        builder.append("\n页面爬虫错误");
        builder.append("\n时间 ");
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress("709597311@qq.com"));
     
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "709597311@qq.com", "zzjaynlodnnbbbhg");
     
        transport.sendMessage(msg, new Address[] { new InternetAddress("709597311@qq.com") });
        transport.close();
      }
}
