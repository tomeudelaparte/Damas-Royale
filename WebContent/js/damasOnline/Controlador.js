var clienteRest;

var jugador = false;

function crearPartida(idPartida, idUsuario) {

	clienteRest = new ClienteRest(idPartida, idUsuario);
		
	controlSetTablero();
	
	$("#abandonarPartida").click(function() {
		
		clienteRest.abandonarPartida();
		
		$("#abandonar").modal("hide");
	});
	
	setInterval("checkPartida()", 300);

}

function controlJugadorConectado() {
	
	if (jugador === false && clienteRest.oponente !== undefined) {
		
		if(clienteRest.partida.idUsuario_B != clienteRest.idUsuario) {
			
			jugadorConectado(clienteRest.oponente, clienteRest.puntuacionOponente);
			
			jugador = true;
		}

	}

}

function checkPartida() {

	clienteRest.getPartida();
	clienteRest.getJugadores();
	controlEstadoPartida();
	controlJugadorConectado();
	controlSetTablero();
}

function controlEstadoPartida(){
	
	var estado = clienteRest.getEstadoPartida();
		
	if(estado === true) {
		var resultado = clienteRest.getResultadoPartida();
		
		partidaFinalizada(resultado, clienteRest.usuario, clienteRest.oponente);
	} 
}

function controlSetTablero() {
	
	setTablero(clienteRest.getTablero());
	
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
		
		var movimiento = clienteRest.makeMovimiento(clienteRest.idPartida, clienteRest.idUsuario, filaOrigen, filaDestino, columnaOrigen, columnaDestino);
				
		if(movimiento !== undefined) {
			
			$(this).append(ficha);
			mover(casilla);

			
		} else {
			mover(casilla);
			$('#incorrecto').modal('show');
		}
		
	});
}


