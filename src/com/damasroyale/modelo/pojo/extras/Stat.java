package com.damasroyale.modelo.pojo.extras;

import java.sql.Date;

public class Stat {

	private Date fecha;
	private int partidas_ganadas;
	private int partidas_perdidas;
	private int tablas;

	public Stat() {

	}

	public Stat(Date fecha, int partidas_ganadas, int partidas_perdidas, int tablas) {

		this.fecha = fecha;
		this.partidas_ganadas = partidas_ganadas;
		this.partidas_perdidas = partidas_perdidas;
		this.tablas = tablas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPartidas_ganadas() {
		return partidas_ganadas;
	}

	public void setPartidas_ganadas(int partidas_ganadas) {
		this.partidas_ganadas = partidas_ganadas;
	}

	public int getPartidas_perdidas() {
		return partidas_perdidas;
	}

	public void setPartidas_perdidas(int partidas_perdidas) {
		this.partidas_perdidas = partidas_perdidas;
	}

	public int getTablas() {
		return tablas;
	}

	public void setTablas(int tablas) {
		this.tablas = tablas;
	}

	@Override
	public String toString() {
		return "{fecha:'" + fecha + "', partidas_ganadas:'" + partidas_ganadas + "', partidas_perdidas:'"
				+ partidas_perdidas + "', tablas:'" + tablas + "'}";
	}
	
	
}
