package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.UsuarioMapper;
import com.damasroyale.modelo.pojo.Usuario;

public class UsuarioDAO {

	public ArrayList<Usuario> getAllUsuario() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getAllUsuario();
		} finally {
			sqlSession.close();
		}
	}

	public Usuario getUsuarioByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getUsuarioByID(id);
		} finally {
			sqlSession.close();
		}
	}

	public Usuario getUsuarioLogin(String email, String contrasenya) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getUsuarioLogin(email, contrasenya);
		} finally {
			sqlSession.close();
		}
	}

	public String getExistUsuario(String nombre, String email) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getExistUsuario(nombre, email);
		} finally {
			sqlSession.close();
		}
	}

	public void addUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.addUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void updateUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.updateUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public void activateUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.activateUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	public void delUsuario(Usuario usuario) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.delUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}
