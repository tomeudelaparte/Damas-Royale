package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.ResultadoMapper;
import com.damasroyale.modelo.pojo.Resultado;

/**
 * Classe DAO para manejar las verificaciones de las cuentas de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class ResultadoDAO {

	/**
	 * Obtiene el resultado de una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Resultado
	 */
	public Resultado getResultadoByIdPartida(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ResultadoMapper ResultadoMapper = sqlSession.getMapper(ResultadoMapper.class);
			return ResultadoMapper.getResultadoByIdPartida(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Añade un resultado a una partida.
	 * 
	 * @param resultado Resultado
	 */
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

	/**
	 * Obtiene todos los resultados de las partidas realizadas de un usuario a partir de su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Resultado>
	 */
	public ArrayList<Resultado> getAllResultadoByIdUsuario(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ResultadoMapper ResultadoMapper = sqlSession.getMapper(ResultadoMapper.class);
			return ResultadoMapper.getAllResultadoByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

}
