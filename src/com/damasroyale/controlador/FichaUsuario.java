package com.damasroyale.controlador;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet que redirige a un servlet según la opción seleccionada en el menú, en la ficha de usuario, por el usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Ficha")
public class FichaUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;

	/**
	 * Método que recibe las peticiones GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene los parametros del identificador de usuario y la sección tab seleccionada.
		String id = request.getParameter("id");
		String tab = request.getParameter("tab");

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			// Si el identificador es null o equivale a un string vacío, reenvia al servlet ListaPartidasCreadas.
			if (id == null || id.equals("")) {

				response.sendRedirect("Jugar");

			} else {

				RequestDispatcher rs;

				// Si tab es null o tab equivale a un string vacío, redirige al servlet FichaUsuarioInformacion.
				if (tab == null || tab.equals("")) {

					response.sendRedirect("Ficha?id=" + id + "&tab=info");

				// Si tab equivalea info, redirige al servlet FichaUsuarioInformacion.
				} else if (tab.equals("info")) {

					// Prepara una solicitud para mostrar un jsp
					rs = getServletContext().getRequestDispatcher("/Informacion");

					// Setea el atributo tab con valor info.
					request.setAttribute("tab", "info");

					// Reenvia al jsp.
					rs.forward(request, response);

				// Si tab equivalea a stats, redirige al servlet FichaUsuarioEstadistica.
				} else if (tab.equals("stats")) {

					// Prepara una solicitud para mostrar un jsp
					rs = getServletContext().getRequestDispatcher("/Estadistica");

					// Setea el atributo tab con valor stats.
					request.setAttribute("tab", "stats");

					// Reenvia al jsp.
					rs.forward(request, response);

				// Si tab equivalea a history, redirige al servlet FichaUsuarioHistorial.
				} else if (tab.equals("history")) {

					// Prepara una solicitud para mostrar un jsp.
					rs = getServletContext().getRequestDispatcher("/Historial");

					// Setea el atributo tab como valor history.
					request.setAttribute("tab", "history");

					// Reenvia al jsp.
					rs.forward(request, response);

				} else {

					// Redirige al servlet FichaUsuarioInformacion.
					response.sendRedirect("Ficha?id=" + id + "&tab=info");

				}
			}
		}
	}
}
