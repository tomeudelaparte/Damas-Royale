package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Estadistica;
import com.damasroyale.modelo.pojo.Rank;

public interface EstadisticaMapper {

	public ArrayList<Estadistica> getAllEstadistica();

	public Estadistica getEstadisticaByUsuarioID(@Param("id") Integer id);

	public void addEstadistica(Estadistica estadistica);

	public void updateEstadistica(Estadistica estadistica);

	public ArrayList<Rank> getRanking();

}
