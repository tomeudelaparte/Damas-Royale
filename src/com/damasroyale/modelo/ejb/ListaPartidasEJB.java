package com.damasroyale.modelo.ejb;

import java.util.Hashtable;  

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import com.damasroyale.modelo.juego.DamasOnline;

@Stateless
@Startup
@LocalBean
public class ListaPartidasEJB<P extends DamasOnline> {

	private Hashtable<Integer, DamasOnline> listaPartidas = new Hashtable<Integer, DamasOnline>();

	public void add(P partida) {

		if (contains(partida) == false) {

			listaPartidas.put(partida.getId(), partida);
		}
	}

	public void remove(P partida) {

		if (contains(partida) == true) {

			listaPartidas.remove(partida.getId(), partida);
		}
	}

	public boolean contains(P partida) {

		return listaPartidas.containsValue(partida);
	}

	public DamasOnline getPartida(Integer id) {

		return listaPartidas.get(id);
	}

}
