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
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Informacion")
public class Informacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	PuntuacionEJB puntuacionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String page = (String) request.getAttribute("page");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || page==null) {

				response.sendRedirect("Jugar");

			} else {

				Usuario jugador = new Usuario();

				jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));

				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/FichaInfomacion.jsp");

					ArrayList<Resultado> resultados = partidaEJB
							.getAllResultadoByIdUsuario(Integer.valueOf(jugador.getId()));
					
					int puntuacion = puntuacionEJB.getPuntuacion(jugador.getId(), resultados);
					int partidasJugadas = resultados.size();
					int partidasGanadas = puntuacionEJB.getPartidasGanadas(jugador.getId(), resultados);
					int partidasPerdidas = puntuacionEJB.getPartidasPerdidas(jugador.getId(), resultados);
					int partidasTablas = puntuacionEJB.getTablas(resultados);

					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);
					request.setAttribute("puntuacion", puntuacion);
					request.setAttribute("jugadas", partidasJugadas);
					request.setAttribute("ganadas", partidasGanadas);
					request.setAttribute("perdidas", partidasPerdidas);
					request.setAttribute("tablas", partidasTablas);

					rs.forward(request, response);
				}
			}
		}

	}

}
