package com.damasroyale.modelo.pojo;

public class Rank {

	private Integer id;
	private String nombre;
	private String imagen;
	private int puntuacion;

	public Rank() {

	}

	public Rank(Integer id, String nombre, String imagen, int puntuacion) {

		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.puntuacion = puntuacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
