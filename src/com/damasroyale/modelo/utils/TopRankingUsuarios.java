package com.damasroyale.modelo.utils;

import java.util.Comparator;

import com.damasroyale.modelo.pojo.Rank;

/**
 * Clase Util para ordenar una lista de usuarios de mayor a menor puntuación.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class TopRankingUsuarios implements Comparator<Rank> {

	@Override
	public int compare(Rank r01, Rank r02) {
		return Integer.valueOf(r02.getPuntuacion()).compareTo(Integer.valueOf(r01.getPuntuacion()));
	}

}
