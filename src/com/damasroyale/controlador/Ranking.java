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
import com.damasroyale.modelo.ejb.RankingEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Rank;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;
	
	@EJB
	PartidaEJB partidaEJB;
	
	@EJB
	PuntuacionEJB puntuacionEJB;
	
	@EJB
	RankingEJB rankingEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/Ranking.jsp");
			
			ArrayList<Usuario> usuarios = usuarioEJB.getAllUsuario();
						
			ArrayList<Rank> ranking = new ArrayList<Rank>();
			
			for (Usuario listaUsuario : usuarios) {
				
				ArrayList<Resultado> resultados =  partidaEJB.getAllResultadoByIdUsuario(Integer.valueOf(listaUsuario.getId()));
				
				int puntuacion = puntuacionEJB.getPuntuacion(listaUsuario.getId(), resultados);
								
				Rank rank = new Rank(listaUsuario.getId(), listaUsuario.getNombre(), listaUsuario.getImagen(), puntuacion);
								
				ranking.add(rank);
				
			}
			
			ranking.sort(rankingEJB);
			ranking.subList(10, ranking.size()).clear();

			request.setAttribute("usuario", usuario);
			request.setAttribute("ranking", ranking);

			rs.forward(request, response);
		}
	}

}
