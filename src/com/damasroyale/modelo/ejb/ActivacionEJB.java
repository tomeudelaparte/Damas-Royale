package com.damasroyale.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.lang3.RandomStringUtils;

import com.damasroyale.modelo.dao.ActivacionDAO;
import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Classe EJB para manejar las verificaciones de las cuentas de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class ActivacionEJB {

	/**
	 * Añade una activación vinculada a un usuario.
	 * 
	 * @param usuario Usuario
	 * @return codigo String
	 */
	public String addActivacion(Usuario usuario) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		// Obtiene un string alfanumérico de 15 carácteres aleatorios.
		String codigo = RandomStringUtils.random(15, true, true);

		Activacion activacion = new Activacion(0, usuario.getId(), codigo);

		activacionDAO.addActivacion(activacion);

		return codigo;
	}

	/**
	 * Obtiene un usuario a partir un código a modo de comprobación.
	 * 
	 * @param codigo String
	 * @return Usuario
	 */
	public Usuario checkActivacion(String codigo) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		return activacionDAO.checkActivacion(codigo);
	}

	/**
	 * Elimina la activación vinculada a un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void delActivacion(Usuario usuario) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		activacionDAO.delActivacion(usuario);
	}

}
