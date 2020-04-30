package com.damasroyale.modelo.pojo;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {

	private Integer id;
	private Integer idPartida;
	private Timestamp fecha_hora;
	private boolean tablas;
	private Integer ganador;
	private Integer perdedor;

	public Resultado() {

	}

	public Resultado(Integer id, Integer idPartida, Timestamp fecha_hora, boolean tablas, Integer ganador, Integer perdedor) {
		
		this.id = id;
		this.idPartida = idPartida;
		this.fecha_hora = fecha_hora;
		this.tablas = tablas;
		this.ganador = ganador;
		this.perdedor = perdedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Integer idPartida) {
		this.idPartida = idPartida;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public boolean isTablas() {
		return tablas;
	}

	public void setTablas(boolean tablas) {
		this.tablas = tablas;
	}

	public Integer getGanador() {
		return ganador;
	}

	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}

	public Integer getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(Integer perdedor) {
		this.perdedor = perdedor;
	}

}
