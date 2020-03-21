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

import com.damasroyale.modelo.ejb.ImagenEJB;
import com.damasroyale.modelo.ejb.SessionEJB;
import com.damasroyale.modelo.ejb.UsuarioEJB;
import com.damasroyale.modelo.pojo.Usuario;

@WebServlet("/Editar")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Editar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SessionEJB sessionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	ImagenEJB imagenEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Usuario usuario = sessionEJB.usuarioLogueado(session);

		if (usuario == null) {

			response.sendRedirect("Login");

		} else {

			RequestDispatcher rs = getServletContext().getRequestDispatcher("/Editar.jsp");

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
			String imagen = imagenEJB.uploadImage(request);

			if (nombre != null && !nombre.equals("")) {
				usuario.setNombre(nombre);
			}

			if (contrasenya != null && !contrasenya.equals("")) {
				usuario.setContrasenya(contrasenya);
			}

			if (imagen != null && !imagen.equals("")) {
				usuario.setImagen(imagen);
			}

			usuarioEJB.updateUsuario(usuario);

			response.sendRedirect("Jugador?id=" + usuario.getId());
		}

	}

}
