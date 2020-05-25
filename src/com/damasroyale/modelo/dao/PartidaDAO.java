package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.PartidaMapper;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Stat;

/**
 * Classe DAO para manejar los movimientos de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class PartidaDAO {

	/**
	 * Obtiene una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaByID(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene una partida no finalizada a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaNoFinalizadaByID(id);
		} finally {
			sqlSession.close();
		}

	}

	/**
	 * Obtiene una partida no finalizada de un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return Partida
	 */
	public Partida getPartidaNoFinalizadaByIdUsuario(Integer id) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getPartidaNoFinalizadaByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Añade una partida con un usuario anfitrión.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
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

	/**
	 * Actualiza la información de una partida.
	 * 
	 * @param partida Partida
	 */
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

	/**
	 * Elimina una partida a partir de su identificador.
	 * 
	 * @param id Integer, identificador de la partida.
	 */
	public void delPartidaByID(Integer id) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			partidaMapper.delPartidaByID(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene todas las partidas donde haya participado un usuario.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Partida>
	 */
	public ArrayList<Partida> getAllPartidaByIdUsuario(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllPartidaByIdUsuario(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene todas las partidas no finalizadas.
	 * 
	 * @return ArrayList<Partida>
	 */
	public ArrayList<Partida> getAllPartidaEnCurso() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			PartidaMapper partidaMapper = sqlSession.getMapper(PartidaMapper.class);
			return partidaMapper.getAllPartidaEnCurso();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene las estadisticas de un usuario en base a sus partidas.
	 * 
	 * @param id Integer, identificador del usuario.
	 * @return ArrayList<Stat>
	 */
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
