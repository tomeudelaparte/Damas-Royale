package com.damasroyale.modelo.pojo;

import java.sql.Date;

/**
 * Clase POJO de Stat
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class Stat {

	private Date fecha;
	private int partidas_ganadas;
	private int partidas_perdidas;
	private int partidas_tablas;

	public Stat() {

	}

	public Stat(Date fecha, int partidas_ganadas, int partidas_perdidas, int partidas_tablas) {
		this.fecha = fecha;
		this.partidas_ganadas = partidas_ganadas;
		this.partidas_perdidas = partidas_perdidas;
		this.partidas_tablas = partidas_tablas;
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

	public int getPartidas_tablas() {
		return partidas_tablas;
	}

	public void setPartidas_tablas(int partidas_tablas) {
		this.partidas_tablas = partidas_tablas;
	}

	// toString() para poder manejar la información en JavaScript
	@Override
	public String toString() {
		return "{fecha:'" + fecha + "', partidas_ganadas:'" + partidas_ganadas + "', partidas_perdidas:'"
				+ partidas_perdidas + "', tablas:'" + partidas_tablas + "'}";
	}
	
	
}
