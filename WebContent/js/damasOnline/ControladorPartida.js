// Client REST
var partidaRestClient;

// Usuario no conectado.
var usuario = false;

// Oponente no conectado.
var jugador = false;

// El oponente no ha solicitado tablas.
var tablas = false;

// Partida actualizada
var partidaActualizada;


// Crea la partida
function crearPartida(idPartida, idUsuario) {
	
	// Crea un nuevo cliente REST
	partidaRestClient = new PartidaRestClient(idPartida, idUsuario);

	// Al pulsar al botón abandonar partida
	$("#abandonarPartida").click(function() {

		// Abandona partida
		partidaRestClient.abandonarPartida();

		// Esconde el panel informativo,
		$("#abandonar").modal("hide");

		// Si no hay oponente
		if (partidaRestClient.oponente == undefined) {
			
			// Reenvia a Jugar, lista de partidas.
			window.location.href = 'Jugar';
		}
	});

	// Al pulsar el botón solicitar tablas
	$("#solicitarTablas").click(function() {

		// Solicita tablas
		partidaRestClient.solicitarTablas();

		// Esconde el panel informativo,
		$("#tablas").modal("hide");

		// Comprueba el estado de la partida.
		controlEstadoPartida();

	});

	// Inhabilita el envio de formulario mediante la tecla Intro.
	// Al presionar una tecla
	$(window).keydown(function(event) {
		
		// Si la tecla es 13
		if (event.keyCode == 13) {
			
			// Evita el evento
			event.preventDefault();
		
			// Enviar el mensaje.
			controlSendMensaje();

		}
	});

	// Partida actualizada cada 3 segundos
	partidaActualizada = setInterval("checkPartida()", 3000);
	
}

// Controla los mensajes cuando el usuario oponente y tu os habeis unido.
function controlJugadorConectado() {

	// Si el oponente no se ha unido y no hay oponente. 
	if (jugador === false && partidaRestClient.oponente !== undefined) {

		// Si el usuario oponente es diferente al usuario.
		if (partidaRestClient.partida.idUsuario_B != partidaRestClient.idUsuario) {

			// Mostrar que se ha unido un usuario.
			jugadorConectado(partidaRestClient.oponente, partidaRestClient.puntuacionOponente);

			// Oponente unido.
			jugador = true;
		
		// Si el usuario no se ha unido y el usuario es el oponente.
		} else if (usuario === false && partidaRestClient.partida.idUsuario_B == partidaRestClient.idUsuario) {
			
			// Mostrar que te has unido.
			jugadorConectadoAlt();

			// Usuario unido.
			usuario = true;
		}

	}

}

// Comprueba cada parte de la partida.
function checkPartida() {

	// Obtiene la partida
	partidaRestClient.getPartida();
	
	// Obtiene los participantes
	partidaRestClient.getJugadores();
	
	// Checkear si un usuario se ha unido.
	controlJugadorConectado();
	
	// Obtiene los mensajes del chat.
	controlGetMensajes();
	
	// Añade el tablero.
	controlSetTablero();
	
	// Comprueba el estado.
	controlEstadoPartida();
	
	// Comprueba el turno.
	controlTurno();
	
	// Comprueba las tablas.
	controlTablas();
}


// Comprueba el estado de la partida para mostrar un panel.
function controlEstadoPartida() {

	// Obtiene el estado
	var estado = partidaRestClient.getEstado();

	// Ha finalizado
	if (estado === true) {

		// Obtiene el ganador
		var ganador = partidaRestClient.getGanador();

		// Si el ganador es el usuario
		if (ganador === partidaRestClient.usuario.id) {

			// Muestra un mensaje de que ha ganado el usuario.
			partidaFinalizadaUsuario();
			
			// Para el intervalo.
			clearInterval(partidaActualizada);

		// Si el ganador es el oponente
		} else if (ganador === partidaRestClient.oponente.id) {

			// Muestra un mensaje de que ha perdido el usuario.
			partidaFinalizadaOponente();
			
			// Para el intervalo.
			clearInterval(partidaActualizada);

		} else {

			// Muestra un mensaje de que ha terminado en tablas.
			partidaTablas(partidaRestClient.usuario, partidaRestClient.oponente);
			
			// Para el intervalo.
			clearInterval(partidaActualizada);
		}
	}
}

