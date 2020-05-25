package com.damasroyale.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.damasroyale.modelo.dao.mappers.UsuarioMapper;
import com.damasroyale.modelo.pojo.Usuario;

/**
 * Classe DAO para manejar los usuarios.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class UsuarioDAO {

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return ArrayList<Usuario>
	 */
	public ArrayList<Usuario> getAllUsuario() {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getAllUsuario();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene un usuario por su identificador.
	 * 
	 * @param id Integer, identificado del usuario.
	 * @return Usuario
	 */
	public Usuario getUsuarioByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getUsuarioByID(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene un usuario por su correo y contraseña.
	 * 
	 * @param email       String
	 * @param contrasenya String
	 * @return Usuario
	 */
	public Usuario getUsuarioLogin(String email, String contrasenya) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getUsuarioLogin(email, contrasenya);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene el identificador de un usuario por su nombre o correo.
	 * 
	 * @param nombre String
	 * @param email  String
	 * @return String
	 */
	public String getUsuarioExistente(String nombre, String email) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getUsuarioExistente(nombre, email);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene el identificador de un usuario por su nombre.
	 * 
	 * @param nombre String
	 * @return String
	 */
	public String getNombreExistente(String nombre) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.getNombreExistente(nombre);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Añade un usuario.
	 * 
	 * @param usuario Usuario
	 */
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

	/**
	 * Actualiza la información de un usuario.
	 * 
	 * @param usuario Usuario
	 */
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

	/**
	 * Verifica la cuenta de un usuario.
	 * 
	 * @param usuario Usuario
	 */
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

	/**
	 * Elimina un usuario por su identificador.
	 * 
	 * @param id Integer, identificador del usuario.
	 */
	public void delUsuarioByID(Integer id) {

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.delUsuarioByID(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}
