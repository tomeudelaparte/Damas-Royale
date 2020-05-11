package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.MovimientoMapper;
import com.damasroyale.modelo.pojo.Movimiento;

public class MovimientoDAO {

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
