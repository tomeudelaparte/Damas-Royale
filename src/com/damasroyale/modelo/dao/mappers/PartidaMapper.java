package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;
import com.damasroyale.modelo.pojo.Usuario;

public interface PartidaMapper {

	public Partida getPartidaByID(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaByUsuario(Usuario usuario);

	public Resultado getResultadoByPartidaID(@Param("id") Integer id);

	public ArrayList<Resultado> getAllResultadoByUsuario(Usuario usuario);

	public ArrayList<Stat> getEstadisticaByIdUsuario(@Param("id") Integer id);

	public ArrayList<Partida> getAllPartidaEnCurso();

	public void addPartida(Usuario usuario);

	public Partida getPartidaCreadaByUsuario(Usuario usuario);

}
