var partidaRestClient;

var usuario = false;
var jugador = false;

var tablas = false;

function crearPartida(idPartida, idUsuario) {

	partidaRestClient = new PartidaRestClient(idPartida, idUsuario);

	$("#abandonarPartida").click(function() {

		partidaRestClient.abandonarPartida();

		$("#abandonar").modal("hide");

		if (partidaRestClient.oponente == undefined) {
			window.location.href = 'Jugar';
		}
	});

	$("#solicitarTablas").click(function() {

		partidaRestClient.solicitarTablas();

		$("#tablas").modal("hide");

		controlEstadoPartida();

	});

	$(window).keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			controlSendMensaje();

		}
	});

	setInterval("checkPartida()", 1000);

}

function controlJugadorConectado() {

	if (jugador === false && partidaRestClient.oponente !== undefined) {

		if (partidaRestClient.partida.idUsuario_B != partidaRestClient.idUsuario) {

			jugadorConectado(partidaRestClient.oponente,
					partidaRestClient.puntuacionOponente);

			jugador = true;
		} else if (usuario === false
				&& partidaRestClient.partida.idUsuario_B == partidaRestClient.idUsuario) {
			jugadorConectadoAlt();

			usuario = true;
		}

	}

}

function checkPartida() {

	partidaRestClient.getPartida();
	partidaRestClient.getJugadores();
	controlJugadorConectado();
	controlGetMensajes()
	controlSetTablero()
	controlEstadoPartida();
	controlTurno()
	controlTablas()
}

function checkTablero() {

	controlSetTablero()
	controlTurno()
}

function controlEstadoPartida() {

	var estado = partidaRestClient.getEstado();

	if (estado === true) {

		var ganador = partidaRestClient.getGanador();

		if (ganador === partidaRestClient.usuario.id) {

			partidaFinalizadaUsuario();

		} else if (ganador === partidaRestClient.oponente.id) {

			partidaFinalizadaOponente();

		} else {

			partidaTablas(partidaRestClient.usuario, partidaRestClient.oponente);

		}

	}
}

function controlTurno() {

	var turno = partidaRestClient.getTurno();

	if (turno === partidaRestClient.idUsuario) {

		addTurnoUsuario();

	} else {
		addTurnoOponente();
	}

}

function controlTablas() {

	var tablasOponente = partidaRestClient.comprobarTablas();

	if (tablasOponente === true && tablas === false) {

		tablasSolicitadas();

		tablas = true;
	}

}

function controlSetTablero() {

	setTablero(partidaRestClient.getTablero());

	$(".ficha.usuario").click(controlSeleccionar);
}

function controlSendMensaje() {

	var mensaje = $("#mensaje").val();

	console.log(mensaje);

	if (mensaje.length > 0 && mensaje.length < 255) {

		partidaRestClient.sendMensaje(mensaje);

		$("#mensaje").val("");
	}
}

function controlGetMensajes() {

	var mensajes = partidaRestClient.getMensajes();

	if (mensajes !== undefined) {
		setMensajes(partidaRestClient.usuario, partidaRestClient.oponente,
				mensajes);

	}

}

function controlSeleccionar() {

	var ficha = $(this);

	var casilla = $(this).parent();

	var filaOrigen = $(casilla).parent().index();
	var columnaOrigen = $(casilla).index();

	seleccionar(casilla);

	$(".casilla:not(:has(img))").click(
			function() {

				var filaDestino = $(this).parent().index();
				var columnaDestino = $(this).index();

				var movimiento = partidaRestClient.makeMovimiento(
						partidaRestClient.idPartida,
						partidaRestClient.idUsuario, filaOrigen, filaDestino,
						columnaOrigen, columnaDestino);

				if (movimiento !== undefined) {

					checkTablero();
					mover(casilla);

				} else {
					mover(casilla);
					$('#incorrecto').modal('show');
				}

			});
}
