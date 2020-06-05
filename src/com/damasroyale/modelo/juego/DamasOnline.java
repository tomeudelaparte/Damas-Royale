package com.damasroyale.modelo.juego;

import java.sql.Timestamp;
import java.util.Date;

import com.damasroyale.modelo.ejb.PartidaEJB;
import com.damasroyale.modelo.ejb.ResultadoEJB;
import com.damasroyale.modelo.pojo.Movimiento;
import com.damasroyale.modelo.pojo.Partida;
import com.damasroyale.modelo.pojo.Resultado;

/**
 * Clase núcleo de la lógica del juego de las damas.
 * 
 * @author Tomeu de la Parte Mulet
 *
 */
public class DamasOnline {

	// Identificador de la partida.
	private Integer id;

	// Identificadores de los usuarios participantes.
	private Integer anfitrion;
	private Integer oponente;
	
	// Tablero virtual sobre el que se realizan los movimientos.
	private int[][] tablero = {
			{ 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0 }, 
			{ 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, 
			{ 1, 0, 1, 0, 1, 0, 1, 0 } };

	// Turno principal, el anfitrión siempre empieza.
	private int turno = 1;
	
	// Identificador del usuario al que le corresponde el turno.
	private Integer turnoUsuario = 0;

	// Número de fichas de cada participante.
	private int fichasAnfitrion = 12;
	private int fichasOponente = 12;

	// Posición de la ficha comida para ser eliminada.
	private int[] fichaEliminada = new int[2];

	// Participantes que ha solicitado acordar tablas.
	private boolean tablasAnfitrion = false;
	private boolean tablasOponente = false;

	// Estado de la partida.
	private boolean finalizada = false;

	// Identificador del usuario ganador y perdedor.
	private Integer ganador;
	private Integer perdedor;

	/**
	 * Constructor del juego.
	 * 
	 * @param id        Integer, identificador de la partida.
	 * @param anfitrion Integer, identificador del usuario anfitrión.
	 */
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
	
	/**
	 * Añade el turno al usuario oponente si le corresponde.
	 * 
	 * @param turnoUsuario Integer
	 */
	public void setTurnoUsuario(Integer turnoUsuario) {
		
		if (turno == 2) {
			
			this.turnoUsuario = turnoUsuario;
		}
	}

	public Integer getOponente() {
		return oponente;
	}

	public void setOponente(Integer oponente) {
		this.oponente = oponente;
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

	/**
	 * Devuelve el tablero con una perspectiva u otra según el usuario.
	 * 
	 * @param idUsuario Integer, identificador del usuario.
	 * @return int[][]
	 */
	public int[][] getTablero(Integer idUsuario) {

		if (anfitrion.equals(idUsuario)) {

			return tablero;

		} else {

			return invertirTablero(tablero);
		}
	}

	/**
	 * Realiza un movimiento realizado por un usuario.
	 * 
	 * @param idPartida      Integer, identificador de la partida.
	 * @param idUsuario      Integer, identificador del usuario.
	 * @param filaOrigen     int, fila origen de la ficha.
	 * @param filaDestino    int, fila destino de la ficha.
	 * @param columnaOrigen  int, columna origen de la ficha.
	 * @param columnaDestino int, columna destino de la ficha .
	 * @return Movimiento
	 */
	public Movimiento mover(Integer idPartida, Integer idUsuario, int filaOrigen, int filaDestino, int columnaOrigen,
			int columnaDestino) {

		// Si el usuario no es el anfitrión, invierte las posiciones del movimiento de la ficha.
		if (!anfitrion.equals(idUsuario)) {

			filaOrigen = 7 - filaOrigen;
			filaDestino = 7 - filaDestino;

			columnaOrigen = 7 - columnaOrigen;
			columnaDestino = 7 - columnaDestino;
		}

		// Si el movimiento realizado cumple con las condiciones.
		if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			// Realiza el movimiento.
			realizarMovimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino);

			// Si se come una ficha.
			if (comerFicha(fichaEliminada[0], fichaEliminada[1])) {

				// Comprueba que no pueda dar más saltos para cambiar de turno.
				if (comprobarSaltoEncadenado(idUsuario, filaDestino, columnaDestino) == false) {

					cambiarTurno(idUsuario);
				}

			} else {

				// Cambia de turno.
				cambiarTurno(idUsuario);
			}

			// Comprueba el estado de la partida tras el movimiento realizado.
			comprobarEstadoPartida(idPartida);

			// Devuelve el movimiento realizado.
			return new Movimiento(0, idPartida, idUsuario, filaOrigen, filaDestino, columnaOrigen, columnaDestino);

		} else {

			// Devuelve null debido a que no ha cumplido las condiciones necesarias.
			return null;
		}
	}
	
