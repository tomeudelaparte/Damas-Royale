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

@WebServlet("/Sala")
public class SalaPartidaDamas extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SalaPartidaDamas.class);

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

		String id = request.getParameter("id");
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || id.equals("")) {

				response.sendRedirect("Jugar");

			} else {
				Partida partida = null;
				
				try {
					partida = partidaEJB.getPartidaNoTerminadaByID(Integer.valueOf(id));

				} catch (Exception ex) {
					
					logger.error(ex.getMessage());
				}
				
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/SalaPartidaDamas.jsp");

				if (partida == null) {
					
					response.sendRedirect("Jugar");
					
				} else {
					
					if (partida.getIdUsuario_A() != usuario.getId() && partida.getIdUsuario_B() == null) {

						partida.setIdUsuario_B(usuario.getId());

						partidaEJB.updatePartida(partida);

						Client cliente = ClientBuilder.newClient();

						WebTarget target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/setOponente/"
								+ partida.getId() + "/" + usuario.getId());

						target.request().get();

						target = cliente.target("http://localhost:8080/Damas-Royale/PartidaRest/setTurnoOponente/"
								+ partida.getId() + "/" + usuario.getId());

						target.request().get();
					}

					if (partida.getIdUsuario_A() == usuario.getId()) {

						Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_B());

						if (oponente != null) {
							ArrayList<Resultado> resultadosOponente = resultadoEJB
									.getAllResultadoByIdUsuario(oponente.getId());

							int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

							request.setAttribute("oponente", oponente);
							request.setAttribute("oponentePuntuacion", oponentePuntuacion);

						}
					}

					if (partida.getIdUsuario_B() == usuario.getId()) {

						Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());
						ArrayList<Resultado> resultadosOponente = resultadoEJB
								.getAllResultadoByIdUsuario(oponente.getId());

						int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

						request.setAttribute("oponente", oponente);
						request.setAttribute("oponentePuntuacion", oponentePuntuacion);

					}

					if (partida.getIdUsuario_A() == usuario.getId() || partida.getIdUsuario_B() == usuario.getId()) {

						ArrayList<Resultado> resultadosUsuario = resultadoEJB
								.getAllResultadoByIdUsuario(usuario.getId());
						int usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);

						int sala = partidaEJB.getAllPartidaEnCurso().size();

						request.setAttribute("sala", sala);
						request.setAttribute("partida", partida);
						request.setAttribute("usuario", usuario);
						request.setAttribute("usuarioPuntuacion", usuarioPuntuacion);

						rs.forward(request, response);

					} else {

						response.sendRedirect("Jugar");
					}

				}

			}
		}

	}

}
