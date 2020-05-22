package com.damasroyale.modelo.ejb;

import java.util.ArrayList; 

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.PartidaDAO;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.extras.Stat;

@Stateless
@LocalBean
public class PartidaEJB {

	public Partida getPartidaByID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaByID(id);
	}

	public Partida getPartidaNoFinalizadaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaNoFinalizadaByIdUsuario(id);
	}

	public void addPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.addPartidaByIdUsuario(id);
	}

	public void updatePartida(Partida partida) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.updatePartida(partida);
	}

	public void delPartidaByIdPartida(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.delPartidaByIdPartida(id);
	}

	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaByIdUsuario(id);
	}

	public ArrayList<Stat> getEstadisticaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getEstadisticaByIdUsuario(id);
	}

	public ArrayList<Partida> getAllPartidaEnCurso() {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaEnCurso();
	}

	public Partida getPartidaNoTerminadaByID(Integer id) {
		
		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaNoTerminadaByID(id);
	}

}
