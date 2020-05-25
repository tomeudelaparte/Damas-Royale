package com.damasroyale.modelo.ejb;

import java.util.Hashtable;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import com.damasroyale.modelo.juego.DamasOnline;

/**
 * 
 * Clase EJB que almacena las partidas en curso.
 * 
 * @author Tomeu de la Parte Mulet
 *
 * @param <P> DamasOnline
 */
@Stateless
@Startup
@LocalBean
public class ListaPartidasEJB<P extends DamasOnline> {

	private Hashtable<Integer, DamasOnline> listaPartidas = new Hashtable<Integer, DamasOnline>();

	/**
	 * Añade la partida a la lista.
	 * 
	 * @param partida Partida
	 */
	public void add(P partida) {

		if (contains(partida) == false) {

			listaPartidas.put(partida.getId(), partida);
		}
	}

	/**
	 * Comprueba que la lista contenga la partida.
	 * 
	 * @param partida Partida
	 * @return boolean
	 */
	public boolean contains(P partida) {

		return listaPartidas.containsValue(partida);
	}

	/**
	 * Obtiene la partida por el identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return DamasOnline
	 */
	public DamasOnline getPartida(Integer id) {

		return listaPartidas.get(id);
	}

}
