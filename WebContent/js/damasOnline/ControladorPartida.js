var partidaRestClient;

var jugador = false;

function crearPartida(idPartida, idUsuario) {

	partidaRestClient = new PartidaRestClient(idPartida, idUsuario);
		
	controlSetTablero();
	
	$("#abandonarPartida").click(function() {
		
		partidaRestClient.abandonarPartida();
		
		$("#abandonar").modal("hide");
		
		if(partidaRestClient.oponente == undefined) {
			window.location.href = 'Jugar';
		}
	});
	
	setInterval("checkPartida()", 1000);

}

function controlJugadorConectado() {
	
	if (jugador === false && partidaRestClient.oponente !== undefined) {
		
		if(partidaRestClient.partida.idUsuario_B != partidaRestClient.idUsuario) {
			
			jugadorConectado(partidaRestClient.oponente, partidaRestClient.puntuacionOponente);
			
			jugador = true;
		}

	}

}

function checkPartida() {

	partidaRestClient.getPartida();
	partidaRestClient.getJugadores();
	controlEstadoPartida();
	controlJugadorConectado();
	controlSetTablero();
	controlTurno();
}

function controlEstadoPartida(){
	
	var estado = partidaRestClient.getEstado();
		
	if(estado === true) {
		var ganador = partidaRestClient.getGanador();
		console.log(ganador);
		var nombre;
				
		if(ganador === partidaRestClient.usuario.id ) {
			
			nombre = partidaRestClient.usuario.nombre;
			
		} else {
			
			nombre = partidaRestClient.oponente.nombre;
		}
		
		
		partidaFinalizada(nombre);
	} 
}

function controlTurno(){
	
	var turno = partidaRestClient.getTurno();
	if(partidaRestClient.oponente !== undefined) {
		if(turno == partidaRestClient.idUsuario) {
			
			addTurnoUsuario();
			
		} else {
			addTurnoOponente();
		}
	}
		
	}


function controlSetTablero() {
	
	setTablero(partidaRestClient.getTablero());
	
	$(".ficha.usuario").click(controlSeleccionar);
}

function controlSeleccionar() {

	var ficha = $(this);

	var casilla = $(this).parent();

	var filaOrigen = $(casilla).parent().index();
	var columnaOrigen = $(casilla).index();
	
	seleccionar(casilla);
	
	$(".casilla:not(:has(img))").click( function() {

		var filaDestino = $(this).parent().index();
		var columnaDestino = $(this).index();
		
		var movimiento = partidaRestClient.makeMovimiento(partidaRestClient.idPartida, partidaRestClient.idUsuario, filaOrigen, filaDestino, columnaOrigen, columnaDestino);
				
		if(movimiento !== undefined) {
			
			controlSetTablero();
			mover(casilla);

			
		} else {
			mover(casilla);
			$('#incorrecto').modal('show');
		}
		
	});
}


