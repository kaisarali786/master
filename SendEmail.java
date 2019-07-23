package com.vit.intrack;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
  
public class SendEmail { 
	
	   public static void sendMail(Properties properties, String username, String password, String fromEmailAddress, String toEmailAddress, 													     String subject, String messageText) {
	      Session session = Session.getInstance(properties, new Authenticator() {
	         @Override
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });
	      try {
	         Message msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress(fromEmailAddress));
	         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
	         msg.setSubject(subject);
	         msg.setText(messageText);
	         Transport.send(msg);
	         System.out.println("Email sent to : " + toEmailAddress);
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	   }
	   
	   public static void main(String[] args) {
	      String host = "smtp.gmail.com"; 
	      String username = "";// Enter the User name
	      String password = "*******"; // Enter the email password
	      String fromEmailAddress = "";
	      String toEmailAddress = "";
	      String subject = "Test mail";
	      String messageText = "This is a sample message. Thank you.";
	      
	      //Set email properties
	      Properties properties = new Properties();
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.transport.protocol", "smtp");
	      //properties.put("mail.smtp.starttls.enable", "true");
	      
	      SendEmail.sendMail(properties, username, password, fromEmailAddress, toEmailAddress, subject, messageText);
	   }
} 
