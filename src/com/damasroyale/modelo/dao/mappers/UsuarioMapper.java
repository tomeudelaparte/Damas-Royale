package com.damasroyale.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Usuario;

public interface UsuarioMapper {

	public void addUsuario(Usuario usuario);

	public void updateUsuario(Usuario usuario);

	public void delUsuario(Usuario usuario);

	public Usuario getUsuario(@Param("email") String email, @Param("contrasenya") String contrasenya);

}
