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

import org.slf4j.LoggerFactory;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Stat;
import com.damasroyale.modelo.pojo.Usuario;

import ch.qos.logback.classic.Logger;

/**
 * Servlet que muestra la sección de las estadisticas de la ficha del usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Estadistica")
public class FichaUsuarioEstadistica extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Logger para almacenar los errores que pueda ocasionar el identificador de usuario de la petición.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FichaUsuarioEstadistica.class);

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene los parametros del identificador de usuario y la sección tab seleccionada.
		String id = request.getParameter("id");
		String tab = (String) request.getAttribute("tab");

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			// Si el identificador es null o tab es null, reenvia al servlet ListaPartidasCreadas.
			if (id == null || tab == null) {

				response.sendRedirect("Jugar");

			} else {
				
				Usuario jugador = null;

				// Evita errores como que el identificador no sea un valor numérico.
				try {
					
					// Obtiene el usuario de la ficha.
					jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));

				} catch (Exception ex) {

					// Almacena en un log el error que pueda ocasionar.
					logger.error(ex.getMessage());
				}
				
				// Si jugador es null, reenvia al servlet ListaPartidasCreadas.
				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {
					
					// Obtiene las estadisticas en base a sus partidas realizadas.
					ArrayList<Stat> estadistica = partidaEJB.getEstadisticaByIdUsuario(jugador.getId());
					
					// Prepara una solicitud para mostrar un jsp.
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/FichaUsuarioEstadistica.jsp");
					
					// Setea el usuario, el usuario de la ficha y sus estadisticas.
					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);
					request.setAttribute("estadistica", estadistica);

					// Reenvia al jsp.
					rs.forward(request, response);
				}
			}
		}

	}

}
