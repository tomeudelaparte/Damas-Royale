package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.PartidaMapper;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;
import com.damasroyale.modelo.pojo.Stat;
import com.damasroyale.modelo.pojo.Usuario;

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

	public ArrayList<Partida> getAllPartidaByUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllPartidaByUsuario(usuario);
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

	public ArrayList<Resultado> getAllResultadoByUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllResultadoByUsuario(usuario);
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

	public void addPartida(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.addPartida(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public Partida getPartidaCreadaByUsuario(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaCreadaByUsuario(usuario);
		} finally {
			sqlSession.close();
		}
	}

}
