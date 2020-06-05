package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.PartidaDAO;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Stat;

/**
 * Classe EJB para manejar las partidas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class PartidaEJB {

	/**
	 * Obtiene una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaByID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaByID(id);
	}

	/**
	 * Obtiene una partida no finalizada a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaNoFinalizadaByID(id);
	}

	/**
	 * Obtiene una partida no finalizada de un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaNoFinalizadaByIdUsuario(id);
	}

	/**
	 * Añade una partida con un usuario anfitrión.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
	public void addPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.addPartidaByIdUsuario(id);
	}

	/**
	 * Actualiza la información de una partida.
	 * 
	 * @param partida Partida
	 */
	public void updatePartida(Partida partida) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.updatePartida(partida);
	}

	/**
	 * Elimina una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 */
	public void delPartidaByID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.delPartidaByID(id);
	}

	/**
	 * Obtiene todas las partidas donde haya participado un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList(Partida)
	 */
	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaByIdUsuario(id);
	}

	/**
	 * Obtiene todas las partidas no finalizadas.
	 * 
	 * @return ArrayList(Partida)
	 */
	public ArrayList<Partida> getAllPartidaEnCurso() {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaEnCurso();
	}

	/**
	 * Obtiene las estadisticas de un usuario en base a sus partidas.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList(Stat)
	 */
	public ArrayList<Stat> getEstadisticaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getEstadisticaByIdUsuario(id);
	}

}
