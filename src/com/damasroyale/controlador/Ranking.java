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

import com.damasroyale.modelo.ejb.EstadisticaEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Rank;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
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

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/Ranking.jsp");

			ArrayList<Rank> ranking = estadisticaEJB.getRanking();

			request.setAttribute("usuario", usuario);
			request.setAttribute("ranking", ranking);

			rs.forward(request, response);
		}
	}

}
