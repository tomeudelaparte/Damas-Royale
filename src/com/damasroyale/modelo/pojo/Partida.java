package com.damasroyale.modelo.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase POJO de Partida
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@XmlRootElement
public class Partida {

	private Integer id;
	private Integer idUsuario_A;
	private Integer idUsuario_B;
	private boolean finalizada;

	public Partida() {

	}

	public Partida(Integer id, Integer idUsuario_A, Integer idUsuario_B, boolean finalizada) {
		
		this.id = id;
		this.idUsuario_A = idUsuario_A;
		this.idUsuario_B = idUsuario_B;
		this.finalizada = finalizada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario_A() {
		return idUsuario_A;
	}

	public void setIdUsuario_A(Integer idUsuario_A) {
		this.idUsuario_A = idUsuario_A;
	}

	public Integer getIdUsuario_B() {
		return idUsuario_B;
	}

	public void setIdUsuario_B(Integer idUsuario_B) {
		this.idUsuario_B = idUsuario_B;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

}
