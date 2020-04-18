package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.PartidaMapper;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;

public class PartidaDAO {

	public Partida getPartidaByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaByID(id);
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllPartidaByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	public Resultado getResultadoByPartidaID(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getResultadoByPartidaID(id);
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllResultadoByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Stat> getEstadisticaByIdUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getEstadisticaByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

}