	/**
	 * Cambia el turno del jugador.
	 * 
	 * @param idUsuario Integer, identificador del usuario.
	 */
	private void cambiarTurno(Integer idUsuario) {

		// Si turno es 1 y el usuario es igual al anfitrión.
		if (turno == 1 && anfitrion.equals(idUsuario)) {

			// Turno cambia a 2.
			turno = 2;

			// TurnoUsuario cambia al id del oponente.
			turnoUsuario = oponente;

		} else if (turno == 2 && !anfitrion.equals(idUsuario)) {

			// Turno cambia a 1.
			turno = 1;

			// TurnoUsuario cambia al id del anfitrión.
			turnoUsuario = anfitrion;
		}

	}

	/**
	 * Realiza el movimiento en el tablero virtual.
	 * 
	 * @param filaOrigen     int, fila origen de la ficha.
	 * @param columnaOrigen  int, columna origen de la ficha.
	 * @param filaDestino    int, fila destino de la ficha.
	 * @param columnaDestino int, columna destino de la ficha.
	 */
	private void realizarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		// Obtiene la ficha correspondiente a la posición con la posibilidad de convertirse en reina.
		int dama = reinarDama(tablero[filaOrigen][columnaOrigen], filaDestino);

		// Cambia la posición origen por una posición vacía y la posición destino por la ficha.
		this.tablero[filaOrigen][columnaOrigen] = 0;
		this.tablero[filaDestino][columnaDestino] = dama;
	}

	/**
	 * Convierte la ficha en reina.
	 * 
	 * @param dama       int, ficha
	 * @param filaLimite int, fila
	 * @return int, ficha
	 */
	private int reinarDama(int dama, int filaLimite) {

		// Si la ficha es de jugador 1 y el límite de la fila es 0
		if (dama == 1 && filaLimite == 0) {

			// Devuelve 13
			return 13;
		}

		// Si la ficha es de jugador 2 y el límite de la fila es 7
		if (dama == 2 && filaLimite == 7) {

			// Devuelve 23
			return 23;
		}

		// En cambio, devuelve la ficha igual.
		return dama;
	}

	/**
	 * Elimina la ficha que ha sido comida.
	 * 
	 * @param fila    int, posición de la fila.
	 * @param columna int, posición de la columna.
	 * @return boolean
	 */
	private boolean comerFicha(int fila, int columna) {

		// Si la fila y la columna es difente a 0
		if (fila != 0 && columna != 0) {

			// Elimina la ficha del tablero y reinicia el array.
			this.tablero[fila][columna] = 0;
			fichaEliminada = new int[2];

			// Si turno es 1
			if (turno == 1) {

				// Resta el número de fichas al oponente.
				fichasOponente--;

				// Si turno es 2
			} else if (turno == 2) {

				// Resta el número de fichas al anfitrión.
				fichasAnfitrion--;
			}

			// Devuele que ha eliminado una ficha.
			return true;
		}

		// Devuelve que no ha eliminado una ficha.
		return false;
	}

	/**
	 * Verifica el movimiento realizado.
	 * 
	 * @param idUsuario      Integer, identificador del usuario.
	 * @param filaOrigen     int, fila origen de la ficha.
	 * @param columnaOrigen  int, columna origen de la ficha.
	 * @param filaDestino    int, fila destino de la ficha.
	 * @param columnaDestino int, columna destino de la ficha.
	 * @return boolean
	 */
	private boolean verificarMovimiento(Integer idUsuario, int filaOrigen, int columnaOrigen, int filaDestino,
			int columnaDestino) {

		// Posición origen y destino del movimiento de la ficha.
		int origen = this.tablero[filaOrigen][columnaOrigen];
		int destino = this.tablero[filaDestino][columnaDestino];

		// Si cumple con el origen, destino, reina o direccion y distancia, devuelve true, en cambio, devuelve false.
		if (comprobarOrigen(origen) && comprobarDestino(destino)
				&& (comprobarReina(filaOrigen, columnaOrigen) || comprobarDireccion(idUsuario, filaOrigen, filaDestino))
				&& comprobarDistancia(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			return true;

		} else {

			return false;
		}
	}

	/**
	 * Comprueba la posición origen del movimiento de la ficha.
	 * 
	 * @param origen int, posición de la ficha.
	 * @return boolean
	 */
	private boolean comprobarOrigen(int origen) {

		// Si la ficha es igual al turno que corresponde. Se comprueba las fichas reina,
		// 13 si el turno es de 1 o 23 si el turno es de 2.
		if (origen == turno || String.valueOf(origen).equals(turno + "3")) {

			return true;

		} else {

			return false;
		}
	}

	/**
	 * Comprueba el destino del movimiento la ficha. 0 indica que la posición está vacía.
	 * 
	 * @param destino int, posición de la ficha.
	 * @return boolean
	 */
	private boolean comprobarDestino(int destino) {

		if (destino == 0) {

			return true;

		} else {

			return false;
		}
	}

	/**
	 * Comprueba la dirección del movimiento de la ficha.
	 * 
	 * @param idUsuario   Integer, identificador del usuario.
	 * @param filaOrigen  int, fila origen de la ficha.
	 * @param filaDestino int, fila destino de la ficha.
	 * @return boolean
	 */
	private boolean comprobarDireccion(Integer idUsuario, int filaOrigen, int filaDestino) {

		// Si turno es 1 y el usuario es igual al anfitrion y la fila destino es mayor a la fila origen.
		if (turno == 1 && anfitrion.equals(idUsuario) && filaDestino > filaOrigen) {

			return false;

		}

		// Si turno es 2 y el usuario es diferente al anfitrion y la fila destino es menor a la fila origen.
		if (turno == 2 && !anfitrion.equals(idUsuario) && filaDestino < filaOrigen) {

			return false;

		}

		return true;

	}

	/**
	 * Comprueba que la posición de la ficha sea reina.
	 * 
	 * @param fila    int, posición en filas
	 * @param columna int, posición en columnas
	 * @return boolean
	 */
	private boolean comprobarReina(int fila, int columna) {

		// Si la posición es 13 o 23 representado a una dama reina.
		if (tablero[fila][columna] == 13 || tablero[fila][columna] == 23) {

			return true;
		}

		return false;
	}

	/**
	 * Comprueba la distancia del movimiento de la ficha.
	 * 
	 * @param filaOrigen     int, fila origen de la ficha.
	 * @param columnaOrigen  int, columna origen de la ficha.
	 * @param filaDestino    int, fila destino de la ficha.
	 * @param columnaDestino int, columna destino de la ficha.
	 * @return boolean
	 */
	private boolean comprobarDistancia(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		// El número que indica el salto en filas.
		int saltoFila = Math.abs(filaOrigen - filaDestino);

		// El número que indica el salto en columnas.
		int saltoColumna = Math.abs(columnaOrigen - columnaDestino);

		// Si el salto es de 1 fila y 1 columna.
		if (saltoFila == 1 && saltoColumna == 1) {

			return true;
		}

		// Si el salto es de 2 filas y 2 columnas y se elimina una ficha.
		if (saltoFila == 2 && saltoColumna == 2
				&& eliminarFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

			return true;
		}

		return false;
	}

	/**
	 * Obtiene la posición de la ficha comida que para ser eliminada.
	 * 
	 * @param filaOrigen     int, fila origen de la ficha.
	 * @param columnaOrigen  int, columna origen de la ficha.
	 * @param filaDestino    int, fila destino de la ficha.
	 * @param columnaDestino int, columna destino de la ficha.
	 * @return boolean
	 */
	private boolean eliminarFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

		// Número de la fila y de la columna de la ficha comida. Obtenido mediante una media de los dos valores.
		int filaPosicion = filaDestino + ((filaOrigen - filaDestino) / 2);
		int columnaPosicion = columnaDestino + ((columnaOrigen - columnaDestino) / 2);

		// Si la ficha es diferente al turno y la posición no está vacía.
		if (this.tablero[filaPosicion][columnaPosicion] != turno && this.tablero[filaPosicion][columnaPosicion] != 0) {

			fichaEliminada[0] = filaPosicion;
			fichaEliminada[1] = columnaPosicion;

			return true;
		}

		return false;
	}

	/**
	 * Comprueba la posibilidad de un siguiente movimiento para comer una ficha sin cambiar el turno.
	 * 
	 * @param idUsuario     Integer, identificador del usuario.
	 * @param filaOrigen    int, fila origen de la ficha.
	 * @param columnaOrigen int, columna origen de la ficha.
	 * @return boolean
	 */
	private boolean comprobarSaltoEncadenado(Integer idUsuario, int filaOrigen, int columnaOrigen) {

		// Si un salto de +2 filas es menor a 8 y un salto de +2 columnas es menor a 8.
		if ((filaOrigen + 2) < 8 && (columnaOrigen + 2) < 8) {

			// Verifica el posible movimiento para comer una ficha.
			if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen + 2, columnaOrigen + 2)) {

				return true;
			}
		}

		// Si un salto de +2 filas es menor a 8 y un salto de -2 columnas es mayor o igual a 0.
		if ((filaOrigen + 2) < 8 && (columnaOrigen - 2) >= 0) {

			// Verifica el posible movimiento para comer una ficha.
			if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen + 2, columnaOrigen - 2)) {

				return true;
			}
		}

		// Si un salto de -2 filas es mayor o igual a 0 y un salto de +2 columnas es menor a 8.
		if ((filaOrigen - 2) >= 0 && (columnaOrigen + 2) < 8) {

			// Verifica el posible movimiento para comer una ficha.
			if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen - 2, columnaOrigen + 2)) {

				return true;
			}
		}

		// Si un salto de -2 filas es mayor o igual a 0 y un salto de -2 columnas es mayor o igual a 0.
		if ((filaOrigen - 2) >= 0 && (columnaOrigen - 2) >= 0) {

			// Verifica el posible movimiento para comer una ficha.
			if (verificarMovimiento(idUsuario, filaOrigen, columnaOrigen, filaOrigen - 2, columnaOrigen - 2)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Invierte el tablero virtual.
	 * 
	 * @param tablero int [][]
	 * @return int [][]
	 */
	private int[][] invertirTablero(int[][] tablero) {

		// Pasa el array original a otro array.
		int[][] tableroInvertido = new int[8][8];

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				
				tableroInvertido[i][j] = tablero[i][j];
			}
		}

		// Convierte las fichas del anfitrión a las fichas del oponente y viceversa.
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {

				if (tableroInvertido[i][j] == 1) {

					tableroInvertido[i][j] = 2;

				} else if (tableroInvertido[i][j] == 2) {

					tableroInvertido[i][j] = 1;

				} else if (tableroInvertido[i][j] == 13) {

					tableroInvertido[i][j] = 23;

				} else if (tableroInvertido[i][j] == 23) {

					tableroInvertido[i][j] = 13;
				}
			}
		}

		// Gira el tablero de forma vertical por filas.
		for (int i = 0; i < 8 / 2; i++) {

			// La variable temporal guarda la posición invertida.
			int[] tmp = tableroInvertido[7 - i];

			// La posición invertida guarda la posición actual.
			tableroInvertido[7 - i] = tableroInvertido[i];

			// La posición actual guarda la posición invertida.
			tableroInvertido[i] = tmp;

		}

		// Gira el tablero de forma horizontal por filas y columnas.
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8 / 2; j++) {

				// La variable temporal guarda la posición invertida.
				int tmp = tableroInvertido[i][7 - j];

				// La posición invertida guarda la posición actual.
				tableroInvertido[i][7 - j] = tableroInvertido[i][j];

				// La posición actual guarda la posición invertida.
				tableroInvertido[i][j] = tmp;
			}
		}

		return tableroInvertido;
	}

	/**
	 * Comprueba que un usuario haya solicitado acordar tablas.
	 * 
	 * @param idUsuario Integer, identificador del usuario.
	 * @return boolean
	 */
	public boolean comprobarTablas(Integer idUsuario) {

		// Si el usuario es igual al anfitrión.
		if (idUsuario == anfitrion) {

			return tablasOponente;
		}

		// Si el usuario es diferente al anfitrión.
		if (idUsuario != anfitrion) {

			return tablasAnfitrion;
		}

		return false;
	}

	/**
	 * Solicita acordar tablas según el usuario.
	 * 
	 * @param idPartida Integer, identificador del usuario.
	 * @param idUsuario Integer, identificador de la partida.
	 */
	public void solicitarTablas(Integer idPartida, Integer idUsuario) {

		// Si el usuario es igual al anfitrión.
		if (idUsuario == anfitrion) {

			tablasAnfitrion = true;

			// Si el usuario es diferente al anfitrión.
		} else if (idUsuario != anfitrion) {

			tablasOponente = true;
		}

		// Si los dos usuarios ha solicitado tablas, finaliza la partida en un empate.
		if (tablasAnfitrion == true && tablasOponente == true) {

			finalizada = true;

			finalizarPartida(idPartida);
		}

	}

	/**
	 * Finaliza forzosamente la partida por un usuario dando la victoria al contrario.
	 * 
	 * @param idPartida Integer, identificador del usuario.
	 * @param idUsuario Integer, identificador de la partida.
	 */
	public void abandonarPartida(Integer idPartida, Integer idUsuario) {

		// Si el usuario es igual al anfitrion
		if (idUsuario == anfitrion) {

			// Almacena el ganador y perdedor.
			ganador = oponente;
			perdedor = anfitrion;

			// Indica que la partida ha finalizado.
			finalizada = true;

			// Finaliza la partida.
			finalizarPartida(idPartida);

			// Si el usuario es diferente al anfitrion.
		} else if (idUsuario != anfitrion) {

			// Almacena el ganador y perdedor.
			ganador = anfitrion;
			perdedor = oponente;

			// Indica que la partida ha finalizado.
			finalizada = true;

			// Finaliza la partida.
			finalizarPartida(idPartida);
		}
	}

	/**
	 * Comprueba el estado de la partida.
	 * 
	 * @param idPartida Integer, identificador de la partida.
	 */
	private void comprobarEstadoPartida(Integer idPartida) {

		// Si el número de fichas del anfitrión ha llegado a 0.
		if (fichasAnfitrion <= 0) {

			// Almacena el ganador y perdedor.
			ganador = oponente;
			perdedor = anfitrion;

			// Indica que la partida ha finalizado.
			finalizada = true;

			// Finaliza la partida.
			finalizarPartida(idPartida);

			// Si el número de fichas del oponente ha llegado a 0.
		} else if (fichasOponente <= 0) {

			// Almacena el ganador y perdedor.
			ganador = anfitrion;
			perdedor = oponente;

			// Indica que la partida ha finalizado.
			finalizada = true;

			// Finaliza la partida.
			finalizarPartida(idPartida);
		}
	}

	/**
	 * Finaliza la partida añadiendo el resultado.
	 * 
	 * @param idPartida Integer, identificador de la partida.
	 */
	public void finalizarPartida(Integer idPartida) {

		PartidaEJB partidaEJB = new PartidaEJB();
		ResultadoEJB resultadoEJB = new ResultadoEJB();

		// Obtiene la partida.
		Partida partida = partidaEJB.getPartidaByID(idPartida);

		// Obtiene la fecha actual.
		Date date = new Date();

		// Obtiene el timestamp a partir de la fecha.
		Timestamp fechaHora = new Timestamp(date.getTime());

		// Si los dos participantes han solicitado tablas.
		if (tablasAnfitrion == true && tablasOponente == true) {

			// Crea un resultado donde no hay ganador ni perdedor solo tablas.
			Resultado resultado = new Resultado(0, idPartida, fechaHora, true, null, null);

			// Añade el resultado.
			resultadoEJB.addResultadoPartida(resultado);

			// Actualiza la información de la partida.
			partida.setFinalizada(true);

			partidaEJB.updatePartida(partida);

			// Si el oponente no es null
		} else if (oponente != null) {

			// Crea un resultado con ganador y perdedor.
			Resultado resultado = new Resultado(0, idPartida, fechaHora, false, ganador, perdedor);

			// Añade el resultado.
			resultadoEJB.addResultadoPartida(resultado);

			// Actualiza la información de la partida.
			partida.setFinalizada(true);

			partidaEJB.updatePartida(partida);

			// En cambio, elimina la partida.
		} else {

			partidaEJB.delPartidaByID(idPartida);
		}
	}

}
