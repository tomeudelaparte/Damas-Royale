package com.damasroyale.modelo.ejb;

import java.io.File;
import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Clase EJB para guardar la imagen del usuario al editar su cuenta.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class FileUploaderEJB {

	// Logger para almacenar los errores que pueda ocasionar.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FileUploaderEJB.class);

	// Directorio donde se guardaran las imagenes.
	private static final String UPLOAD_DIRECTORY = "media";

	/**
	 * Sube la imagen al servidor y devuelve un nombre para la imagen.
	 * 
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String uploadFile(HttpServletRequest request) {

		// Directorio.
		String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		// Prepara el directorio.
		File uploadDir = new File(uploadPath);

		// Si el directorio no existe lo crea.
		if (!uploadDir.exists()) {
			
			uploadDir.mkdir();
		}

		String fileName = null;

		// Obtiene el nombre que se encuentra en la request.
		try {
			for (Part part : request.getParts()) {

				fileName = getFileName(part);

				// Si el nombre es null o equivale a un string vacío.
				if (fileName == null || fileName.equals("")) {

					fileName = null;

				} else {

					// Crea un nombre con un string aleatorio.
					fileName = "img" + RandomStringUtils.random(15, true, true);
				}

				// Escribe el archivo en el directorio.
				part.write(uploadPath + File.separator + fileName);
			}
			
		} catch (IllegalStateException | IOException | ServletException ex) {

			// Almacena en un log el error que pueda ocasionar.
			logger.error(ex.getMessage());
		}

		return fileName;
	}

	/**
	 * Obtiene el nombre del archivo guardado
	 * 
	 * @param part Part
	 * @return String
	 */
	private String getFileName(Part part) {
		
		for (String content : part.getHeader("content-disposition").split(";")) {
			
			if (content.trim().startsWith("filename"))
				
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		
		return null;
	}

}
