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

@WebServlet("/Estadisticas")
public class Estadisticas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String page = (String) request.getAttribute("page");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || page==null) {

				response.sendRedirect("Jugar");

			} else {

				Usuario jugador = new Usuario();

				jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));

				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/FichaEstadisticas.jsp");

					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);

					rs.forward(request, response);
				}
			}
		}

	}

}
