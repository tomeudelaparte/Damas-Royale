package com.damasroyale.controlador;

import java.io.IOException;

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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	SessionEJB sessionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = request.getParameter("error");
		String registro = request.getParameter("registro");
		String activacion = request.getParameter("activacion");

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			sessionEJB.usuarioLogout(session);
		}

		RequestDispatcher rs = getServletContext().getRequestDispatcher("/Login.jsp");

		request.setAttribute("error", error);
		request.setAttribute("registro", registro);
		request.setAttribute("activacion", activacion);

		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");

		if (email == null || email.equals("") || contrasenya == null || contrasenya.equals("")) {

			response.sendRedirect("Login?error=true");

		} else {

			Usuario usuario = usuarioEJB.getUsuarioLogin(email.toLowerCase(), contrasenya);

			if (usuario == null) {

				response.sendRedirect("Login?error=true");

			} else {

				sessionEJB.usuarioLogin(session, usuario);

				response.sendRedirect("Portal");
			}
		}

	}

}
