package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.UsuarioDAO;
import com.damasroyale.modelo.pojo.Estadistica;
import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class UsuarioEJB {

	@EJB
	ActivacionEJB activacionEJB;

	@EJB
	EstadisticaEJB estadisticaEJB;

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

		if (usuario.getImagen().length() <= 0) {
			usuario.setImagen("Default.jpg");
		}

		usuarioDAO.addUsuario(usuario);

		usuario = usuarioDAO.getUsuarioLogin(usuario.getEmail(), usuario.getContrasenya());

		String codigo = activacionEJB.addActivacion(usuario);

		mailEJB.sendActivacion(usuario, codigo);
	}

	public void updateUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.updateUsuario(usuario);
	}

	public void activateUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		Estadistica estadistica = new Estadistica(0, usuario.getId(), 0, 0, 0, 0);

		usuarioDAO.activateUsuario(usuario);

		estadisticaEJB.addEstadistica(estadistica);

		activacionEJB.delActivacion(usuario);
	}

	public void delUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.delUsuario(usuario);
	}

}
