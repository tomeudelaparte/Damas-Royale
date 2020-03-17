package com.damasroyale.modelo.pojo;

public class Activacion {

	private Integer id;
	private Integer idUsuario;
	private String codigo;

	public Activacion() {

	}

	public Activacion(Integer id, Integer idUsuario, String codigo) {

		this.id = id;
		this.idUsuario = idUsuario;
		this.codigo = codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
