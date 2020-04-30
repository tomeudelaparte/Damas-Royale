package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.PartidaDAO;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;

@Stateless
@LocalBean
public class PartidaEJB {

	public Partida getPartidaByID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaByID(id);
	}

	public Resultado getResultadoByPartidaID(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getResultadoByPartidaID(id);
	}

	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaByIdUsuario(id);
	}

	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllResultadoByIdUsuario(id);
	}

	public ArrayList<Stat> getEstadisticaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getEstadisticaByIdUsuario(id);
	}

	public ArrayList<Partida> getAllPartidaEnCurso() {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaEnCurso();
	}

	public Partida getPartidaCreadaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getPartidaCreadaByIdUsuario(id);
	}

	public void addPartidaByIdUsuario(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.addPartidaByIdUsuario(id);
	}

	public void updatePartida(Partida partida) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.updatePartida(partida);

	}

	public void addResultadoPartida(Resultado resultado) {
		PartidaDAO partidaDAO = new PartidaDAO();
		partidaDAO.addResultadoPartida(resultado);
	}

	public void delPartidaByIdPartida(Integer id) {

		PartidaDAO partidaDAO = new PartidaDAO();
		partidaDAO.delPartidaByIdPartida(id);		
	}

}
