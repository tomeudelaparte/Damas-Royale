package com.damasroyale.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.UsuarioMapper;
import com.damasroyale.modelo.pojo.Usuario; 


public class UsuarioDAO {


	public void addUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuariosMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuariosMapper.addUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void activateUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuariosMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuariosMapper.activateUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public void delUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuariosMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuariosMapper.delUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}


	public Usuario getUsuarioLogin(String email, String contrasenya) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuariosMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuariosMapper.getUsuarioLogin(email, contrasenya);
		} finally {
			sqlSession.close();
		}
	}

	public String getExistUsuario(String nombre, String email) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuariosMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuariosMapper.getExistUsuario(nombre, email);
		} finally {
			sqlSession.close();
		}
	}

}
