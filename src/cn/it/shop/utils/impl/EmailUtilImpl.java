package cn.it.shop.utils.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import cn.it.shop.utils.EmailUtil;

@Component("emailUtil")
public class EmailUtilImpl implements EmailUtil {

	@Override
	public void sendEmail(String emailAddress, String id) {
		Properties prop = new Properties();
		Session session = null;
		Message message = null;
		Transport transport = null;
		try {
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.transport.protocol", "smtp");
			session = Session.getDefaultInstance(prop);
			session.setDebug(true);
			message = new MimeMessage(session);
			message.setSubject("����֪ͨ");
			message.setContent("���������̳ǹ������Ʒ�������ţ�" + id + "�Ѿ�֧���ɹ�", "text/html;charset=utf-8");
			message.setFrom(new InternetAddress("liuziyk@126.com"));
			transport = session.getTransport();
			transport.connect("smtp.126.com", "liuziyk", "yunkuan1996");
			transport.sendMessage(message, new InternetAddress[] { new InternetAddress(emailAddress) });
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

}
