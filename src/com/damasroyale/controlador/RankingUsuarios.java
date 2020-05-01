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
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;
import com.damasroyale.modelo.pojo.extras.Rank;
import com.damasroyale.modelo.utils.TopRankingUsuarios;

@WebServlet("/Ranking")
public class RankingUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	ResultadoEJB resultadoEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/RankingUsuarios.jsp");

			ArrayList<Usuario> usuarios = usuarioEJB.getAllUsuario();

			ArrayList<Rank> ranking = new ArrayList<Rank>();

			for (Usuario listaUsuario : usuarios) {

				ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(listaUsuario.getId());

				int puntuacion = puntuacionEJB.getPuntuacion(listaUsuario.getId(), resultados);

				Rank rank = new Rank(listaUsuario.getId(), listaUsuario.getNombre(), listaUsuario.getImagen(),
						puntuacion);

				ranking.add(rank);

			}

			ranking.sort(new TopRankingUsuarios());
			ranking.subList(10, ranking.size()).clear();

			request.setAttribute("usuario", usuario);
			request.setAttribute("ranking", ranking);

			rs.forward(request, response);
		}
	}

}
