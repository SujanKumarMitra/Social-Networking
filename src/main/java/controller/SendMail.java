package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			User user = (User) request.getSession().getAttribute("user");
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
 
			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
			    public PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication("interstellarsthe@gmail.com", "theInterstellars.");
			    }
			};
 
			Session session = Session.getInstance(properties, auth);
 
			// creates a new e-mail message
			Message msg = new MimeMessage(session);
 
			msg.setFrom(new InternetAddress("interstellarsthe@gmail.com"));
//			InternetAddress[] toAddresses = { new InternetAddress(user.getEmail()) };
//			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("webmaster");
			msg.setSentDate(new Date());
			Integer OTP = new Random().nextInt(10000);
			String message = "Hello, we received a request of password updatation."+
			"Your OTP is "+OTP;
			//add in session
			
			msg.setText(message);
			request.getSession().setAttribute("OTP", OTP);
			// sends the e-mail
			Transport.send(msg);
			response.sendRedirect("verify_otp.jsp");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
