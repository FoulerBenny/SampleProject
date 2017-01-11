package com.in.commonHelpers;


public class SendEmail {
	
	//public static void sendEmail(String reportPath) {
        // SMTP info
        String host = "smtp.letv.cn";
        String port = "25";
        String mailFrom = "jimcherian@le.com";
        String password = "Tavant@101";
 
        // message info
        String mailTo = "jimcherian@le.com";
        String mailCc = "divyabharathi@le.com,shreyaps@le.com";
        String subject = "Automation Status Report-LeMall PC site";
        String message = "Hi Team,"
	     		+ "\n"+"\t\t"+"Please find the attached Automation Report for LeMall-India PC site. The report needs to be open in chrome."
        		+"\nKindly contact Automation team for more clarificaiton "+
	    		 "\n\n"+"Regards"+"\n"+"Automation-India Team ,"+"\n"+"LeMall R&D";
 
        // attachments
       // String attachFile = "E:/LeEco/Automation/Framework_India/LeMall/test-output/ExtentReport/LeMallIndia-PC-16122016_034807.html";
        //String attachFile = reportPath;
    /*    List<String> attachFiles = new ArrayList<String>();
        attachFiles.add(attachFile);*/
 
/*        try {
        	EmailAttachmentSender.sendEmailWithAttachments(host, port, mailFrom, password, mailTo, mailCc,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
*/
	
}
