package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.MovimientoDAO;
import com.damasroyale.modelo.pojo.Movimiento;

@Stateless
@LocalBean
public class MovimientoEJB {

	public void addMovimiento(Movimiento movimiento) {

		MovimientoDAO movimientoDAO = new MovimientoDAO();

		movimientoDAO.addMovimiento(movimiento);
	}

	public ArrayList<Movimiento> getAllMovimientoByIdPartida(Integer idPartida) {

		MovimientoDAO movimientoDAO = new MovimientoDAO();

		return movimientoDAO.getAllMovimientoByIdPartida(idPartida);
	}

}
