package com.damasroyale.modelo.pojo;

import java.sql.Timestamp;

public class Usuario {

	private Integer idusuario;
	private String nombre;
	private String email;
	private String contrasenya;
	private String imagen;
	private int puntuacion;
	private Timestamp registro;
	private String estado;

	public Usuario() {

	}

	public Usuario(Integer idusuario, String nombre, String email, String contrasenya, String imagen, int puntuacion, Timestamp registro, String estado) {
		
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.email = email;
		this.contrasenya = contrasenya;
		this.imagen = imagen;
		this.puntuacion = puntuacion;
		this.registro = registro;
		this.estado = estado;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
