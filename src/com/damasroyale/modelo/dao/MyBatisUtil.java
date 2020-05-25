package com.damasroyale.modelo.dao;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Clase para obtener la configuración de MyBatis.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class MyBatisUtil {
	private static SqlSessionFactory factory;

	// Logger para obtener la información debug de MyBatis.
	private static final Logger logger = (Logger) LoggerFactory.getLogger(MyBatisUtil.class);

	/**
	 * Constructor
	 */
	private MyBatisUtil() {

	}

	// Se ejecuta una vez para la configuración.
	static {

		Reader reader = null;

		try {

			reader = Resources.getResourceAsReader("mybatis-config.xml");

		} catch (IOException e) {

			logger.debug(e.getMessage());
		}

		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	/**
	 * Devuelve SqlSessionFactory
	 * 
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {

		return factory;
	}
}