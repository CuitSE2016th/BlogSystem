package com.bs.ssh.common.email163;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;

/**
 * Create By ZZY on 2018/11/9
 */
public class MailUtil {
    public static final String FROM = "18382418120@163.com"; //发件人的email
    public static final String PWD = "18382418120zzyok"; //发件人密码--邮箱密码
    public static final int TIMELIMIT = 1000 * 60 * 60 * 24; //激活邮件过期时间24小时
    public static final String HOST = "smtp.163.com"; //163的smtp服务器
    public static final String SMTP = "smtp";

    /**
     *
     * @param size 指定生成随机数的位数
     * @return
     */
    public static String EmailCode(int size){

        StringBuffer buffer = new StringBuffer();

        String number = "0123456789";

        for (int i = 0; i < size; i++) {
            buffer.append(number.charAt(new Random().nextInt(9)));
        }

        return buffer.toString();

    }


    public static int codeMail(String email, String code) {
        //发送的邮箱内容
        String content = "<p>您正在注册验证，验证码:"+ code + "，请在15分钟内按页面提示提交验证码，切勿将验证码泄露于他人。</p>";
        //调用发送邮箱服务
        try {
            MailUtil.sendMail(email, "注册验证", content);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //邮件发送
    public static void sendMail(String email, String title, String content) throws Exception {

        Properties props = new Properties(); //加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", HOST);//存储发送邮件服务器的信息
        props.put("mail.smtp.port", 465);//设置端口
        props.put("mail.smtp.auth", "true");//同时通过验证
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(FROM));//设置发件人的地址
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(FROM)); //抄送
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//设置收件人,并设置其接收类型为TO
        message.setSubject(title);//设置标题
        //设置信件内容
        message.setContent(content, "text/html;charset=gbk"); //发送HTML邮件
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息
        //发送邮件
        Transport transport = session.getTransport(SMTP);
        transport.connect(FROM, PWD);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }
}

