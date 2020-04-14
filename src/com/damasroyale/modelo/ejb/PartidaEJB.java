package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.PartidaDAO;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;

@Stateless
@LocalBean
public class PartidaEJB {

	public Partida getPartidaByID(Integer id) {

		PartidaDAO usuarioDAO = new PartidaDAO();

		return usuarioDAO.getPartidaByID(id);
	}

	public Resultado getResultadoByPartidaID(Integer id) {

		PartidaDAO usuarioDAO = new PartidaDAO();

		return usuarioDAO.getResultadoByPartidaID(id);
	}

	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		PartidaDAO usuarioDAO = new PartidaDAO();

		return usuarioDAO.getAllPartidaByIdUsuario(id);
	}

	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {
		
		PartidaDAO usuarioDAO = new PartidaDAO();

		return usuarioDAO. getAllResultadoByIdUsuario(id);
	}

}
