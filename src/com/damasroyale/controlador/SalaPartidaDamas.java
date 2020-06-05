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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.slf4j.LoggerFactory;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.PuntuacionEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

import ch.qos.logback.classic.Logger;

/**
 * Servlet que sirve como sala para que dos jugadores puedan realizar una partida de forma online.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Sala")
public class SalaPartidaDamas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Logger para almacenar los errores que pueda ocasionar el identificador de usuario de la petición.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SalaPartidaDamas.class);

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene el parametro identificador de la partida.
		String idPartida = request.getParameter("id");

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario == null) {

			response.sendRedirect("Login");

		} else {
			
			// Si idPartida es null o equivalente a un string vacío, reenvia al servlet ListaPartidasCreadas.
			if (idPartida == null || idPartida.equals("")) {

				response.sendRedirect("Jugar");

			} else {
				Partida partida = null;

				// Evita errores como que el identificador no sea un valor numérico.
				try {

					// Obtiene una partida no finalizada.
					partida = partidaEJB.getPartidaNoFinalizadaByID(Integer.valueOf(idPartida));

				} catch (Exception ex) {

					// Almacena en un log el error que pueda ocasionar.
					logger.error(ex.getMessage());
				}

				// Obtiene una partida no finalizada donde el usuario participa en ella,
				Partida usuarioPartida = partidaEJB.getPartidaNoFinalizadaByIdUsuario(usuario.getId());

				// Si partida es null o usuarioPartida es diferente a null y el identificador de un partida es diferente a la otra, reenvia al servler ListaPartidasCreadas.
				if (partida == null || usuarioPartida != null && usuarioPartida.getId() != partida.getId()) {

					response.sendRedirect("Jugar");

				} else {

					// Si el usuario es diferente al anfitrion y el segundo jugador está vacío.
					if (partida.getIdUsuario_A() != usuario.getId() && partida.getIdUsuario_B() == null) {

						// Setea el segundo jugador con el usuario.
						partida.setIdUsuario_B(usuario.getId());

						// Actualiza la partida.
						partidaEJB.updatePartida(partida);

						// Crea un cliente para realizar una llamada rest al servicio PartidaRest para añadir el usuario como oponente.
						Client cliente = ClientBuilder.newClient();

						WebTarget target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/setOponente/"
								+ partida.getId() + "/" + usuario.getId());

						target.request().get();
						
						// Realiza una llamada rest al servicio PartidaRest para asignarle el turno de la partida si le pertenece.
						target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/setTurnoOponente/"
								+ partida.getId() + "/" + usuario.getId());

						target.request().get();

					}

					// Si el usuario es igual al anfitrión.
					if (partida.getIdUsuario_A() == usuario.getId()) {

						// Obtiene el usuario oponente.
						Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_B());

						// Si oponente es diferente a null
						if (oponente != null) {

							// Obtiene los resultados del oponente.
							ArrayList<Resultado> resultadosOponente = resultadoEJB.getAllResultadoByIdUsuario(oponente.getId());

							// Obtiene la puntuación del oponente.
							int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

							// Setea el usuario y la puntuación.
							request.setAttribute("oponente", oponente);
							request.setAttribute("oponentePuntuacion", oponentePuntuacion);

						}
					}

					// Si el usuario es igual al segundo jugador.
					if (partida.getIdUsuario_B() == usuario.getId()) {

						// Obtiene los resultados del anfitrion.
						Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());
						ArrayList<Resultado> resultadosOponente = resultadoEJB.getAllResultadoByIdUsuario(oponente.getId());

						// Obtiene la puntuación del oponente.
						int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

						// Setea el oponente y la puntuación.
						request.setAttribute("oponente", oponente);
						request.setAttribute("oponentePuntuacion", oponentePuntuacion);

					}

					// Si el usuario es igual al anfitrion or es igual al segundo jugador.
					if (partida.getIdUsuario_A() == usuario.getId() || partida.getIdUsuario_B() == usuario.getId()) {

						// Obtiene la puntuación del usuario a partir de los resultados.
						ArrayList<Resultado> resultadosUsuario = resultadoEJB.getAllResultadoByIdUsuario(usuario.getId());
						int usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);
						
						// Obtiene el número de la sala.
						int sala = partidaEJB.getAllPartidaEnCurso().size();

						// Prepara una solicitud para mostrar un jsp.
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/SalaPartidaDamas.jsp");
						
						// Setea la el número de la sala, la partida, el usuario y su puntuación.
						request.setAttribute("sala", sala);
						request.setAttribute("partida", partida);
						request.setAttribute("usuario", usuario);
						request.setAttribute("usuarioPuntuacion", usuarioPuntuacion);

						// Reenvia al jsp.
						rs.forward(request, response);

					} else {
						
						// Reenvia al servlet ListaPartidasCreadas
						response.sendRedirect("Jugar");
					}
				}
			}
		}
	}
}
