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

import org.slf4j.LoggerFactory;

import com.damasroyale.modelo.pojo.Usuario;

import ch.qos.logback.classic.Logger;

/**
 * Clase EJB para poder enviar correos de verificación a los usuarios que se han registrado.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class JavaMailEJB {

	// Logger para almacenar los errores que pueda ocasionar.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(JavaMailEJB.class);

	/**
	 * Envia un correo electrónico a un destinatario.
	 * 
	 * @param email     String
	 * @param remitente String
	 * @param asunto    String
	 * @param mensaje   String
	 */
	public void sendMail(String email, String remitente, String asunto, String mensaje) {

		// El servicio que utiliza
		String host = "smtp.gmail.com";

		// El puerto que utiliza
		int port = 587;

		// Cuenta del remitente
		String username = "damasroyale@gmail.com";
		String password = "Patata23";

		// Configuración de las propiedades
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.ssl.trust", host);

		// Inicia sesión en la cuenta del remitente
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// Añade las partes del mail para finalmente enviar el correo.
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

		} catch (Exception ex) {

			// Almacena en un log el error que pueda ocasionar.
			logger.error(ex.getMessage());
		}
	}

	/**
	 * Envia un correo de verificación a un usuario registrado.
	 * 
	 * @param usuario Usuario
	 * @param codigo  String
	 */
	public void sendActivacion(Usuario usuario, String codigo) {

		JavaMailEJB mail = new JavaMailEJB();

		// Información del correo que recibirá el destinatario

		String asunto = "Verificar cuenta - Damas Royale";
		
		String mensaje = "<div style='background-color:#f5f5f5;'>"
				+ "<br><br>"
				+ "<div style='text-align:center;margin-left:25vw;margin-right:25vw;border-radius: 10px;background-color:#FFFFFF;'>"
				+ "<br><br><br>"
				+ "<img src='http://damasroyale.tk/media/banner.png' width='500' style='margin-left:50px;margin-right:50px;'>"
				+ "<br><br>"
				+ "<h1>Hola " + usuario.getNombre()+",</h1>"
				+ "<h1>¡Te has registrado correctamente!</h1>"
				+ "<br><br>"
				+ "<a href='http://localhost:8080/Damas-Royale/Login?activation="+ codigo+"' style='text-decoration:none;'>"
				+ "<div style='background-color:darkred;text-align:center;border-radius:10px; margin-left:30%;margin-right:30%; padding:1px'>"
				+ "<h3 style=\"color:white\">Verificar cuenta</h3>"
				+ "</div></a>"
				+ "</p><br><br>"
				+ "<br></div>"
				+ "<br>"
				+ "<p style='text-align:center;'>Copyright &copy; 2020 Damas Royale by Tomeu de la Parte</p>"
				+ "<br>"
				+ "</div>";

		String remitente = "damasroyale@gmail.com";

		// Envia el correo electrónico
		mail.sendMail(usuario.getEmail(), remitente, asunto, mensaje);

	}

}
