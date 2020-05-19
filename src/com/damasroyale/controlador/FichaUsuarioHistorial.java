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
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

import ch.qos.logback.classic.Logger;

@WebServlet("/Historial")
public class FichaUsuarioHistorial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(FichaUsuarioHistorial.class);
	
	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	PartidaEJB partidaEJB;

	@EJB
	ResultadoEJB resultadoEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		String id = request.getParameter("id");
		String tab = (String) request.getAttribute("tab");

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null || tab == null) {

				response.sendRedirect("Jugar");

			} else {
				Usuario jugador = null;
				
				try {
					
					jugador = usuarioEJB.getUsuarioByID(Integer.valueOf(id));
					
				} catch (Exception ex) {

					logger.error(ex.getMessage());
				}

				if (jugador == null) {

					response.sendRedirect("Jugar");

				} else {

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/FichaUsuarioHistorial.jsp");

					ArrayList<Usuario> usuarios = usuarioEJB.getAllUsuario();

					ArrayList<Partida> partidas = partidaEJB.getAllPartidaByIdUsuario(jugador.getId());
					ArrayList<Resultado> resultados = resultadoEJB.getAllResultadoByIdUsuario(jugador.getId());

					request.setAttribute("usuario", usuario);
					request.setAttribute("jugador", jugador);
					request.setAttribute("partidas", partidas);
					request.setAttribute("resultados", resultados);
					request.setAttribute("usuarios", usuarios);

					rs.forward(request, response);
				}
			}
		}

	}

}
