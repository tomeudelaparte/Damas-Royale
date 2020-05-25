package com.damasroyale.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.ActivacionMapper;
import com.damasroyale.modelo.pojo.Activacion;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Clase DAO para manejar las verificaciones de las cuentas de usuario.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class ActivacionDAO {

	/**
	 * Añade una activación vinculada a un usuario.
	 * 
	 * @param activacion Activacion
	 */
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

	/**
	 * Obtiene un usuario a partir un código a modo de comprobación.
	 * 
	 * @param codigo String
	 * @return Usuario
	 */
	public Usuario checkActivacion(String codigo) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ActivacionMapper activacionMapper = sqlSession.getMapper(ActivacionMapper.class);
			return activacionMapper.checkActivacion(codigo);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Elimina la activación vinculada a un usuario.
	 * 
	 * @param usuario Usuario
	 */
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

}
