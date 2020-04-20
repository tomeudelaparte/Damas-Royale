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

import com.damasroyale.modelo.ejb.ActivacionEJB;
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
	
	@EJB
	ActivacionEJB activacionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = request.getParameter("error");
		String register = request.getParameter("register");
		String activation = request.getParameter("activation");

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario != null) {

			sessionEJB.usuarioLogout(session);
		}
		
		if (activation != null) {

			usuario = activacionEJB.checkActivacion(activation);

			if (usuario != null) {

				usuarioEJB.activateUsuario(usuario);

			}
		}

		RequestDispatcher rs = getServletContext().getRequestDispatcher("/Login.jsp");

		request.setAttribute("error", error);
		request.setAttribute("register", register);
		request.setAttribute("activation", activation);

		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");

		if (email == null || email.equals("") || contrasenya == null || contrasenya.equals("") || email.length() > 64 || contrasenya.length() > 64) {

			response.sendRedirect("Login?error=true");

		} else {

			Usuario usuario = usuarioEJB.getUsuarioLogin(email.toLowerCase(), contrasenya);

			if (usuario == null || usuario.isEstado() == false) {

				response.sendRedirect("Login?error=true");

			} else {

				sessionEJB.usuarioLogin(session, usuario);

				response.sendRedirect("Jugar");
			}
		}

	}

}
