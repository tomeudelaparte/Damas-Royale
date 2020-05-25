package com.damasroyale.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Clase Interfaz, Mapper para manejar las verificaciones de las cuentas de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface ActivacionMapper {

	/**
	 * Añade una activación vinculada a un usuario.
	 * 
	 * @param activacion Activacion
	 */
	public void addActivacion(Activacion activacion);

	/**
	 * Obtiene un usuario a partir un código a modo de comprobación.
	 * 
	 * @param codigo String
	 * @return Usuario
	 */
	public Usuario checkActivacion(@Param("codigo") String codigo);

	/**
	 * Elimina la activación vinculada a un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void delActivacion(Usuario usuario);

}
