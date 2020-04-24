package com.damasroyale.modelo.pojo;

import java.sql.Time;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensaje {

	private Integer id;
	private Integer idPartida;
	private Integer idUsuario;
	private Time hora;
	private String texto;

	public Mensaje() {
	
	}

	public Mensaje(Integer id, Integer idPartida, Integer idUsuario, Time hora, String texto) {
		
		this.id = id;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		this.hora = hora;
		this.texto = texto;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
