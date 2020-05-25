package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.ResultadoDAO;
import com.damasroyale.modelo.pojo.Resultado;

/**
 * Classe EJB para manejar los resultados de las partidas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class ResultadoEJB {

	/**
	 * Obtiene el resultado de una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Resultado
	 */
	public Resultado getResultadoByIdPartida(Integer id) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		return resultadoDAO.getResultadoByIdPartida(id);
	}

	/**
	 * Añade un resultado a una partida.
	 * 
	 * @param resultado Resultado
	 */
	public void addResultadoPartida(Resultado resultado) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		resultadoDAO.addResultadoPartida(resultado);
	}

	/**
	 * Obtiene todos los resultados de las partidas realizadas de un usuario a partir de su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Resultado>
	 */
	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {

		ResultadoDAO resultadoDAO = new ResultadoDAO();

		return resultadoDAO.getAllResultadoByIdUsuario(id);
	}

}
