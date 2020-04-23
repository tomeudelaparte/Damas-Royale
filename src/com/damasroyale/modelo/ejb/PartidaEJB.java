package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.PartidaDAO;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;
import com.damasroyale.modelo.pojo.Usuario;

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

	public ArrayList<Partida> getAllPartidaByUsuario(Usuario usuario) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaByUsuario(usuario);
	}

	public ArrayList<Resultado> getAllResultadoByUsuario(Usuario usuario) {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllResultadoByUsuario(usuario);
	}

	public ArrayList<Stat> getEstadisticaByIdUsuario(Integer id) {
		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getEstadisticaByIdUsuario(id);
	}

	public ArrayList<Partida> getAllPartidaEnCurso() {

		PartidaDAO partidaDAO = new PartidaDAO();

		return partidaDAO.getAllPartidaEnCurso();
	}

	public Partida createPartida(Usuario usuario) {

		PartidaDAO partidaDAO = new PartidaDAO();

		partidaDAO.addPartida(usuario);

		return partidaDAO.getPartidaCreadaByUsuario(usuario);

	}

}
