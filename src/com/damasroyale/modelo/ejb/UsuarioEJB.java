package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.UsuarioDAO;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Classe EJB para manejar los usuarios.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class UsuarioEJB {
	
	// EJB para manejar las activaciones.
	@EJB
	ActivacionEJB activacionEJB;

	// EJB para enviar un correo.
	@EJB
	JavaMailEJB javaMailEJB;

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return ArrayList(Usuario)
	 */
	public ArrayList<Usuario> getAllUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getAllUsuario();
	}

	/**
	 * Obtiene un usuario por su identificador.
	 * 
	 * @param id Integer, identificado del usuario.
	 * @return Usuario
	 */
	public Usuario getUsuarioByID(Integer id) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioByID(id);
	}

	/**
	 * Obtiene un usuario por su correo y contraseña.
	 * 
	 * @param email       String
	 * @param contrasenya String
	 * @return Usuario
	 */
	public Usuario getUsuarioLogin(String email, String contrasenya) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioLogin(email, contrasenya);
	}

	/**
	 * Obtiene el identificador de un usuario por su nombre o correo.
	 * 
	 * @param nombre String
	 * @param email  String
	 * @return String
	 */
	public String getUsuarioExistente(String nombre, String email) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getUsuarioExistente(nombre, email);
	}

	/**
	 * Obtiene el identificador de un usuario por su nombre.
	 * 
	 * @param nombre String
	 * @return String
	 */
	public String getNombreExistente(String nombre) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.getNombreExistente(nombre);
	}

	/**
	 * Añade un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void addUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.addUsuario(usuario);

		// Obtiene el usuario añadido.
		usuario = usuarioDAO.getUsuarioLogin(usuario.getEmail(), usuario.getContrasenya());

		// Obtiene el codigo de la activacion del usuario.
		String codigo = activacionEJB.addActivacion(usuario);

		// Envia el correo con el enlace de verificación.
		javaMailEJB.sendActivacion(usuario, codigo);
	}

	/**
	 * Actualiza la información de un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void updateUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.updateUsuario(usuario);
	}

	/**
	 * Verifica la cuenta de un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void activateUsuario(Usuario usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// Activa la cuenta del usuario.
		usuarioDAO.activateUsuario(usuario);

		// Elimina la activación
		activacionEJB.delActivacion(usuario);
	}

	/**
	 * Elimina un usuario por su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
	public void delUsuarioByID(Integer id) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.delUsuarioByID(id);
	}

}
