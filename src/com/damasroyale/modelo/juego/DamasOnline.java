package com.damasroyale.modelo.juego;

import java.sql.Timestamp;
import java.util.Date;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;

public class DamasOnline {

	private Integer id;

	private Integer anfitrion;
	private Integer oponente;

	private int turno = 1;

	private int fichasAnfitrion = 12;
	private int fichasOponente = 12;

	private int[] fichaEliminada = new int[2];

	private boolean tablasAnfitrion = false;
	private boolean tablasOponente = false;

	private boolean finalizada = false;

	private int tablero[][] = { { 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 }, { 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 } };

	private Integer turnoUsuario = 0;

	private Integer ganador = 0;
	private Integer perdedor = 0;

	public DamasOnline(Integer id, Integer anfitrion) {

		this.id = id;
		this.anfitrion = anfitrion;
		this.turnoUsuario = anfitrion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTurnoUsuario() {
		return turnoUsuario;
	}

	public Integer getOponente() {
		return oponente;
	}

	public void setOponente(Integer oponente) {
		this.oponente = oponente;
	}

	public void setTurnoUsuario(Integer turnoUsuario) {
		if (turno == 2) {
			this.turnoUsuario = turnoUsuario;
		}
	}

	public Integer getGanador() {
		return ganador;
	}

	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}

