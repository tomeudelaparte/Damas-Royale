package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.pojo.Resultado;

/**
 * Clase EJB para calcular la puntuación de un usuario y el número de partidas ganadas, perdidas y tablas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
@Stateless
@LocalBean
public class PuntuacionEJB {

	// Puntuación de la aplicación.
	private final static int PTS_PARTIDAS_GANADAS = 9;
	private final static int PTS_PARTIDAS_PERDIDAS = 4;
	private final static int PTS_PARTIDAS_TABLAS = 3;

	/**
	 * Obtiene la puntuacióne en base a los resultados.
	 * 
	 * @param idUsuario  Integer, identificador del usuario.
	 * @param resultados ArrayList(Resultado), resultados del usuario.
	 * @return int
	 */
	public int getPuntuacion(Integer idUsuario, ArrayList<Resultado> resultados) {

		int puntuacionTotal = 0;

		// Por cada resultado
		for (Resultado resultado : resultados) {

			// Si el ganador es el usuario, suma puntuación.
			if (resultado.getGanador() == idUsuario) {

				puntuacionTotal += PTS_PARTIDAS_GANADAS;
			}

			// Si el perdedor es el usuario, suma puntuación.
			if (resultado.getPerdedor() == idUsuario) {

				puntuacionTotal += PTS_PARTIDAS_PERDIDAS;
			}

			// Si la partida es tablas, suma puntuación.
			if (resultado.isTablas() == true) {

				puntuacionTotal += PTS_PARTIDAS_TABLAS;
			}
		}

		return puntuacionTotal;

	}

	/**
	 * Obtiene el número de partidas ganadas.
	 * 
	 * @param idUsuario  Integer, identificador del usuario.
	 * @param resultados ArrayList(Resultado), resultados del usuario.
	 * @return int
	 */
	public int getPartidasGanadas(Integer idUsuario, ArrayList<Resultado> resultados) {

		int partidasGanadas = 0;

		// Por cada resultado donde el usuario sea el ganador, suma 1.
		for (Resultado resultado : resultados) {

			if (resultado.getGanador() == idUsuario) {

				partidasGanadas += 1;
			}
		}

		return partidasGanadas;
	}

	/**
	 * Obtiene el número de partidas perdidas.
	 * 
	 * @param idUsuario  Integer, identificador del usuario.
	 * @param resultados ArrayList(Resultado), resultados del usuario.
	 * @return int
	 */
	public int getPartidasPerdidas(Integer idUsuario, ArrayList<Resultado> resultados) {

		int partidasPerdidas = 0;

		// Por cada perdedor donde el usuario sea el ganador, suma 1.
		for (Resultado resultado : resultados) {

			if (resultado.getPerdedor() == idUsuario) {

				partidasPerdidas += 1;
			}
		}

		return partidasPerdidas;
	}

	/**
	 * Obtiene el número de partidas terminadas en tablas.
	 * 
	 * @param resultados ArrayList(Resultado), resultados del usuario.
	 * @return int
	 */
	public int getPartidasTablas(ArrayList<Resultado> resultados) {

		int partidasTablas = 0;

		// Por cada resultado donde la partida sea tablas, suma 1.
		for (Resultado resultado : resultados) {

			if (resultado.isTablas() == true) {

				partidasTablas += 1;
			}
		}

		return partidasTablas;
	}
}
