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

@WebServlet("/CrearPartida")
public class CrearPartidaNueva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	ListaPartidasEJB<DamasOnline> listaPartidasEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			Partida partida = partidaEJB.getPartidaNoFinalizadaByIdUsuario(usuario.getId());
			
			if (partida == null) {

				partidaEJB.addPartidaByIdUsuario(usuario.getId());

				partida = partidaEJB.getPartidaNoFinalizadaByIdUsuario(usuario.getId());

				Client cliente = ClientBuilder.newClient();

				WebTarget target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/create/"
						+ partida.getId() + "/" + usuario.getId());

				target.request().get();

				response.sendRedirect("Sala?id=" + partida.getId());

			} else {
				
				response.sendRedirect("Jugar");
			}
		}
	}

}
