package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Mensaje;

/**
 * Classe Interfaz, Mapper para manejar los mensajes de chat de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface MensajeMapper {

	/**
	 * Añade un mensaje a la partida.
	 * 
	 * @param mensaje Mensaje
	 */
	public void addMensaje(Mensaje mensaje);

	/**
	 * Obtiene todos los mensajes de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList<Mensaje>
	 */
	public ArrayList<Mensaje> getAllMensajeByIdPartida(@Param("id") Integer id);
}
