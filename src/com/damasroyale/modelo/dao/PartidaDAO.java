package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.PartidaMapper;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.extras.Stat;

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

	public ArrayList<Partida> getAllPartidaEnCurso() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllPartidaEnCurso();
		} finally {
			sqlSession.close();
		}
	}

	public void addPartidaByIdUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.addPartidaByIdUsuario(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public Partida getPartidaCreadaByIdUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaCreadaByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	public void updatePartida(Partida partida) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.updatePartida(partida);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public void addResultadoPartida(Resultado resultado) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.addResultadoPartida(resultado);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void delPartidaByIdPartida(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.delPartidaByIdPartida(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}		
	}

}
