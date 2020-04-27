package com.damasroyale.modelo.pojo;

import java.util.ArrayList;
import java.util.List;

public class Damas {

	private Integer id;

	private int turno = 1;

	private int fichas01 = 12;
	private int fichas02 = 12;

	public int tablero[][] = { 
			{ 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, 
			{ 1, 0, 1, 0, 1, 0, 1, 0 } };

	private List<Integer> fichasEliminadas = new ArrayList<Integer>();

	public Damas(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getFichas01() {
		return fichas01;
	}

	public void setFichas01(int fichas01) {
		this.fichas01 = fichas01;
	}

	public int getFichas02() {
		return fichas02;
	}

	public void setFichas02(int fichas02) {
		this.fichas02 = fichas02;
	}

	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public List<Integer> getFichasEliminadas() {
		return fichasEliminadas;
	}

	public void setFichasEliminadas(List<Integer> fichasEliminadas) {
		this.fichasEliminadas = fichasEliminadas;
	}

	

}
