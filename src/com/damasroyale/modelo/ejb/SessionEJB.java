package com.damasroyale.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.pojo.Usuario;

/**
 * Clase EJB para manejar las sesiones de usuario.
 * 
 * @author Tomeu
 *
 */
@Stateless
@LocalBean
public class SessionEJB {

	/**
	 * Obtiene un usuario de la sesión.
	 * 
	 * @param session HttpSession
	 * @return Usuario
	 */
	public Usuario usuarioLogueado(HttpSession session) {

		Usuario usuario = null;

		if (session != null) {
			usuario = (Usuario) session.getAttribute("usuario");
		}

		return usuario;
	}

	/**
	 * Añade un usuario a la sesión.
	 * 
	 * @param session HttpSession
	 * @param usuario Usuario
	 */
	public void usuarioLogin(HttpSession session, Usuario usuario) {

		if (session != null) {
			session.setAttribute("usuario", usuario);
		}
	}

	/**
	 * Cierra la sesión por completo.
	 * 
	 * @param session HttpSession
	 */
	public void usuarioLogout(HttpSession session) {

		if (session != null) {
			session.invalidate();
		}
	}
}
