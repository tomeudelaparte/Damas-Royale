package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Usuario;

public interface UsuarioMapper {

	public ArrayList<Usuario> getAllUsuario();

	public Usuario getUsuarioLogin(@Param("email") String email, @Param("contrasenya") String contrasenya);

	public Usuario getUsuarioByID(@Param("id") Integer id);

	public String getUsuarioExistente(@Param("nombre") String nombre, @Param("email") String email);

	public void addUsuario(Usuario usuario);

	public void updateUsuario(Usuario usuario);

	public void activateUsuario(Usuario usuario);

	public void delUsuarioByID(@Param("id") Integer id);

	public String getNombreExistente(@Param("nombre") String nombre);

}
