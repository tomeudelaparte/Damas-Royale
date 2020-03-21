package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.EstadisticaMapper;
import com.damasroyale.modelo.pojo.Estadistica;
import com.damasroyale.modelo.pojo.Rank;
import com.damasroyale.modelo.pojo.Usuario;

public class EstadisticaDAO {

	public ArrayList<Estadistica> getAllEstadistica() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			EstadisticaMapper estadisticaMapper = sqlSession.getMapper(EstadisticaMapper.class);
			return estadisticaMapper.getAllEstadistica();
		} finally {
			sqlSession.close();
		}
	}

	public Estadistica getEstadisticaByUsuarioID(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			EstadisticaMapper estadisticaMapper = sqlSession.getMapper(EstadisticaMapper.class);
			return estadisticaMapper.getEstadisticaByUsuarioID(usuario.getId());
		} finally {
			sqlSession.close();
		}
	}

	public void addEstadistica(Estadistica estadistica) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			EstadisticaMapper estadisticaMapper = sqlSession.getMapper(EstadisticaMapper.class);
			estadisticaMapper.addEstadistica(estadistica);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void updateEstadistica(Estadistica estadistica) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			EstadisticaMapper estadisticaMapper = sqlSession.getMapper(EstadisticaMapper.class);
			estadisticaMapper.updateEstadistica(estadistica);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Rank> getRanking() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			EstadisticaMapper estadisticaMapper = sqlSession.getMapper(EstadisticaMapper.class);
			return estadisticaMapper.getRanking();
		} finally {
			sqlSession.close();
		}
	}

}
