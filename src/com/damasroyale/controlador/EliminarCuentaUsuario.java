package com.damasroyale.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet que permite a un usuario eliminar su cuenta y todos los datos que lo relacionan.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Eliminar")
public class EliminarCuentaUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;

	// EJB para utilizar las funciones de usuario.
	@EJB
	UsuarioEJB usuarioEJB;

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

			// Cierra la sesión del usuario.
			sessionEJB.usuarioLogout(session);

			// Elimina la cuenta del usuario.
			usuarioEJB.delUsuarioByID(usuario.getId());

			// Reenvia al servlet LogInOutUsuario.
			response.sendRedirect("Login?delaccount=true");
		}
	}
}
