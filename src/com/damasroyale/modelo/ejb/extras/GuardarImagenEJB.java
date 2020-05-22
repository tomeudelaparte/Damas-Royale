package com.damasroyale.modelo.ejb.extras;

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

@Stateless
@LocalBean
public class GuardarImagenEJB {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(GuardarImagenEJB.class);

	private static final String UPLOAD_DIRECTORY = "media";

	public String uploadImage(HttpServletRequest request) {

		String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		String fileName = null;

		try {
			for (Part part : request.getParts()) {

				fileName = getFileName(part);

				if (fileName == null || fileName.equals("")) {

					fileName = null;

				} else {

					fileName = "img" + RandomStringUtils.random(15, true, true);
				}

				part.write(uploadPath + File.separator + fileName);
			}
		} catch (IllegalStateException | IOException | ServletException ex) {

			logger.error(ex.getMessage());
		}

		return fileName;

	}

	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return null;
	}

}
