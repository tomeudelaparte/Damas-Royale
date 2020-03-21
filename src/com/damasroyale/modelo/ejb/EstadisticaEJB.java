package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.EstadisticaDAO;
import com.damasroyale.modelo.pojo.Estadistica;
import com.damasroyale.modelo.pojo.Rank;
import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class EstadisticaEJB {

	public ArrayList<Estadistica> getAllEstadistica() {

		EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

		return estadisticaDAO.getAllEstadistica();
	}

	public Estadistica getEstadisticaByUsuarioID(Usuario usuario) {

		EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

		return estadisticaDAO.getEstadisticaByUsuarioID(usuario);
	}

	public void addEstadistica(Estadistica estadistica) {

		EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

		estadisticaDAO.addEstadistica(estadistica);
	}

	public void updateEstadistica(Estadistica estadistica) {

		EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

		estadisticaDAO.updateEstadistica(estadistica);
	}

	public ArrayList<Rank> getRanking() {

		EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

		return estadisticaDAO.getRanking();
	}

}
