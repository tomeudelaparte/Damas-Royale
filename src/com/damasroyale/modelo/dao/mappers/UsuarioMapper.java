package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Usuario;

/**
 * Clase Interfaz, Mapper para manejar los usuarios.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface UsuarioMapper {

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return ArrayList(Usuario)
	 */
	public ArrayList<Usuario> getAllUsuario();

	/**
	 * Obtiene un usuario por su identificador.
	 * 
	 * @param id Integer, identificado del usuario.
	 * @return Usuario
	 */
	public Usuario getUsuarioByID(@Param("id") Integer id);

	/**
	 * Obtiene un usuario por su correo y contraseña.
	 * 
	 * @param email       String
	 * @param contrasenya String
	 * @return Usuario
	 */
	public Usuario getUsuarioLogin(@Param("email") String email, @Param("contrasenya") String contrasenya);

	/**
	 * Obtiene el identificador de un usuario por su nombre o correo.
	 * 
	 * @param nombre String
	 * @param email  String
	 * @return String
	 */
	public String getUsuarioExistente(@Param("nombre") String nombre, @Param("email") String email);

	/**
	 * Obtiene el identificador de un usuario por su nombre.
	 * 
	 * @param nombre String
	 * @return String
	 */
	public String getNombreExistente(@Param("nombre") String nombre);

	/**
	 * Añade un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void addUsuario(Usuario usuario);

	/**
	 * Actualiza la información de un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void updateUsuario(Usuario usuario);

	/**
	 * Verifica la cuenta de un usuario.
	 * 
	 * @param usuario Usuario
	 */
	public void activateUsuario(Usuario usuario);

	/**
	 * Elimina un usuario por su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
	public void delUsuarioByID(@Param("id") Integer id);

}
