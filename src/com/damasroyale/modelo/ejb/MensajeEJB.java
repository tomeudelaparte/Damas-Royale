package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.MensajeDAO;
import com.damasroyale.modelo.pojo.Mensaje;

/**
 * Classe EJB para manejar los mensajes de chat de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class MensajeEJB {

	/**
	 * Añade un mensaje a la partida.
	 * 
	 * @param mensaje Mensaje
	 */
	public void addMensaje(Mensaje mensaje) {

		MensajeDAO mensajeDAO = new MensajeDAO();

		mensajeDAO.addMensaje(mensaje);
	}

	/**
	 * Obtiene todos los mensajes de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList<Mensaje>
	 */
	public ArrayList<Mensaje> getAllMensajeByIdPartida(Integer id) {

		MensajeDAO mensajeDAO = new MensajeDAO();

		return mensajeDAO.getAllMensajeByIdPartida(id);
	}

}
