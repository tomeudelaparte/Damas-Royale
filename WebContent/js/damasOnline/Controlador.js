var jugador = false;

var clienteRest;

function crearPartida(idPartida, idUsuario) {

	clienteRest = new ClienteRest(idPartida, idUsuario);
		
	controlSetTablero();
	
	$("#abandonarPartida").click(function() {
		
		clienteRest.abandonarPartida();
		
		$("#abandonar").modal("hide");
	});
	
	setInterval("checkPartida()", 300);

}

function empezarPartida() {
	
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
	controlSetTablero();
	controlEstadoPartida();
	
	empezarPartida();
}

function controlEstadoPartida(){
	
	var estado = clienteRest.getEstadoPartida();
		
	if(estado === true) {
		var resultado = clienteRest.getResultadoPartida();
		
		var nombreUsuario;
		
		if(resultado.ganador == clienteRest.usuario.id ) {
			nombreUsuario = clienteRest.usuario.nombre;
		} else {
			nombreUsuario = clienteRest.oponente.nombre;

		}
		
		$("#partidaFinalizada").find('.modal-body').html("<p>El usuario "+nombreUsuario+" ha ganado la partida.</p>");
		
		$("#partidaFinalizada").modal('show');
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


