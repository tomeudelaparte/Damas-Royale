package com.damasroyale.modelo.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.pojo.Resultado;

@Stateless
@LocalBean
public class PuntuacionEJB {

	public int getPuntuacion(Integer idUsuario, ArrayList<Resultado> resultados) {

		int puntuacion = 0;

		int ganar = 10;
		int perder = 5;
		int tablas = 5;

		for (Resultado resultado : resultados) {

			if (resultado.getGanador() == idUsuario) {

				puntuacion += ganar;
			}

			if (resultado.getPerdedor() == idUsuario) {

				puntuacion += perder;
			}

			if (resultado.isTablas() == true) {

				puntuacion += tablas;
			}
		}

		return puntuacion;

	}

	public int getPartidasGanadas(Integer idUsuario, ArrayList<Resultado> resultados) {

		int partidasGanadas = 0;

		for (Resultado resultado : resultados) {

			if (resultado.getGanador() == idUsuario) {

				partidasGanadas += 1;
			}
		}

		return partidasGanadas;
	}
	
	public int getPartidasPerdidas(Integer idUsuario, ArrayList<Resultado> resultados) {

		int partidasPerdidas = 0;

		for (Resultado resultado : resultados) {

			if (resultado.getPerdedor() == idUsuario) {

				partidasPerdidas += 1;
			}
		}

		return partidasPerdidas;
	}
	
	public int getPartidasTablas(ArrayList<Resultado> resultados) {

		int partidasTablas = 0;

		for (Resultado resultado : resultados) {

			if (resultado.isTablas() == true) {

				partidasTablas += 1;
			}
		}

		return partidasTablas;
	}
}
