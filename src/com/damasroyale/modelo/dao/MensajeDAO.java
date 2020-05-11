package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.MensajeMapper;
import com.damasroyale.modelo.pojo.Mensaje;

public class MensajeDAO {

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

	public ArrayList<Mensaje> getMensajesByIdPartida(Integer idPartida) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MensajeMapper mensajeMapper = sqlSession.getMapper(MensajeMapper.class);
			return mensajeMapper.getMensajesByIdPartida(idPartida);
		} finally {
			sqlSession.close();
		}
	}

}
