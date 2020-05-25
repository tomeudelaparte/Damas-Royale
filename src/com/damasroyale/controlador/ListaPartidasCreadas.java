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
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet que muestra la lista de partidas creadas con la posibilidad de crear o unirte a una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Jugar")
public class ListaPartidasCreadas extends HttpServlet {

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

			// Prepara una solicitud para mostrar un jsp.
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/ListaPartidasCreadas.jsp");

			// Obtiene todos los usuarios y todas las partidas en curso.
			ArrayList<Usuario> usuarios = usuarioEJB.getAllUsuario();
			ArrayList<Partida> partidas = partidaEJB.getAllPartidaEnCurso();

			// Setea el usuario, los usuarios y las partidas en curso.
			request.setAttribute("usuario", usuario);
			request.setAttribute("usuarios", usuarios);
			request.setAttribute("partidas", partidas);

			// Reenvia al jsp.
			rs.forward(request, response);
		}
	}

}
