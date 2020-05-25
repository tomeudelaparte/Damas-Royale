package com.damasroyale.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.damasroyale.modelo.pojo.Movimiento;

/**
 * Clase Interfaz, Mapper para manejar los movimientos de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public interface MovimientoMapper {

	/**
	 * Añade un movimiento a la partida.
	 * 
	 * @param movimiento Movimiento
	 */
	public void addMovimiento(Movimiento movimiento);

	/**
	 * Obtiene todos los movimientos de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList<Movimiento>
	 */
	public ArrayList<Movimiento> getAllMovimientoByIdPartida(@Param("id") Integer id);
}
