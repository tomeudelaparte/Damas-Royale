package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.MensajeMapper;
import com.damasroyale.modelo.pojo.Mensaje;

/**
 * Classe DAO para manejar los mensajes de chat de una partida.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class MensajeDAO {

	/**
	 * Añade un mensaje a la partida.
	 * 
	 * @param mensaje Mensaje
	 */
	public void addMensaje(Mensaje mensaje) {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MensajeMapper mensajeMapper = sqlSession.getMapper(MensajeMapper.class);
			mensajeMapper.addMensaje(mensaje);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene todos los mensajes de la partida.
	 * 
	 * @param id Integer, identificador de la partida.
	 * @return ArrayList(Mensaje)
	 */
	public ArrayList<Mensaje> getAllMensajeByIdPartida(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			MensajeMapper mensajeMapper = sqlSession.getMapper(MensajeMapper.class);
			return mensajeMapper.getAllMensajeByIdPartida(id);
		} finally {
			sqlSession.close();
		}
	}

}
