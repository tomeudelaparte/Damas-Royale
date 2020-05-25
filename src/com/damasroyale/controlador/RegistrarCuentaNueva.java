package com.damasroyale.controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
 * Servlet para crear una cuenta nueva de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Register")
public class RegistrarCuentaNueva extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Obtiene el parametro error para mostrar un mensaje.
		String error = request.getParameter("error");

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario != null) {

			response.sendRedirect("Jugar");

		} else {

			// Prepara una solicitud para mostrar un jsp.
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/RegistrarCuentaNueva.jsp");

			// Setea el error para mostrar un mensaje.
			request.setAttribute("error", error);

			// Reenvia al jsp.
			rs.forward(request, response);
		}

	}

	/**
	 * Método que recibe las peticiones POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Si no hay ningún usuario existente, reenvia al servlet LogInOutUsuario.
		if (usuario != null) {

			response.sendRedirect("Jugar");

		} else {

			// Obtiene los parametros introducidos por el usuario.
			String nombre = request.getParameter("nombre");
			String contrasenya = request.getParameter("contrasenya");
			String email = request.getParameter("email");

			// Si el nombre o el email o la contraseña es null o excede el máximo de carácteres, reenvia a este mismo servlet con un parametro error.
			if (nombre == null || contrasenya == null || email == null || nombre.length() > 32 || contrasenya.length() > 64 || email.length() > 64) {

				response.sendRedirect("Register?error=true");

			} else {

				// Obtiene un usuario con esos credenciales para comprobar que ya exista.
				String idUsuario = usuarioEJB.getUsuarioExistente(nombre, email);

				// Si idUsuario es null, reenvia a este mismo servlet con un parametro error. 
				if (idUsuario != null) {

					response.sendRedirect("Register?error=true");

				} else {
					
					// Obtiene la fecha actual.
					Date date = new Date();

					// Obtiene un timestamp a partir de la fecha.
					Timestamp registro = new Timestamp(date.getTime());

					// Crea y añade el usuario.
					Usuario newUsuario = new Usuario(0, nombre, email, contrasenya, "usuario.jpg", registro, false);

					usuarioEJB.addUsuario(newUsuario);

					// Reenvia a este mismo servlet con un parametro register.
					response.sendRedirect("Login?register=true");
				}

			}

		}

	}

}
