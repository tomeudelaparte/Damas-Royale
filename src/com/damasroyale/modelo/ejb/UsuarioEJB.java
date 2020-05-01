package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.UsuarioDAO;
import com.damasroyale.modelo.ejb.extras.EnviarMailEJB;
import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuarioEJB {

	@EJB
	ActivacionEJB activacionEJB;

	@EJB
	EnviarMailEJB enviarMailEJB;

	public ArrayList<Usuario> getAllUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getAllUsuario();
	}

	public Usuario getUsuarioByID(Integer id) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioByID(id);
	}

	public Usuario getUsuarioLogin(String email, String contrasenya) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioLogin(email, contrasenya);
	}

	public String getExistUsuario(String nombre, String email) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getExistUsuario(nombre, email);
	}

	public void addUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.addUsuario(usuario);

		usuario = usuarioDAO.getUsuarioLogin(usuario.getEmail(), usuario.getContrasenya());

		String codigo = activacionEJB.addActivacion(usuario);

		enviarMailEJB.sendActivacion(usuario, codigo);
	}

	public void updateUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.updateUsuario(usuario);
	}

	public void activateUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.activateUsuario(usuario);

		activacionEJB.delActivacion(usuario);
	}

	public void delUsuarioByID(Integer id) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.delUsuarioByID(id);
	}

}
