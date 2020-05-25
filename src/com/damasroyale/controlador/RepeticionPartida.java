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

import com.damasroyale.modelo.ejb.MovimientoEJB;
import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

import ch.qos.logback.classic.Logger;

/**
 * Servlet que muestra la repetición de un partida, el usuario puede visualizar los movimientos de forma manual.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Repeticion")
public class RepeticionPartida extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Logger para almacenar los errores que pueda ocasionar el identificador de usuario de la petición.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RepeticionPartida.class);

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
	
	// EJB para utilizar las funciones de movimiento.
	@EJB
	MovimientoEJB movimientoEJB;

	/**
	 * Método que recibe las peticiones GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene los parametros con el identificador de la partida y el identificador del usuario de la ficha.
		String idPartida = request.getParameter("id");
		String idUsuario = request.getParameter("usuario");

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			// Si idPartida o idUsuario es null o equivalentea un string vacío, reenvia al servlet ListaPartidasCreadas.
			if (idPartida == null || idPartida.equals("") || idUsuario == null || idUsuario.equals("")) {

				response.sendRedirect("Jugar");

			} else {

				Partida partida = null;
				
				usuario = null;

				// Evita errores como que el identificador no sea un valor numérico.
				try {
					
					// Obtiene la partida con el identificador.
					partida = partidaEJB.getPartidaByID(Integer.valueOf(idPartida));

				} catch (Exception ex) {

					// Almacena en un log el error que pueda ocasionar.
					logger.error(ex.getMessage());
				}

				// Evita errores como que el identificador no sea un valor numérico.
				try {
					
					// Obtiene el usuario con el identificador
					usuario = usuarioEJB.getUsuarioByID(Integer.valueOf(idUsuario));
					
				} catch (Exception ex) {

					// Almacena en un log el error que pueda ocasionar.
					logger.error(ex.getMessage());
				}

				// Si partida es null, reenvia al servlet FichaUsuarioHistorial con el usuario correspondiente.
				if (partida == null) {

					response.sendRedirect("Ficha?id=" + idUsuario + "&tab=history");

				// Si usuario es null, reenvia al servlet ListaPartidasCreadas. 
				} else if (usuario == null) {

					response.sendRedirect("Jugar");

				} else {

					Usuario oponente;
					
					// Obtiene todos los movimientos de la partida.
					ArrayList<Movimiento> movimientos = movimientoEJB.getAllMovimientoByIdPartida(partida.getId());

					// Si el usuario es igual al anfritrión.
					if (usuario.getId() == partida.getIdUsuario_A()) {
						
						// Obtiene el usuario oponente.
						oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_B());

					} else {

						// Obtiene el usuario oponente.
						oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());
						
						// Invierte cada movimiento para mostrar los movimientos desde la perspectiva del usuario de la ficha.
						for (Movimiento movimiento : movimientos) {

							movimiento.setFila_origen(7 - movimiento.getFila_origen());
							movimiento.setColumna_origen(7 - movimiento.getColumna_origen());
							movimiento.setFila_destino(7 - movimiento.getFila_destino());
							movimiento.setColumna_destino(7 - movimiento.getColumna_destino());
						}
					}
					
					// Obtiene los resultados y la puntuación del usuario.
					ArrayList<Resultado> resultadosUsuario = resultadoEJB.getAllResultadoByIdUsuario(usuario.getId());
					int usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);
					
					// Obtiene los resultados y la puntuación del usuario oponente.
					ArrayList<Resultado> resultadosOponente = resultadoEJB.getAllResultadoByIdUsuario(oponente.getId());
					int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);
					
					// Prepara una solicitud para mostrar un jsp.
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/RepeticionPartida.jsp");
					
					// Setea la partida, los movimientos, los usuarios participantes y su puntuación.
					request.setAttribute("partida", partida);

					request.setAttribute("movimientos", movimientos);

					request.setAttribute("usuario", usuario);
					request.setAttribute("usuarioPuntuacion", usuarioPuntuacion);

					request.setAttribute("oponente", oponente);
					request.setAttribute("oponentePuntuacion", oponentePuntuacion);

					// Reenvia al jsp.
					rs.forward(request, response);
				}
			}
		}
	}
}
