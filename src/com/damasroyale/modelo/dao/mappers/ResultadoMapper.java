package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Resultado;

/**
 * Clase Interfaz, Mapper para manejar los resultados de las partidas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface ResultadoMapper {

	/**
	 * Obtiene el resultado de una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Resultado
	 */
	public Resultado getResultadoByIdPartida(@Param("id") Integer id);

	/**
	 * Añade un resultado a una partida.
	 * 
	 * @param resultado Resultado
	 */
	public void addResultadoPartida(Resultado resultado);

	/**
	 * Obtiene todos los resultados de las partidas realizadas de un usuario a partir de su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Resultado>
	 */
	public ArrayList<Resultado> getAllResultadoByIdUsuario(@Param("id") Integer id);

}
