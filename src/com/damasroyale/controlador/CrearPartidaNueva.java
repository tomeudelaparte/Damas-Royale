package com.damasroyale.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.damasroyale.modelo.ejb.ListaPartidasEJB;
import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.juego.DamasOnline;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet que permite a un usuario crear una partida nueva.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/CrearPartida")
public class CrearPartidaNueva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;
	
	// EJB para utilizar las funciones de partida.
	@EJB
	PartidaEJB partidaEJB;

	// EJB para utilizar las funciones de la lista de partidas creadas.
	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

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

			// Obtiene una partida no finalizada donde participe el usuario.
			Partida partida = partidaEJB.getPartidaNoFinalizadaByIdUsuario(usuario.getId());

			// Si no hay una partida existente. Si existe, reenvia al servlet ListaPartidasCreadas.
			if (partida == null) {

				// Añade una partida con el usuario como anfitrión
				partidaEJB.addPartidaByIdUsuario(usuario.getId());

				// Obtiene una partida no finalizada donde participe el usuario, en este caso, obtendrá la partida creada
				partida = partidaEJB.getPartidaNoFinalizadaByIdUsuario(usuario.getId());

				// Crea un cliente para realizar una llamada rest al servicio PartidaRest para crear una partida y añadirla a la lista de partidas en curso
				Client cliente = ClientBuilder.newClient();

				WebTarget target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/create/" + partida.getId() + "/" + usuario.getId());

				target.request().get();

				// Redirige al servlet SalaPartidaDamas con la partida correspondiente.
				response.sendRedirect("Sala?id=" + partida.getId());

			} else {
				
				// Reenvia al servlet ListaPartidasCreadas
				response.sendRedirect("Jugar");
			}
		}
	}

}
