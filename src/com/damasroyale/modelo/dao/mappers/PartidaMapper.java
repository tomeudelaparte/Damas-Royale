package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;

public interface PartidaMapper {

	public Partida getPartidaByID(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaByIdUsuario(@Param("id") Integer id);

	public Resultado getResultadoByPartidaID(@Param("id") Integer id);

	public ArrayList<Resultado> getAllResultadoByIdUsuario(@Param("id") Integer id);

	public ArrayList<Stat> getEstadisticaByIdUsuario(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaEnCurso();

}
