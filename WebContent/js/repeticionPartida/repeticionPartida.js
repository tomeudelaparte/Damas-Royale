$(document).ready(function() {

	// Añade el tablero
	setTablero();

	// Añade la acción al botón para ver el movimiento anterior.
	$("#anterior").click(function() {
		setMovimientoAnterior();
	});

	// Añade la acción al botón para ver el movimiento siguiente.
	$("#siguiente").click(function() {
		setMovimientoSiguiente();
	});
});

// Contador de movimientos
var index = -1;

var movimientosPartida;

// Posición ficha eliminada
var fichasEliminadas = [];

// Tablero
var tablero = [ [ 0, 2, 0, 2, 0, 2, 0, 2 ], [ 2, 0, 2, 0, 2, 0, 2, 0 ],
		[ 0, 2, 0, 2, 0, 2, 0, 2 ], [ 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0 ], [ 1, 0, 1, 0, 1, 0, 1, 0 ],
		[ 0, 1, 0, 1, 0, 1, 0, 1 ], [ 1, 0, 1, 0, 1, 0, 1, 0 ] ];

// Carga los movimientos de la partida.
function cargarMovimientos(movimientos) {
	movimientosPartida = movimientos;
}


// Añade las fichas del tablero de la partida.
function setTablero() {

	// Filas del tablero 
	var filas = $(".fila");

	// Fichas de usuario
	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaUsuario.png'>";
	var usuarioReina = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaReinaUsuario.png'>";

	// Fichas de oponente
	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaOponente.png'>";
	var oponenteReina = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaReinaOponente.png'>";

	// Añade las fichas
	for (var i = 0; i < 8; i++) {

		for (var j = 0; j < 8; j++) {

			// Si la posición es 0, eliminar lo que haya dentro.
			if (tablero[i][j] === 0) {

				$(filas[i]).children().eq(j).empty();
			}

			// Si la posición es 1, añade una ficha de usuario.
			if (tablero[i][j] === 1) {

				$(filas[i]).children().eq(j).html(usuario);
			}

			// Si la posición es 2, añade una ficha de oponente.
			if (tablero[i][j] === 2) {

				$(filas[i]).children().eq(j).html(oponente);
			}

			// Si la posición es 13, añade una ficha reina de usuario.
			if (tablero[i][j] === 13) {

				$(filas[i]).children().eq(j).html(usuarioReina);
			}
			
			// Si la posición es 23, añade una ficha reina de oponente.
			if (tablero[i][j] === 23) {

				$(filas[i]).children().eq(j).html(oponenteReina);
			}

		}
	}
}

// Ejecuta el movimiento siguiente
function setMovimientoSiguiente() {
	
	// Mientras index sea menor al número de movimientos.
	if (index < movimientosPartida.length - 1) {
		
		// Suma el index
		index++;
		
		// Realiza el movimiento
		makeMovimiento();
		
		// Actualiza el tablero
		setTablero();
		
		// Actualiza el turno
		setTurno();
		
		// Actualiza el contador.
		updateContador();
	}
}

//Ejecuta el movimiento anterior
function setMovimientoAnterior() {
	
	// Si index es mayor a -1
	if (index > -1) {
		
		// Realiza movimiento invertido
		makeMovimientoInvertido();
		
		// Actualiza el tablero
		setTablero();
		
		// Actualiza el turno invertido
		setTurnoInvertido();
		
		// Resta el index
		index--;
		
		// Actualiza el contador
		updateContador();

	}
}


// Actualiza el contador
function updateContador() {
	$("#movimientos").html(index + 1 + " de " + (movimientosPartida.length));
}

// Actualiza el turno
function setTurno() {
	
	// Obtiene el movimiento
	var movimiento = movimientosPartida[index];

	// Obtiene la ficha de posición destino.
	var seleccionado = tablero[movimiento.fila_destino][movimiento.columna_destino];

	// Si es 1 o 13 añade el turno al usuario
	if (seleccionado == 1 || seleccionado == 13) {

		$('#oponente').removeClass("border border-warning");
		$('#usuario').addClass("border border-warning");

	// Si es 1 o 23 añade el turno al oponente
	} else if (seleccionado == 2 || seleccionado == 23) {

		$('#usuario').removeClass("border border-warning");
		$('#oponente').addClass("border border-warning");

	}
}


