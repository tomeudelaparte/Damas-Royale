$(document).ready(function() {

	setTablero();

	$("#anterior").click(function() {
		setMovimientoAnterior();
	});

	$("#siguiente").click(function() {
		setMovimientoSiguiente();
	});
});

var index = -1;

var movimientosPartida;

var fichasEliminadas = [];

var tablero = [ [ 0, 2, 0, 2, 0, 2, 0, 2 ], [ 2, 0, 2, 0, 2, 0, 2, 0 ],
		[ 0, 2, 0, 2, 0, 2, 0, 2 ], [ 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0 ], [ 1, 0, 1, 0, 1, 0, 1, 0 ],
		[ 0, 1, 0, 1, 0, 1, 0, 1 ], [ 1, 0, 1, 0, 1, 0, 1, 0 ] ];

function cargarMovimientos(movimientos) {
	movimientosPartida = movimientos;
}

function setTablero() {

	var filas = $(".fila");

	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaUsuario.png'>";
	var usuarioReina = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaReinaUsuario.png'>";

	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaOponente.png'>";
	var oponenteReina = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaReinaOponente.png'>";

	for (var i = 0; i < 8; i++) {

		for (var j = 0; j < 8; j++) {

			if (tablero[i][j] === 0) {

				$(filas[i]).children().eq(j).empty();
			}

			if (tablero[i][j] === 1) {

				$(filas[i]).children().eq(j).html(usuario);
			}

			if (tablero[i][j] === 2) {

				$(filas[i]).children().eq(j).html(oponente);
			}

			if (tablero[i][j] === 13) {

				$(filas[i]).children().eq(j).html(usuarioReina);
			}

			if (tablero[i][j] === 23) {

				$(filas[i]).children().eq(j).html(oponenteReina);
			}

		}
	}
}

function setMovimientoSiguiente() {

	if (index < movimientosPartida.length - 1) {
		index++;
		makeMovimiento();
		setTablero();
		setTurno();
		updateContador();
	}
}

function setMovimientoAnterior() {

	if (index > -1) {
		makeMovimientoInvertido();
		setTablero();
		setTurnoInvertido();
		index--;
		updateContador();

	}
}

function updateContador() {
	$("#movimientos").html(index + 1 + " de " + (movimientosPartida.length));
}

function setTurno() {

	var movimiento = movimientosPartida[index];

	var seleccionado = tablero[movimiento.fila_destino][movimiento.columna_destino];

	if (seleccionado == 1 || seleccionado == 13) {

		$('#oponente').removeClass("border border-warning");
		$('#usuario').addClass("border border-warning");

	} else if (seleccionado == 2 || seleccionado == 23) {

		$('#usuario').removeClass("border border-warning");
		$('#oponente').addClass("border border-warning");

	}
}

function setTurnoInvertido() {

	var movimiento = movimientosPartida[index];

	var seleccionado = tablero[movimiento.fila_origen][movimiento.columna_origen];

	if (seleccionado == 1 || seleccionado == 13) {

		$('#oponente').removeClass("border-warning");
		$('#usuario').addClass("border-warning");

	} else if (seleccionado == 2 || seleccionado == 23) {

		$('#usuario').removeClass("border-warning");
		$('#oponente').addClass("border-warning");
	}
}

function makeMovimiento() {

	var seleccionado = movimientosPartida[index];

	var ficha = reinarDama(
			tablero[seleccionado.fila_origen][seleccionado.columna_origen],
			seleccionado.fila_destino);

	tablero[seleccionado.fila_origen][seleccionado.columna_origen] = 0;
	tablero[seleccionado.fila_destino][seleccionado.columna_destino] = ficha;

	eliminarFicha(seleccionado.fila_origen, seleccionado.columna_origen,
			seleccionado.fila_destino, seleccionado.columna_destino);

}

function makeMovimientoInvertido() {

	var seleccionado = movimientosPartida[index];

	var ficha = reinarDamaInvertido(
			tablero[seleccionado.fila_destino][seleccionado.columna_destino],
			seleccionado.fila_origen);

	tablero[seleccionado.fila_destino][seleccionado.columna_destino] = 0;
	tablero[seleccionado.fila_origen][seleccionado.columna_origen] = ficha;

	eliminarFichaInvertido();

}

function eliminarFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino) {

	var saltoFila = Math.abs(filaOrigen - filaDestino);
	var saltoCasilla = Math.abs(columnaOrigen - columnaDestino);

	var fila = parseInt(filaDestino, 10)
			+ ((parseInt(filaOrigen, 10) - parseInt(filaDestino, 10)) / 2);

	var columna = parseInt(columnaDestino, 10)
			+ ((parseInt(columnaOrigen, 10) - parseInt(columnaDestino, 10)) / 2);

	if (saltoFila == 2 && saltoCasilla == 2) {

		fichasEliminadas[index] = [ tablero[fila][columna], fila, columna ];

		tablero[fila][columna] = 0;

	} else {

		fichasEliminadas.push([]);

	}
}

function eliminarFichaInvertido() {

	if (fichasEliminadas[index].length !== 0) {

		tablero[fichasEliminadas[index][1]][fichasEliminadas[index][2]] = fichasEliminadas[index][0];

	}
}

function reinarDama(dama, filaLimite) {

	if (dama == 1 && filaLimite == 0) {

		return 13;

	} else if (dama == 2 && filaLimite == 7) {

		return 23;

	} else {

		return dama;
	}
}

function reinarDamaInvertido(dama, filaLimite) {

	if (dama == 13 && filaLimite == 1) {

		return 1;

	} else if (dama == 23 && filaLimite == 6) {

		return 2;

	} else {

		return dama;
	}
}
