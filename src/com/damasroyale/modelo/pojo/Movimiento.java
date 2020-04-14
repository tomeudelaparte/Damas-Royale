package com.damasroyale.modelo.pojo;

public class Movimiento {

	private Integer id;
	private Integer idPartida;
	private Integer idUsuario;
	private int pos_inicial;
	private int pos_final;

	public Movimiento() {
	
	}

	public Movimiento(Integer id, Integer idPartida, Integer idUsuario, int pos_inicial, int pos_final) {
		
		this.id = id;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		this.pos_inicial = pos_inicial;
		this.pos_final = pos_final;
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

	public int getPos_inicial() {
		return pos_inicial;
	}

	public void setPos_inicial(int pos_inicial) {
		this.pos_inicial = pos_inicial;
	}

	public int getPos_final() {
		return pos_final;
	}

	public void setPos_final(int pos_final) {
		this.pos_final = pos_final;
	}

}
