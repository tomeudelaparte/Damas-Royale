package com.damasroyale.controlador;

import java.io.IOException;  
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Register")
public class RegistrarCuentaNueva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	SessionEJB sessionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = request.getParameter("error");

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			response.sendRedirect("Jugar");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/RegistrarCuentaNueva.jsp");

			request.setAttribute("error", error);

			rs.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			response.sendRedirect("Jugar");

		} else {

			String nombre = request.getParameter("nombre");
			String contrasenya = request.getParameter("contrasenya");
			String email = request.getParameter("email");

			if (nombre == null || contrasenya == null || email == null || nombre.length() > 32 || contrasenya.length() > 64 || email.length() > 64) {

				response.sendRedirect("Register?error=true");

			} else {

				String usuarioID = usuarioEJB.getUsuarioExistente(nombre, email);

				if (usuarioID != null) {

					response.sendRedirect("Register?error=true");

				} else {

					Date date = new Date();

					Timestamp registro = new Timestamp(date.getTime());

					Usuario newUsuario = new Usuario(0, nombre, email, contrasenya, "usuario.jpg", registro, false);

					usuarioEJB.addUsuario(newUsuario);

					response.sendRedirect("Login?register=true");
				}

			}

		}

	}

}
