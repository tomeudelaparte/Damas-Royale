package com.damasroyale.modelo.ejb;

import java.util.ArrayList; 

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.damasroyale.modelo.pojo.Resultado;

@Stateless
@LocalBean
public class PuntuacionEJB {

	private final int puntuacionPartidasGanadas = 9;
	private final int puntuacionPartidasPerdidas = 4;
	private final int puntuacionTablas = 3;

	public int getPuntuacion(Integer idUsuario, ArrayList<Resultado> resultados) {

		int puntuacionTotal = 0;

		for (Resultado resultado : resultados) {

			if (resultado.getGanador() == idUsuario) {

				puntuacionTotal += puntuacionPartidasGanadas;
			}

			if (resultado.getPerdedor() == idUsuario) {

				puntuacionTotal += puntuacionPartidasPerdidas;
			}

			if (resultado.isTablas() == true) {

				puntuacionTotal += puntuacionTablas;
			}
		}

		return puntuacionTotal;

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

	public int getTablas(ArrayList<Resultado> resultados) {

		int tablas = 0;

		for (Resultado resultado : resultados) {

			if (resultado.isTablas() == true) {

				tablas += 1;
			}
		}

		return tablas;
	}
}
