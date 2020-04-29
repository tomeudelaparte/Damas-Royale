package com.damasroyale.modelo.pojo;

public class Damas {

	private Integer id;
	private Integer anfitrion;

	private int turno = 1;

	private int fichas01 = 12;
	private int fichas02 = 12;

	private int tablero[][] = { 
			{ 0, 2, 0, 2, 0, 2, 0, 2 }, 
			{ 2, 0, 2, 0, 2, 0, 2, 0 }, 
			{ 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, 
			{ 1, 0, 1, 0, 1, 0, 1, 0 } };

	private int[] fichaEliminada = new int[2];

	public Damas(Integer id, Integer anfitrion) {
		
		this.id = id;
		this.anfitrion = anfitrion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int[][] getTablero(Integer idUsuario) {

		if (anfitrion.equals(idUsuario)) {

			return tablero;

		} else {

			return cambiarTablero(tablero);
		}
	}

	public Movimiento mover(Integer idPartida, Integer idUsuario, int filaInicial, int filaFinal, int columnaInicial, int columnaFinal) {

		if (!anfitrion.equals(idUsuario)) {

			filaInicial = (8 - 1) - filaInicial;
			filaFinal = (8 - 1) - filaFinal;

			columnaInicial = (8 - 1) - columnaInicial;
			columnaFinal = (8 - 1) - columnaFinal;
		}

		if (verificarMovimiento(idUsuario, filaInicial, columnaInicial, filaFinal, columnaFinal)) {

			this.tablero[filaInicial][columnaInicial] = 0;
			this.tablero[filaFinal][columnaFinal] = turno;

			eliminar(fichaEliminada[0], fichaEliminada[1]);
			cambiarTurno(idUsuario);

			Movimiento movimiento = new Movimiento(0, idPartida, idUsuario, filaInicial, filaFinal, columnaInicial, columnaFinal);

			return movimiento;
			
		} else {

			return null;
		}
	}

	private boolean verificarMovimiento(Integer idUsuario, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		int origen = this.tablero[filaOrigen][columnaOrigen];
		int destino = this.tablero[filaDestino][columnaDestino];

		if (comprobarOrigen(origen) 
				&& comprobarDestino(destino)
				&& comprobarDireccion(idUsuario, filaOrigen, filaDestino)
				&& comprobarDistancia(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			return true;

		} else {

			return false;
		}
	}

	private boolean comprobarOrigen(int origen) {

		if (origen == turno) {

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

		} else if (saltoFila == 2 && saltoCasilla == 2 && (eliminarFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino).length > 0)) {

			return true;

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

	private void cambiarTurno(Integer idUsuario) {

		if (turno == 1 && anfitrion.equals(idUsuario)) {

			turno = 2;

		} else if (turno == 2 && !anfitrion.equals(idUsuario)) {

			turno = 1;
		}

	}

	private void eliminar(int fila, int casilla) {

		if (fila != 0 && casilla != 0) {

			this.tablero[fila][casilla] = 0;
			fichaEliminada = new int[2];
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

}
