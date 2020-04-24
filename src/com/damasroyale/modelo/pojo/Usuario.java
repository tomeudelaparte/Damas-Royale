package com.damasroyale.modelo.pojo;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {

	private Integer id;
	private String nombre;
	private String email;
	private String contrasenya;
	private String imagen;
	private Timestamp registro;
	private boolean estado;

	public Usuario() {

	}

	public Usuario(Integer id, String nombre, String email, String contrasenya, String imagen, Timestamp registro, boolean estado) {
		
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contrasenya = contrasenya;
		this.imagen = imagen;
		this.registro = registro;
		this.estado = estado;
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

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
