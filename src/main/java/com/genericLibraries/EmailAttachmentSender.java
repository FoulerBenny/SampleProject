package com.genericLibraries;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentSender {
	 
    public static void sendEmailWithAttachments(final Map<String, String> map, List<String> attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", map.get("host"));
        properties.put("mail.smtp.port", map.get("port"));
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.class","java.net.ssl.SSLSocketFactory");
        properties.put("mail.user", map.get("mailFrom"));
        properties.put("mail.password", map.get("password"));
 
        try
        {
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(map.get("mailFrom"), map.get("password"));
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(map.get("mailFrom")));
       
        
        InternetAddress[] toAddresses = new InternetAddress[map.get("mailTo").split(",").length];
        for (int i =0;i<map.get("mailTo").split(",").length;i++)
        {
        	toAddresses[i]=new InternetAddress(map.get("mailTo").split(",")[i]);
        }
        
        if(map.get("mailTo").split(",").length!=0)
        {
        	msg.setRecipients(Message.RecipientType.TO, toAddresses);
        }
        
        
        InternetAddress[] ccAddresses = new InternetAddress[map.get("mailCc").split(",").length];
        
        for (int i =0;i<map.get("mailCc").split(",").length;i++)
        {
        	ccAddresses[i]=new InternetAddress(map.get("mailCc").split(",")[i]);
        }
        
        if(map.get("mailCc").split(",").length!=0)
        {
        	msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        }
        
        
        
        msg.setSubject(map.get("subject"));
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(map.get("message"), "text/plain");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                
                try {
                	 //attachPart.attachFile(filePath);
                    DataSource source = new FileDataSource(filePath);

                    attachPart.setDataHandler(new DataHandler(source));

                    attachPart.setFileName(filePath);

                    
                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    
    catch(Exception e)
    {
    	System.out.println(" You might have entered wrong email information in the Configuration.properties file. Please recheck");
    }
    }
 
    /**
     * Test sending e-mail with attachments
     */
  //public static void main(String[] args) {
    	public static void sendEmail(String reportPath) {
	   
	   Map<String,String> map = new HashMap<String,String>();
        // SMTP info
        String host = UtilityClass.getPropertyData("host");
        String port = UtilityClass.getPropertyData("port");
        String mailFrom = UtilityClass.getPropertyData("mailFrom");
        String password = UtilityClass.getPropertyData("password");
	   
	   
        map.put("host", host);
        map.put("port", port);
        map.put("mailFrom", mailFrom);
        map.put("password", password);
        
        
 
        // message info
        String mailTo = UtilityClass.getPropertyData("mailTo");
        String mailCc = UtilityClass.getPropertyData("mailCc");
        String subject = UtilityClass.getPropertyData("subject");
        String message = UtilityClass.getPropertyData("message");
        
        map.put("mailTo", mailTo);
        map.put("mailCc", mailCc);
        map.put("subject", subject);
        map.put("message", message);
 
        // attachments
       //String attachFile = "E:/LeEco/Automation/Framework_India/LeMall/test-output/ExtentReport/LeMallIndia-PC-16122016_034807.html";
      System.out.println(reportPath);
        String attachFile =  reportPath;
        List<String> attachFiles = new ArrayList<String>();
        attachFiles.add(attachFile);
 
        try {
        	if(!map.get("mailTo").equals("")||map.get("mailTo")!=null)
        	{
        		 sendEmailWithAttachments(map, attachFiles);
        	            System.out.println("Email sent.");
        	}
           
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}