// Comprueba el turno para mostrarlo.
function controlTurno() {

	// Obtiene el turno.
	var turno = partidaRestClient.getTurno();

	// Si el turno es del usuario
	if (turno === partidaRestClient.idUsuario) {

		// Muestra un borde dorado alrededor del usuario. 
		addTurnoUsuario();

	// Si el turno es del oponente.
	} else if (turno !== partidaRestClient.idUsuario) {
		
		//Muestra un borde dorado alrededor del oponente. 
		addTurnoOponente();
	}

}

// Comprueba las tablas para mostrar un mensaje.
function controlTablas() {

	// Obtiene las tablas del oponente.
	var tablasOponente = partidaRestClient.comprobarTablas();

	// Si hay tablas y no se ha enviado un mensaje.
	if (tablasOponente === true && tablas === false) {

		// Muestra el mensaje de que el oponente ha solicitado tablas
		tablasSolicitadas();
		
		// Mensaje enviado
		tablas = true;
		
		// Al presionar aceptar tablas
		$("#aceptarTablas").click(function() {

			// Solicita tablas para finalizar la partida
			partidaRestClient.solicitarTablas();
			
			// Comprueba el estado de la partida.
			controlEstadoPartida();
			
			// Elimina el intervalo.
			clearInterval(partidaActualizada);
		});
	}

}

// Añade las fichas al tablero
function controlSetTablero() {

	setTablero(partidaRestClient.getTablero());

	// Añade el evento a las fichas
	$(".ficha.usuario").click(controlSeleccionar);
}

// Envia un mensaje.
function controlSendMensaje() {

	// Obtiene el mensaje del input.
	var mensaje = $("#mensaje").val();

	// Si es mayor a 0 y menor a 255
	if (mensaje.length > 0 && mensaje.length < 255) {

		// Enviar mensaje
		partidaRestClient.sendMensaje(mensaje);

		// Elimina los valores del input
		$("#mensaje").val("");
	}
}

// Obtiene los mensajes del chat.
function controlGetMensajes() {

	// Obtiene los mensajes.
	var mensajes = partidaRestClient.getMensajes();

	// Si hay mensajes los muestra en el chat.
	if (mensajes !== undefined) {
		setMensajes(partidaRestClient.usuario, partidaRestClient.oponente, mensajes);
	}
}


// Seleccion de casilla con ficha.
function controlSeleccionar() {

	// Obtiene la casiila de la ficha
	var casilla = $(this).parent();

	// Obtiene la fila origen de la casilla.
	var filaOrigen = $(casilla).parent().index();
	
	// Obtiene la columna origen de la casilla.
	var columnaOrigen = $(casilla).index();

	// Selecciona la casilla
	seleccionar(casilla);

	// Al presionar sobre una casilla sin ficha
	$(".casilla:not(:has(img))").click( function() {

		// Obtiene la fila destino de la casilla destino.
		var filaDestino = $(this).parent().index();
		
		// Obtiene la columna destino de la casilla destino.
		var columnaDestino = $(this).index();

		// Obtiene el movimiento
		var movimiento = partidaRestClient.makeMovimiento(partidaRestClient.idPartida, partidaRestClient.idUsuario, filaOrigen, filaDestino, columnaOrigen, columnaDestino);
		
		// Si hay movimiento
		if (movimiento !== undefined) {

			// Actualiza el tablero, el turno y cambiael css de movimiento.
			controlSetTablero();
			controlTurno();
			mover(casilla);

		} else {
			
			// Cambia el css de movimiento.
			mover(casilla);
			
			// Muestra un mensaje de movimiento incorrecto.
			$('#incorrecto').modal('show');
		}
	});
}
