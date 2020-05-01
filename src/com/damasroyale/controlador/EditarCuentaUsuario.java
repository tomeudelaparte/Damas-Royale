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

import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.ejb.extras.GuardarImagenEJB;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Editar")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class EditarCuentaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	GuardarImagenEJB guardarImagenEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/EditarCuentaUsuario.jsp");

			request.setAttribute("usuario", usuario);

			rs.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Jugar");

		} else {

			String nombre = request.getParameter("nombre");
			String contrasenya = request.getParameter("contrasenya");
			String imagen = guardarImagenEJB.uploadImage(request);

			if (nombre != null && !nombre.equals("") && nombre.length() <= 32) {
				usuario.setNombre(nombre);
			}

			if (contrasenya != null && !contrasenya.equals("") && contrasenya.length() <= 64) {
				usuario.setContrasenya(contrasenya);
			}

			if (imagen != null && !imagen.equals("") && imagen.length() <= 64) {
				usuario.setImagen(imagen);
			}

			usuarioEJB.updateUsuario(usuario);

			response.sendRedirect("Ficha?id=" + usuario.getId());
		}

	}

}
