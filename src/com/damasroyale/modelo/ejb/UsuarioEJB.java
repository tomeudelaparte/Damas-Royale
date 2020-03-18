package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.UsuarioDAO;
import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuarioEJB {

	@EJB
	ActivacionEJB activacionEJB;

	@EJB
	MailEJB mailEJB;

	public ArrayList<Usuario> getAllUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getAllUsuario();
	}

	public Usuario getUsuarioByID(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioByID(usuario);
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

		mailEJB.sendVerificacion(usuario, codigo);
	}

	public void updateUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.updateUsuario(usuario);
	}

	public void activateUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.activateUsuario(usuario);

		activacionEJB.delActivacion(usuario);

		usuario.setEstado("S");
	}

	public void delUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.delUsuario(usuario);
	}

}
