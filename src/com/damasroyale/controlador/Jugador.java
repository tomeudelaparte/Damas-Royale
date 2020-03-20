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

import com.damasroyale.modelo.ejb.EstadisticaEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Estadistica;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Jugador")
public class Jugador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	EstadisticaEJB estadisticaEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null) {

				response.sendRedirect("Jugar");

			} else {

				Usuario jugador = new Usuario();

				jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));

				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/Jugador.jsp");

					Estadistica estadistica = estadisticaEJB.getEstadisticaByUsuarioID(jugador);

					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);
					request.setAttribute("estadistica", estadistica);

					rs.forward(request, response);
				}
			}
		}

	}

}
