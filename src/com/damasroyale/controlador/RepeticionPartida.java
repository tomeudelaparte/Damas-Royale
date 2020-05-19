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

@WebServlet("/Repeticion")
public class RepeticionPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(RepeticionPartida.class);

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	ResultadoEJB resultadoEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	MovimientoEJB movimientoEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String idUsuario = request.getParameter("usuario");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || id.equals("") || idUsuario == null || idUsuario.equals("")) {

				response.sendRedirect("Jugar");

			} else {

				Partida partida = null;
				usuario = null;

				try {
					partida = partidaEJB.getPartidaByID(Integer.valueOf(id));

				} catch (Exception ex) {

					logger.error(ex.getMessage());
				}

				try {
					usuario = usuarioEJB.getUsuarioByID(Integer.valueOf(idUsuario));
				} catch (Exception ex) {

					logger.error(ex.getMessage());
				}

				if (partida == null) {

					response.sendRedirect("Ficha?id=" + idUsuario + "&tab=history");

				} else if (usuario == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/RepeticionPartida.jsp");

					ArrayList<Movimiento> movimientos = movimientoEJB.getAllMovimientoByIdPartida(partida.getId());

					int usuarioPuntuacion;
					int oponentePuntuacion;

					Usuario oponente;

					if (usuario.getId() == partida.getIdUsuario_A()) {

						ArrayList<Resultado> resultadosUsuario = resultadoEJB
								.getAllResultadoByIdUsuario(usuario.getId());
						usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);

						oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_B());

						ArrayList<Resultado> resultadosOponente = resultadoEJB
								.getAllResultadoByIdUsuario(oponente.getId());
						oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

					} else {

						usuario = usuarioEJB.getUsuarioByID(usuario.getId());
						ArrayList<Resultado> resultadosUsuario = resultadoEJB
								.getAllResultadoByIdUsuario(usuario.getId());
						usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);

						oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());

						ArrayList<Resultado> resultadosOponente = resultadoEJB
								.getAllResultadoByIdUsuario(oponente.getId());
						oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

						for (Movimiento movimiento : movimientos) {

							movimiento.setFila_origen(7 - movimiento.getFila_origen());
							movimiento.setColumna_origen(7 - movimiento.getColumna_origen());
							movimiento.setFila_destino(7 - movimiento.getFila_destino());
							movimiento.setColumna_destino(7 - movimiento.getColumna_destino());
						}
					}

					request.setAttribute("partida", partida);

					request.setAttribute("movimientos", movimientos);

					request.setAttribute("usuario", usuario);
					request.setAttribute("usuarioPuntuacion", usuarioPuntuacion);

					request.setAttribute("oponente", oponente);
					request.setAttribute("oponentePuntuacion", oponentePuntuacion);

					rs.forward(request, response);
				}

			}
		}

	}

}
