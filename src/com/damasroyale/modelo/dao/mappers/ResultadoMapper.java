package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList; 

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Resultado;

public interface ResultadoMapper {

	public Resultado getResultadoByPartidaID(@Param("id") Integer id);

	public ArrayList<Resultado> getAllResultadoByIdUsuario(@Param("id") Integer id);

	public void addResultadoPartida(Resultado resultado);

}
