package com.damasroyale.modelo.ejb;

import java.util.Hashtable;  

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import com.damasroyale.modelo.pojo.Damas;

@Stateless
@Startup
@LocalBean
public class ListaPartidasEJB<P extends Damas> {

	private Hashtable<Integer, Damas> listaPartidas = new Hashtable<Integer, Damas>();

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

	public Damas getPartida(Integer id) {

		return listaPartidas.get(id);
	}

}
