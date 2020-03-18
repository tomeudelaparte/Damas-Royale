package com.damasroyale.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.ActivacionMapper;
import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

public class ActivacionDAO {

	public void addActivacion(Activacion activacion) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ActivacionMapper activacionMapper = sqlSession.getMapper(ActivacionMapper.class);
			activacionMapper.addActivacion(activacion);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public void delActivacion(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ActivacionMapper activacionMapper = sqlSession.getMapper(ActivacionMapper.class);
			activacionMapper.delActivacion(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public Usuario checkActivacion(String codigo) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ActivacionMapper activacionMapper = sqlSession.getMapper(ActivacionMapper.class);
			return activacionMapper.checkActivacion(codigo);
		} finally {
			sqlSession.close();
		}
	}

}
