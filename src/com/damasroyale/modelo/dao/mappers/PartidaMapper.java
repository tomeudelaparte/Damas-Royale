package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Stat;

/**
 * Clase Interfaz, Mapper para manejar las partidas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface PartidaMapper {

	/**
	 * Obtiene una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaByID(@Param("id") Integer id);

	/**
	 * Obtiene una partida no finalizada a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByID(@Param("id") Integer id);

	/**
	 * Obtiene una partida no finalizada de un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByIdUsuario(@Param("id") Integer id);

	/**
	 * Añade una partida con un usuario anfitrión.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
	public void addPartidaByIdUsuario(@Param("id") Integer id);

	/**
	 * Actualiza la información de una partida.
	 * 
	 * @param partida Partida
	 */
	public void updatePartida(Partida partida);

	/**
	 * Elimina una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 */
	public void delPartidaByID(@Param("id") Integer id);

	/**
	 * Obtiene todas las partidas donde haya participado un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Partida>
	 */
	public ArrayList<Partida> getAllPartidaByIdUsuario(@Param("id") Integer id);

	/**
	 * Obtiene todas las partidas no finalizadas.
	 * 
	 * @return ArrayList<Partida>
	 */
	public ArrayList<Partida> getAllPartidaEnCurso();

	/**
	 * Obtiene las estadisticas de un usuario en base a sus partidas.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Stat>
	 */
	public ArrayList<Stat> getEstadisticaByIdUsuario(@Param("id") Integer id);

}
