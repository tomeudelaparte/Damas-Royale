package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.MovimientoMapper;
import com.damasroyale.modelo.pojo.Movimiento;

/**
 * Clase DAO para realizar conexiones SQL con los movimientos de las partidas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class MovimientoDAO {

	/**
	 * Añade un movimiento a la partida.
	 * 
	 * @param movimiento Movimiento
	 */
	public void addMovimiento(Movimiento movimiento) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MovimientoMapper partidaMapper = sqlSession.getMapper(MovimientoMapper.class);
			partidaMapper.addMovimiento(movimiento);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene todos los movimientos de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList<Movimiento>
	 */
	public ArrayList<Movimiento> getAllMovimientoByIdPartida(Integer id) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MovimientoMapper movimientoMapper = sqlSession.getMapper(MovimientoMapper.class);
			return movimientoMapper.getAllMovimientoByIdPartida(id);
		} finally {
			sqlSession.close();
		}
	}

}
