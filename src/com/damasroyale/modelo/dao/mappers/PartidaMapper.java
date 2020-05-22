package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList ;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.extras.Stat;

public interface PartidaMapper {

	public Partida getPartidaByID(@Param("id") Integer id);

	public Partida getPartidaNoFinalizadaByIdUsuario(@Param("id") Integer id);

	public void addPartidaByIdUsuario(@Param("id") Integer id);

	public void updatePartida(Partida partida);

	public void delPartidaByIdPartida(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaByIdUsuario(@Param("id") Integer id);

	public ArrayList<Stat> getEstadisticaByIdUsuario(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaEnCurso();

	public Partida getPartidaNoTerminadaByID(@Param("id") Integer id);

}