	public Integer getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(Integer perdedor) {
		this.perdedor = perdedor;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public int[][] getTablero(Integer idUsuario) {

		if (anfitrion.equals(idUsuario)) {

			return tablero;

		} else {

			return cambiarTablero(tablero);
		}
	}

	public Movimiento mover(Integer idPartida, Integer idUsuario, int filaOrigen, int filaDestino, int columnaOrigen,
			int columnaDestino) {

		if (!anfitrion.equals(idUsuario)) {

			filaOrigen = 7 - filaOrigen;
			filaDestino = 7 - filaDestino;

			columnaOrigen = 7 - columnaOrigen;
			columnaDestino = 7 - columnaDestino;
		}

		if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			realizarMovimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino);

			if (eliminar(fichaEliminada[0], fichaEliminada[1])) {

				if (comprobarDobleSalto(idUsuario, filaDestino, columnaDestino) == false) {

					cambiarTurno(idUsuario);
				}

			} else {
				cambiarTurno(idUsuario);
			}

			comprobarEstadoPartida(idPartida);

			Movimiento movimiento = new Movimiento(0, idPartida, idUsuario, filaOrigen, filaDestino, columnaOrigen,
					columnaDestino);

			return movimiento;

		} else {

			return null;
		}
	}

	private boolean verificarMovimiento(Integer idUsuario, int filaOrigen, int columnaOrigen, int filaDestino,
			int columnaDestino) {

		int origen = this.tablero[filaOrigen][columnaOrigen];
		int destino = this.tablero[filaDestino][columnaDestino];

		if (comprobarOrigen(origen) && comprobarDestino(destino)
				&& (comprobarReina(filaOrigen, columnaOrigen) || comprobarDireccion(idUsuario, filaOrigen, filaDestino))
				&& comprobarDistancia(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			return true;

		} else {

			return false;
		}
	}

	private boolean comprobarOrigen(int origen) {

		if (origen == turno || String.valueOf(origen).equals(turno + "3")) {

			return true;

		} else {

			return false;
		}
	}

	private boolean comprobarDestino(int destino) {

		if (destino == 0) {

			return true;

		} else {

			return false;
		}
	}

	private boolean comprobarDireccion(Integer idUsuario, int filaOrigen, int filaDestino) {

		if (turno == 1 && anfitrion.equals(idUsuario) && filaDestino > filaOrigen) {

			return false;

		} else if (turno == 2 && !anfitrion.equals(idUsuario) && filaDestino < filaOrigen) {

			return false;

		} else {

			return true;
		}
	}

	private boolean comprobarDistancia(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		int saltoFila = Math.abs(filaOrigen - filaDestino);
		int saltoCasilla = Math.abs(columnaOrigen - columnaDestino);

		if (saltoFila == 1 && saltoCasilla == 1) {

			return true;

		} else if (saltoFila == 2 && saltoCasilla == 2
				&& (eliminarFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino).length > 0)) {

			return true;

		} else {

			return false;
		}
	}

	private boolean comprobarDobleSalto(Integer idUsuario, int filaOrigen, int columnaOrigen) {
		if ((filaOrigen + 2) < 8 && (filaOrigen - 2) > 0 && (columnaOrigen + 2) < 8 && (columnaOrigen - 2) > 0) {
			if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen + 2, columnaOrigen + 2)
					|| verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen + 2, columnaOrigen - 2)
					|| verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen - 2, columnaOrigen + 2)
					|| verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen - 2, columnaOrigen - 2)) {

				fichaEliminada = new int[2];

				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	private int[] eliminarFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		int filaPosicion = filaDestino + ((filaOrigen - filaDestino) / 2);
		int casillaPosicion = columnaDestino + ((columnaOrigen - columnaDestino) / 2);

		if (this.tablero[filaPosicion][casillaPosicion] != turno) {

			fichaEliminada[0] = filaPosicion;
			fichaEliminada[1] = casillaPosicion;
		}

		return fichaEliminada;
	}

	private boolean comprobarReina(int fila, int columna) {

		if (tablero[fila][columna] == 13 || tablero[fila][columna] == 23) {
			return true;
		} else {
			return false;
		}
	}

	private void comprobarEstadoPartida(Integer idPartida) {

		if (fichasAnfitrion <= 0) {

			ganador = oponente;
			perdedor = anfitrion;

			finalizada = true;

			finalizarPartida(idPartida);

		} else if (fichasOponente <= 0) {

			ganador = anfitrion;
			perdedor = oponente;

			finalizada = true;

			finalizarPartida(idPartida);
		}
	}

	private int reinarDama(int dama, int filaLimite) {

		if (dama == 1 && filaLimite == 0) {

			return 13;

		} else if (dama == 2 && filaLimite == 7) {

			return 23;

		} else {

			return dama;
		}
	}

	private void realizarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		int dama = reinarDama(tablero[filaOrigen][columnaOrigen], filaDestino);

		this.tablero[filaOrigen][columnaOrigen] = 0;
		this.tablero[filaDestino][columnaDestino] = dama;

	}

	private void cambiarTurno(Integer idUsuario) {

		if (turno == 1 && anfitrion.equals(idUsuario)) {

			turno = 2;

			turnoUsuario = oponente;

		} else if (turno == 2 && !anfitrion.equals(idUsuario)) {

			turno = 1;

			turnoUsuario = anfitrion;
		}

	}

	private boolean eliminar(int fila, int casilla) {

		if (fila != 0 && casilla != 0) {

			this.tablero[fila][casilla] = 0;
			fichaEliminada = new int[2];

			if (turno == 1) {
				fichasOponente--;
			} else if (turno == 2) {
				fichasAnfitrion--;
			}

			return true;
		} else {
			return false;
		}
	}

	private int[][] cambiarTablero(int[][] tablero) {

		int[][] tableroCambiado = new int[8][8];

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				tableroCambiado[i][j] = tablero[i][j];
			}
		}

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {

				if (tableroCambiado[i][j] == 1) {

					tableroCambiado[i][j] = 2;

				} else if (tableroCambiado[i][j] == 2) {

					tableroCambiado[i][j] = 1;

				} else if (tableroCambiado[i][j] == 13) {

					tableroCambiado[i][j] = 23;

				} else if (tableroCambiado[i][j] == 23) {

					tableroCambiado[i][j] = 13;
				}
			}
		}

		for (int i = 0; i < 8 / 2; i++) {

			int[] tmp = tableroCambiado[7 - i];
			tableroCambiado[7 - i] = tableroCambiado[i];
			tableroCambiado[i] = tmp;

		}

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8 / 2; j++) {
				int tmp = tableroCambiado[i][7 - j];
				tableroCambiado[i][7 - j] = tableroCambiado[i][j];
				tableroCambiado[i][j] = tmp;
			}
		}

		return tableroCambiado;
	}

	public boolean comprobarTablas(Integer idUsuario) {

		if (idUsuario == anfitrion) {

			return tablasOponente;

		} else if (idUsuario != anfitrion) {

			return tablasAnfitrion;

		} else {

			return false;
		}

	}

	public void solicitarTablas(Integer idPartida, Integer idUsuario) {

		if (idUsuario == anfitrion) {

			tablasAnfitrion = true;

		} else if (idUsuario != anfitrion) {

			tablasOponente = true;
		}

		if (tablasAnfitrion == true && tablasOponente == true) {

			finalizada = true;

			finalizarPartida(idPartida);
		}

	}

	public void abandonarPartida(Integer idPartida, Integer idUsuario) {

		if (idUsuario == anfitrion) {

			ganador = oponente;
			perdedor = anfitrion;

			finalizada = true;

			finalizarPartida(idPartida);

		} else if (idUsuario != anfitrion) {

			ganador = anfitrion;
			perdedor = oponente;

			finalizada = true;

			finalizarPartida(idPartida);
		}
	}

	public void finalizarPartida(Integer idPartida) {

		PartidaEJB partidaEJB = new PartidaEJB();
		ResultadoEJB resultadoEJB = new ResultadoEJB();

		Partida partida = partidaEJB.getPartidaByID(idPartida);

		Date date = new Date();

		Timestamp fechaHora = new Timestamp(date.getTime());

		if (tablasAnfitrion == true && tablasOponente == true) {

			Resultado resultado = new Resultado(0, idPartida, fechaHora, true, null, null);

			resultadoEJB.addResultadoPartida(resultado);

			partida.setFinalizada(true);

			partidaEJB.updatePartida(partida);

		} else if (oponente != null) {

			Resultado resultado = new Resultado(0, idPartida, fechaHora, false, ganador, perdedor);

			resultadoEJB.addResultadoPartida(resultado);

			partida.setFinalizada(true);

			partidaEJB.updatePartida(partida);

		} else {

			partidaEJB.delPartidaByIdPartida(idPartida);
		}
	}

}
