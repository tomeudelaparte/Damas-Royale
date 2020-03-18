package com.damasroyale.modelo.pojo;

public class Estadistica {

	private Integer id;
	private Integer idUsuario;
	private int totales;
	private int ganadas;
	private int perdidas;
	private int puntuacion;

	public Estadistica() {

	}

	public Estadistica(Integer id, Integer idUsuario, int totales, int ganadas, int perdidas, int puntuacion) {
		
		this.id = id;
		this.idUsuario = idUsuario;
		this.totales = totales;
		this.ganadas = ganadas;
		this.perdidas = perdidas;
		this.puntuacion = puntuacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getTotales() {
		return totales;
	}

	public void setTotales(int totales) {
		this.totales = totales;
	}

	public int getGanadas() {
		return ganadas;
	}

	public void setGanadas(int ganadas) {
		this.ganadas = ganadas;
	}

	public int getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(int perdidas) {
		this.perdidas = perdidas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
