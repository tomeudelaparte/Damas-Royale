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

	public void addPartidaByIdUsuario(@Param("id") Integer id);

	public Partida getPartidaCreadaByIdUsuario(@Param("id") Integer id);

	public void updatePartida(Partida partida);

	public void addResultadoPartida(Resultado resultado);

	public void delPartidaByIdPartida(@Param("id") Integer id);

}
