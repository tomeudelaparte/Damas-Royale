package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.dao.MensajeDAO;
import com.damasroyale.modelo.pojo.Mensaje;

@Stateless
@LocalBean
public class MensajeEJB {

	public void addMensaje(Mensaje mensaje) {

		MensajeDAO mensajeDAO = new MensajeDAO();

		mensajeDAO.addMensaje(mensaje);
	}

	public ArrayList<Mensaje> getMensajesByIdPartida(Integer idPartida) {
		MensajeDAO mensajeDAO = new MensajeDAO();

		return mensajeDAO.getMensajesByIdPartida(idPartida);
	}

}
