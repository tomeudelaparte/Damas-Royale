package com.damasroyale.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Stat;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Estadistica")
public class Estadistica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PartidaEJB partidaEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String page = (String) request.getAttribute("page");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || page == null) {

				response.sendRedirect("Jugar");

			} else {

				Usuario jugador = new Usuario();

				jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));

				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/FichaEstadistica.jsp");

					ArrayList<Stat> estadistica = partidaEJB.getEstadisticaByIdUsuario(jugador.getId());
					
					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);
					request.setAttribute("estadistica", estadistica);

					rs.forward(request, response);
				}
			}
		}

	}

}
