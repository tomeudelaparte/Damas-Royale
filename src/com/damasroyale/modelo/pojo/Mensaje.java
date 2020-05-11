package com.damasroyale.modelo.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensaje {

	private Integer id;
	private Integer idPartida;
	private Integer idUsuario;
	private String texto;

	public Mensaje() {
	
	}

	public Mensaje(Integer id, Integer idPartida, Integer idUsuario, String texto) {
		
		this.id = id;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}



}
