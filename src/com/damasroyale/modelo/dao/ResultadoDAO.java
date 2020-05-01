package com.damasroyale.modelo.dao;

import java.util.ArrayList; 

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.ResultadoMapper;
import com.damasroyale.modelo.pojo.Resultado;

public class ResultadoDAO {

	public Resultado getResultadoByPartidaID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ResultadoMapper ResultadoMapper = sqlSession.getMapper(ResultadoMapper.class);
			return ResultadoMapper.getResultadoByPartidaID(id);
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ResultadoMapper ResultadoMapper = sqlSession.getMapper(ResultadoMapper.class);
			return ResultadoMapper.getAllResultadoByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	public void addResultadoPartida(Resultado resultado) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ResultadoMapper ResultadoMapper = sqlSession.getMapper(ResultadoMapper.class);
			ResultadoMapper.addResultadoPartida(resultado);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}
