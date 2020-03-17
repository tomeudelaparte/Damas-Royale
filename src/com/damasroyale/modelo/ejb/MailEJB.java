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

/**
 * Clase EJB para enviar correos a usuarios.
 * 
 * @author Tomeu
 *
 */
@Stateless
@LocalBean
public class MailEJB {

	/**
	 * Enviar un correo electrónico.
	 * 
	 * @param email     String con el mail del receptor.
	 * @param remitente String con el mail del remitente.
	 * @param asunto    String con el asunto del correo.
	 * @param mensaje   String con el mensaje del correo.
	 */
	public void sendMail(String email, String remitente, String asunto, String mensaje) {

		// Para realizar las conexiones smtp.
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

	/**
	 * Envia un correo de verificacion al usuario que se ha registrado.
	 * 
	 * @param usuario
	 * @param codigo
	 */
	public void sendVerificacion(Usuario usuario, String codigo) {

		MailEJB mail = new MailEJB();

		String asunto = "Activar cuenta - CalculaIMC";
		String mensaje = "Hola " + usuario.getNombre()
				+ ", pulse el enlace para activar la cuenta: http://localhost:8080/DamasRoyale/Login?activation="
				+ codigo;

		String remitente = "dwesaplicaciontomeu@gmail.com";

		mail.sendMail(usuario.getEmail(), remitente, asunto, mensaje);

	}

}