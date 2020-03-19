package com.damasroyale.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.ImagenEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Register")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	ImagenEJB imagenEJB;

	@EJB
	SessionEJB sessionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = request.getParameter("error");

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			response.sendRedirect("Inicio");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/Register.jsp");

			request.setAttribute("error", error);

			rs.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			response.sendRedirect("Inicio");

		} else {

			String nombre = request.getParameter("nombre");
			String contrasenya = request.getParameter("contrasenya");
			String email = request.getParameter("email");
			String imagen = imagenEJB.uploadImage(request);

			if (nombre == null || contrasenya == null || email == null || imagen == null || nombre.length() > 32
					|| contrasenya.length() > 64 || email.length() > 64) {

				response.sendRedirect("Register?error=true");

			} else {

				String usuarioID = usuarioEJB.getExistUsuario(nombre, email);

				if (usuarioID != null) {

					response.sendRedirect("Register?error=true");

				} else {

					Date date = new Date();

					Timestamp registro = new Timestamp(date.getTime());

					Usuario newUsuario = new Usuario(0, nombre, email, contrasenya, imagen, registro, "N");

					usuarioEJB.addUsuario(newUsuario);

					response.sendRedirect("Login?register=true");
				}

			}

		}

	}

}
