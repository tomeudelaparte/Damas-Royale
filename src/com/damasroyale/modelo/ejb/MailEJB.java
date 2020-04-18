package com.damasroyale.modelo.ejb;

import java.util.Properties; 

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class MailEJB {

	public void sendMail(String email, String remitente, String asunto, String mensaje) {

		String host = "smtp.gmail.com";
		int port = 587;
		String username = "dwesaplicaciontomeu@gmail.com";
		String password = "Ageofempires2";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.ssl.trust", host);

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendActivacion(Usuario usuario, String codigo) {

		MailEJB mail = new MailEJB();

		String asunto = "Activar cuenta - Damas Royale";
		String mensaje = "<img src='http://localhost:8080/Damas-Royale/media/banner.png'><br><br>Hola " + usuario.getNombre()
				+ ", pulse el enlace para activar la cuenta: http://localhost:8080/Damas-Royale/Login?activation="
				+ codigo;

		String remitente = "dwesaplicaciontomeu@gmail.com";

		mail.sendMail(usuario.getEmail(), remitente, asunto, mensaje);

	}

}