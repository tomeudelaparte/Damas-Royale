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
import com.damasroyale.modelo.pojo.Rank;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;
import com.damasroyale.modelo.utils.TopRankingUsuarios;

/**
 * Servlet para mostrar los 10 mejores usuarios de la aplicación.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Ranking")
public class RankingUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;

	// EJB para utilizar las funciones de usuario.
	@EJB
	UsuarioEJB usuarioEJB;

	// EJB para utilizar las funciones de partida.
	@EJB
	PartidaEJB partidaEJB;

	// EJB para utilizar las funciones de resultado.
	@EJB
	ResultadoEJB resultadoEJB;

	// EJB para utilizar las funciones de puntuación.
	@EJB
	PuntuacionEJB puntuacionEJB;

	/**
	 * Método que recibe las peticiones GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			// Obtiene todos los usuarios
			ArrayList<Usuario> usuarios = usuarioEJB.getAllUsuario();

			// Obtiene una lista de todos los usuarios y su puntuación.
			ArrayList<Rank> ranking = new ArrayList<Rank>();

			for (Usuario listaUsuario : usuarios) {

				// Obtiene todos los resultados del usuario.
				ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(listaUsuario.getId());

				// Obtiene la puntuación en base a esos resultados.
				int puntuacion = puntuacionEJB.getPuntuacion(listaUsuario.getId(), resultados);

				// Crea un objeto Rank y lo añade a la lista.
				Rank rank = new Rank(listaUsuario.getId(), listaUsuario.getNombre(), listaUsuario.getImagen(), puntuacion);

				ranking.add(rank);
			}

			// Ordena la lista de usuarios de mayor a menor puntuación.
			ranking.sort(new TopRankingUsuarios());

			// Obtiene los 10 primeros usuarios de la lista.
			ranking.subList(10, ranking.size()).clear();

			// Prepara una solicitud para mostrar un jsp.
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/RankingUsuarios.jsp");
			
			// Setea el usuario y el ranking de usuarios.
			request.setAttribute("usuario", usuario);
			request.setAttribute("ranking", ranking);

			// Reenvia al jsp.
			rs.forward(request, response);
		}
	}

}
