package com.damasroyale.modelo.pojo;

public class Movimiento {

	private Integer id;
	private Integer idPartida;
	private Integer idUsuario;
	private int filaInicial;
	private int filaFinal;
	private int columnaInicial;
	private int columnaFinal;

	public Movimiento() {

	}

	public Movimiento(Integer id, Integer idPartida, Integer idUsuario, int filaInicial, int filaFinal,
			int columnaInicial, int columnaFinal) {

		this.id = id;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		this.filaInicial = filaInicial;
		this.filaFinal = filaFinal;
		this.columnaInicial = columnaInicial;
		this.columnaFinal = columnaFinal;
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

	public int getFilaInicial() {
		return filaInicial;
	}

	public void setFilaInicial(int filaInicial) {
		this.filaInicial = filaInicial;
	}

	public int getFilaFinal() {
		return filaFinal;
	}

	public void setFilaFinal(int filaFinal) {
		this.filaFinal = filaFinal;
	}

	public int getColumnaInicial() {
		return columnaInicial;
	}

	public void setColumnaInicial(int columnaInicial) {
		this.columnaInicial = columnaInicial;
	}

	public int getColumnaFinal() {
		return columnaFinal;
	}

	public void setColumnaFinal(int columnaFinal) {
		this.columnaFinal = columnaFinal;
	}

}
