package com.damasroyale.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.lang3.RandomStringUtils;

import com.damasroyale.modelo.dao.ActivacionDAO;
import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class ActivacionEJB {

	public String addActivacion(Usuario usuario) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		String codigo = RandomStringUtils.random(15, true, true);

		Activacion activacion = new Activacion(0, usuario.getId(), codigo);

		activacionDAO.addActivacion(activacion);

		return codigo;
	}

	public void delActivacion(Usuario usuario) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		activacionDAO.delActivacion(usuario);
	}

	public Usuario checkActivacion(String codigo) {

		ActivacionDAO activacionDAO = new ActivacionDAO();

		return activacionDAO.checkActivacion(codigo);
	}

}
