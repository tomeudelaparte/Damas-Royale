package com.damasroyale.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Estadistica;

public interface EstadisticaMapper {

	public Estadistica getEstadisticaByUsuarioID(@Param("id") Integer id);

	public void addEstadistica(Estadistica estadistica);

	public void updateEstadistica(Estadistica estadistica);

}
