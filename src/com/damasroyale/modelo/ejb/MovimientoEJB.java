package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.MovimientoDAO;
import com.damasroyale.modelo.pojo.Movimiento;

/**
 * Classe EJB para manejar los movimientos de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class MovimientoEJB {

	/**
	 * Añade un movimiento a la partida.
	 * 
	 * @param movimiento Movimiento
	 */
	public void addMovimiento(Movimiento movimiento) {

		MovimientoDAO movimientoDAO = new MovimientoDAO();

		movimientoDAO.addMovimiento(movimiento);
	}

	/**
	 * Obtiene todos los movimientos de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList(Movimiento)
	 */
	public ArrayList<Movimiento> getAllMovimientoByIdPartida(Integer id) {

		MovimientoDAO movimientoDAO = new MovimientoDAO();

		return movimientoDAO.getAllMovimientoByIdPartida(id);
	}

}
