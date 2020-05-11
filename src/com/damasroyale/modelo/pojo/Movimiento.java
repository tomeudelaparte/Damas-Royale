package com.damasroyale.modelo.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movimiento {

	private Integer id;
	private Integer idPartida;
	private Integer idUsuario;
	private int fila_origen;
	private int fila_destino;
	private int columna_origen;
	private int columna_destino;

	public Movimiento() {

	}

	public Movimiento(Integer id, Integer idPartida, Integer idUsuario, int fila_origen, int fila_destino, int columna_origen, int columna_destino) {
		
		this.id = id;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		this.fila_origen = fila_origen;
		this.fila_destino = fila_destino;
		this.columna_origen = columna_origen;
		this.columna_destino = columna_destino;
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

	public int getFila_origen() {
		return fila_origen;
	}

	public void setFila_origen(int fila_origen) {
		this.fila_origen = fila_origen;
	}

	public int getFila_destino() {
		return fila_destino;
	}

	public void setFila_destino(int fila_destino) {
		this.fila_destino = fila_destino;
	}

	public int getColumna_origen() {
		return columna_origen;
	}

	public void setColumna_origen(int columna_origen) {
		this.columna_origen = columna_origen;
	}

	public int getColumna_destino() {
		return columna_destino;
	}

	public void setColumna_destino(int columna_destino) {
		this.columna_destino = columna_destino;
	}

	@Override
	public String toString() {
		return "{id:'" + id + "', idPartida:'" + idPartida + "', idUsuario:'" + idUsuario + "', fila_origen:'"
				+ fila_origen + "', fila_destino:'" + fila_destino + "', columna_origen:'" + columna_origen
				+ "', columna_destino:'" + columna_destino + "'}";
	}


	
	

}
