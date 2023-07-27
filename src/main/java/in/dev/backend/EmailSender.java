//import java.net.PasswordAuthentication;
package in.dev.backend;
import java.util.Properties;
import javax.mail.PasswordAuthentication;


import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	String to = "";
	String msg = "";
	
//	public EmailSender(String to) {
//		this.to=to;
//	}
	public EmailSender(String to,String msg) {
		this.to = to;
		this.msg = msg;
	}
    public void send() throws Exception {
//    	this.to = to;
//		this.msg = msg;
    	msg = msg!=null?msg: "Hello";
        final String from = "devchauhanexp@gmail.com";
        final String password = "yttbtmgezoteixbe";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("YOUR OTP FOR REGISTRATION");
            
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            
//            MimeBodyPart attechmentBodyPart = new MimeBodyPart();
//            attechmentBodyPart.attachFile(new File("File Path"));
//            multipart.addBodyPart(attechmentBodyPart);
//        

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





