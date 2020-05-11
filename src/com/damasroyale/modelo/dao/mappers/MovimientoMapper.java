package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Movimiento;

public interface MovimientoMapper {

	public void addMovimiento(Movimiento movimiento);

	public ArrayList<Movimiento> getAllMovimientoByIdPartida(@Param("id") Integer id);
}
