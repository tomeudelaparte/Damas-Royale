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

import com.damasroyale.modelo.ejb.ActivacionEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet para iniciar sesión en la aplicación, realiza una doble función, identifica y dejar de identificar a un usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Login")
public class LogInOutUsuario extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;

	// EJB para utilizar las funciones de usuario.
	@EJB
	UsuarioEJB usuarioEJB;

	// EJB para utilizar las funciones de activacion.
	@EJB
	ActivacionEJB activacionEJB;

	/**
	 * Método que recibe las peticiones GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene los parametros para realizar acciones y mostrar ciertos mensajes.
		String error = request.getParameter("error");
		String register = request.getParameter("register");
		String activation = request.getParameter("activation");
		String delaccount = request.getParameter("delaccount");

		// Si hay usuario existente, cierra la sesión.
		if (usuario != null) {

			sessionEJB.usuarioLogout(session);
		}
		
		// Si la activación no es null.
		if (activation != null) {

			// Comprueba que exista la activación.
			usuario = activacionEJB.checkActivacion(activation);

			// Si hay un usuario pendiente de verificación.
			if (usuario != null) {

				// Verifica el usuario activando la cuenta.
				usuarioEJB.activateUsuario(usuario);
			}
		}

		// Prepara una solicitud para mostrar un jsp.
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/LoginUsuario.jsp");
		
		// Setea el error, register y activation para mostrar ciertos mensajes.
		request.setAttribute("error", error);
		request.setAttribute("register", register);
		request.setAttribute("activation", activation);
		request.setAttribute("delaccount", delaccount);

		// Reenvia al jsp.
		rs.forward(request, response);
	}

	/**
	 * Método que recibe las peticiones POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene los parámetros introducidos por el usuario.
		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");

		// Si el email o la contraseña es null, o equivale a un string vacío o supera el límite de carácteres, reenvia a este mismo servlet con un parametro error.
		if (email == null || email.equals("") || contrasenya == null || contrasenya.equals("") || email.length() > 64|| contrasenya.length() > 64) {

			response.sendRedirect("Login?error=true");

		} else {
			
			// Obtiene un usuario existente en relación a los credenciales proporcionados.
			Usuario usuario = usuarioEJB.getUsuarioLogin(email.toLowerCase(), contrasenya);

			// Si usuario no existe o el usuario no está verficado, reenvia a este mismo servlet con un parametro error.
			if (usuario == null || usuario.isEstado() == false) {

				response.sendRedirect("Login?error=true");

			} else {
				
				// Crea una sesión con el usuario.
				sessionEJB.usuarioLogin(session, usuario);

				// Redirige al servlet ListaPartidasCreadas.
				response.sendRedirect("Jugar");
			}
		}

	}

}
