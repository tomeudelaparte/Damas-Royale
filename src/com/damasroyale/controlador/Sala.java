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
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Sala")
public class Sala extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;
	
	@EJB
	PuntuacionEJB puntuacionEJB;

	@EJB
	PartidaEJB partidaEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String id = request.getParameter("id");
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			if (id == null) {

				response.sendRedirect("Jugar");

			} else {
				
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Partida.jsp");
				
				Partida partida = partidaEJB.getPartidaByID(Integer.valueOf(id));

				if(partida.getIdUsuario_A() != usuario.getId() && partida.getIdUsuario_B() == null) {
					
					partida.setIdUsuario_B(usuario.getId());
					
					partidaEJB.updatePartida(partida);
					
					Usuario oponente = usuarioEJB.getUsuarioByID(partida.getIdUsuario_A());
					ArrayList<Resultado> resultadosOponente = partidaEJB.getAllResultadoByIdUsuario(oponente.getId());

					int oponentePuntuacion = puntuacionEJB.getPuntuacion(oponente.getId(), resultadosOponente);

					request.setAttribute("oponente", oponente);
					request.setAttribute("oponentePuntuacion", oponentePuntuacion);
				} 
				
				if(partida.getIdUsuario_A() == usuario.getId() || partida.getIdUsuario_B() == usuario.getId()) {
					ArrayList<Resultado> resultados = partidaEJB.getAllResultadoByIdUsuario(usuario.getId());
					int usuarioPuntuacion = puntuacionEJB.getPuntuacion(usuario.getId(), resultados);
					
					int sala = partidaEJB.getAllPartidaEnCurso().size() +1;
	
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
