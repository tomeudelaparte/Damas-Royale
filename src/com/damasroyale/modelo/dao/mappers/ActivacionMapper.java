package com.damasroyale.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

public interface ActivacionMapper {

	public void addActivacion(Activacion activacion);

	public void delActivacion(Usuario usuario);

	public Usuario checkActivacion(@Param("codigo") String codigo);

}
