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

@WebServlet("/Repeticion")
public class RepeticionPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

			if (id == null || idUsuario == null) {

				response.sendRedirect("Jugar");

			} else {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/RepeticionPartida.jsp");

				Partida partida = partidaEJB.getPartidaByID(Integer.valueOf(id));

				ArrayList<Movimiento> movimientos = movimientoEJB.getAllMovimientoByIdPartida(Integer.valueOf(id));

				usuario = usuarioEJB.getUsuarioByID(Integer.valueOf(idUsuario));
				
				ArrayList<Resultado> resultadosUsuario = resultadoEJB.getAllResultadoByIdUsuario(Integer.valueOf(idUsuario));
				int usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultadosUsuario);

				Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());
				
				ArrayList<Resultado> resultadosOponente = resultadoEJB.getAllResultadoByIdUsuario(oponente.getId());
				int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

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