// Añade el turno invertido.
function setTurnoInvertido() {

	// Obtiene el movimiento
	var movimiento = movimientosPartida[index];

	// Obtiene la ficha de posición origen.
	var seleccionado = tablero[movimiento.fila_origen][movimiento.columna_origen];

	// Si es 1 o 13 añade el turno al usuario
	if (seleccionado == 1 || seleccionado == 13) {

		$('#oponente').removeClass("border-warning");
		$('#usuario').addClass("border-warning");
		
	// Si es 1 o 23 añade el turno al oponente
	} else if (seleccionado == 2 || seleccionado == 23) {

		$('#usuario').removeClass("border-warning");
		$('#oponente').addClass("border-warning");
	}
}

// Ejecuta el movimiento
function makeMovimiento() {

	// Obtiene el movimiento seleccionado
	var seleccionado = movimientosPartida[index];

	// Obtiene la ficha de posición origen.
	var ficha = reinarDama(
			tablero[seleccionado.fila_origen][seleccionado.columna_origen],
			seleccionado.fila_destino);
	
	// Realiza el movimiento
	tablero[seleccionado.fila_origen][seleccionado.columna_origen] = 0;
	tablero[seleccionado.fila_destino][seleccionado.columna_destino] = ficha;
	
	// Elimina ficha
	eliminarFicha(seleccionado.fila_origen, seleccionado.columna_origen,
			seleccionado.fila_destino, seleccionado.columna_destino);

}


// Ejecuta el movimiento invertido
function makeMovimientoInvertido() {

	// Obtiene el movimiento seleccionado
	var seleccionado = movimientosPartida[index];

	// Obtiene la ficha de posición destino.
	var ficha = reinarDamaInvertido(
			tablero[seleccionado.fila_destino][seleccionado.columna_destino],
			seleccionado.fila_origen);
	
	// Realiza el movimiento
	tablero[seleccionado.fila_destino][seleccionado.columna_destino] = 0;
	tablero[seleccionado.fila_origen][seleccionado.columna_origen] = ficha;

	// Elimina ficha de forma invertida
	eliminarFichaInvertido();

}

// Elimina ficha
function eliminarFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino) {
	
	// Obtiene la longitud del salto por fila y casilla
	var saltoFila = Math.abs(filaOrigen - filaDestino);
	var saltoCasilla = Math.abs(columnaOrigen - columnaDestino);
	
	// Obtiene la posición de la ficha para eliminar.
	var fila = parseInt(filaDestino, 10)
			+ ((parseInt(filaOrigen, 10) - parseInt(filaDestino, 10)) / 2);

	var columna = parseInt(columnaDestino, 10)
			+ ((parseInt(columnaOrigen, 10) - parseInt(columnaDestino, 10)) / 2);

	// Si el salto es de 2
	if (saltoFila == 2 && saltoCasilla == 2) {

		// Se añade la ficha eliminada en un array
		fichasEliminadas[index] = [ tablero[fila][columna], fila, columna ];
		
		// Elimina la ficha del tablero.
		tablero[fila][columna] = 0;

	} else {
		
		// Añade un array vacío.
		fichasEliminadas.push([]);

	}
}

// Elimina ficha de forma invertida
function eliminarFichaInvertido() {

	// Si hay ficha eliminada
	if (fichasEliminadas[index].length !== 0) {
		
		// Colocar ficha
		tablero[fichasEliminadas[index][1]][fichasEliminadas[index][2]] = fichasEliminadas[index][0];

	}
}

// Convierte una ficha en dama
function reinarDama(dama, filaLimite) {

	// Si la ficha es 1 y el limite es 0
	if (dama == 1 && filaLimite == 0) {

		return 13;

	// Si la ficha es 23 y el limite es 6
	} else if (dama == 2 && filaLimite == 7) {

		return 23;

	} else {

		return dama;
	}
}

// Convierte una dama en ficha
function reinarDamaInvertido(dama, filaLimite) {
	
	// Si la ficha es 13 y el limite es 1
	if (dama == 13 && filaLimite == 1) {
		
		return 1;
		
	// Si la ficha es 23 y el limite es 6
	} else if (dama == 23 && filaLimite == 6) {

		return 2;

	} else {

		return dama;
	}
}
