var jugador = false;

var damasRestClient;

function crearPartida(idPartida, idUsuario) {

	damasRestClient = new DamasRestClient(idPartida, idUsuario);
		
	controlSetTablero();
	
	$("#abandonarPartida").click(function() {
		
		damasRestClient.abandonarPartida();
		
		$("#abandonar").modal("hide");
	});
	
	setInterval("checkPartida()", 300);

}

function empezarPartida() {
	
	if (jugador === false && damasRestClient.oponente !== undefined) {
		
		if(damasRestClient.partida.idUsuario_B != damasRestClient.idUsuario) {
			
			jugadorConectado(damasRestClient.oponente, damasRestClient.puntuacionOponente);
			
			jugador = true;
		}

	}

}

function checkPartida() {

	damasRestClient.getPartida();
	damasRestClient.getJugadores();
	controlSetTablero();
	controlEstadoPartida();
	
	empezarPartida();
}

function controlEstadoPartida(){
	
	var estado = damasRestClient.getEstadoPartida();
		
	if(estado === true) {
		var resultado = damasRestClient.getResultadoPartida();
		
		$("#partidaFinalizada").find('.modal-body').html("<p>El usuario"+resultado.ganador+" ha ganado la partida.</p>");
		
		$("#partidaFinalizada").modal('show');
	} 
}

function controlSetTablero() {
	
	setTablero(damasRestClient.getTablero());
	
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
		
		var movimiento = damasRestClient.makeMovimiento(damasRestClient.idPartida, damasRestClient.idUsuario, filaOrigen, filaDestino, columnaOrigen, columnaDestino);
				
		if(movimiento !== undefined) {
			
			$(this).append(ficha);
			mover(casilla);

			
		} else {
			mover(casilla);
			$('#incorrecto').modal('show');
		}
		
	});
}


