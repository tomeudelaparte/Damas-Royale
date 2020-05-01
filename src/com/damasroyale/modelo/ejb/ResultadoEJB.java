package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.ResultadoDAO;
import com.damasroyale.modelo.pojo.Resultado;

@Stateless
@LocalBean
public class ResultadoEJB {

	public Resultado getResultadoByPartidaID(Integer id) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		return resultadoDAO.getResultadoByPartidaID(id);
	}

	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		return resultadoDAO.getAllResultadoByIdUsuario(id);
	}

	public void addResultadoPartida(Resultado resultado) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		resultadoDAO.addResultadoPartida(resultado);
	}

}
