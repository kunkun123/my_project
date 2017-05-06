package com.kunkun.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kunkun.domain.Customer;

public class SendMail extends Thread{

	private Customer c;

	public SendMail(Customer c) {
		this.c = c;
	}
	//���ͼ����ʼ�����
	public void run() {
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");//ָ���ʼ����͵�Э�飬�����ǹ淶�涨��
			props.setProperty("mail.host", "smtp.163.com");//ָ�������������ĵ�ַ�������ǹ淶�涨��
//		props.setProperty("mail.debug", "true");//�ʼ����͵ĵ���ģʽ�������ǹ淶�涨��
			props.setProperty("mail.smtp.auth", "true");//������������������֤������������JavaMailʵ���й�
			Session session = Session.getInstance(props);
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("m13230944924_2@163.com"));
			message.setRecipients(Message.RecipientType.TO, c.getEmail());
			message.setSubject("���������վ�ļ����ʼ�");
			
			message.setContent("�װ��Ķ���"+c.getUsername()+"<br/>���ʹ����漤�������˻���<br/><a href='http://localhost:8080/netStore/servlet/ClientServlet?op=active&code="+c.getCode()+"'>������</a><br/>���ʼ���ϵͳ�Զ��������벻Ҫֱ�ӻظ���", "text/html;charset=UTF-8");
			message.saveChanges();
			
			Transport ts = session.getTransport();
			ts.connect("m13230944924_2", "liuyikun132");
			ts.sendMessage(message, message.getAllRecipients());
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
}
