package com.damasroyale.modelo.ejb;

import javax.ejb.LocalBean; 
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class SessionEJB {

	public Usuario usuarioLogueado(HttpSession session) {

		Usuario usuario = null;

		if (session != null) {
			usuario = (Usuario) session.getAttribute("usuario");
		}

		return usuario;
	}

	public void usuarioLogin(HttpSession session, Usuario usuario) {

		if (session != null) {
			session.setAttribute("usuario", usuario);
		}
	}

	public void usuarioLogout(HttpSession session) {

		if (session != null) {
			session.invalidate();
		}
	}
}
