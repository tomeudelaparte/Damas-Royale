package com.damasroyale.controlador;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.ejb.FileUploaderEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Servlet que permite a un usuario editar su cuenta de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@WebServlet("/Editar")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class EditarCuentaUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// EJB para utilizar las funciones de sesión.
	@EJB
	SessionEJB sessionEJB;

	// EJB para utilizar las funciones de usuario. 
	@EJB
	UsuarioEJB usuarioEJB;

	//EJB para guardar la imagen del usuario.
	@EJB
	FileUploaderEJB fileUploaderEJB;

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

			// Prepara una solicitud para mostrar un jsp.
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/EditarCuentaUsuario.jsp");

			// Setea el atributo usuario con un objeto usuario.
			request.setAttribute("usuario", usuario);

			// Reenvia al jsp.
			rs.forward(request, response);
		}

	}

	/**
	 * Método que recibe las peticiones POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene una sesion existente sin crear una nueva.
		HttpSession session = request.getSession(false);

		// Obtiene un usuario si hay un usuario existente en la sesión.
		Usuario usuario = sessionEJB.usuarioLogueado(session);

		// Si no hay ningún usuario existente, reenvia al servlet ListaPartidasCreadas.
		if (usuario == null) {

			response.sendRedirect("Jugar");

		} else {

			// Obtiene los parámetros, datos introducidos por el usuario.
			String nombre = request.getParameter("nombre");
			String contrasenya = request.getParameter("contrasenya");
			String imagen = fileUploaderEJB.uploadFile(request);

			// Si el nombre no es null y es diferente a un string vacío y es menor igual a 32 carácteres y el nombre no es igual al actual.
			if (nombre != null && !nombre.equals("") && nombre.length() <= 32 && !usuario.getNombre().equals(nombre)) {

				// Obtiene el identificador de un usuario con el mismo nombre igual al nombre que ha introducido el usuario.
				String idUsuario = usuarioEJB.getNombreExistente(nombre);

				// Si el identificador es null.
				if (idUsuario == null) {

					// Setea el nombre al usuario.
					usuario.setNombre(nombre);
				}
			}

			// Si el nombre no es null y es diferente a un string vacío y es menor igual a 64 carácteres y la contraseña no es igual a la actual.
			if (contrasenya != null && !contrasenya.equals("") && contrasenya.length() <= 64 && !usuario.getContrasenya().equals(contrasenya)) {

				// Setea la contrasenya al usuario.
				usuario.setContrasenya(contrasenya);
			}

			// Si la imagen no es null.
			if (imagen != null) {

				// Setea la imagen al usuario.
				usuario.setImagen(imagen);
			}

			// Actualiza la información del usuario.
			usuarioEJB.updateUsuario(usuario);

			// Redirige al servlet FichaUsuario con el usuario correspondiente.
			response.sendRedirect("Ficha?id=" + usuario.getId());
		}

	}

}
