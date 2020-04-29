var jugador = false;

var damasRestClient;

function crearPartida(idPartida, idUsuario) {

	damasRestClient = new DamasRestClient(idPartida, idUsuario);
		
	controlSetTablero();
	
	setInterval("checkPartida()", 2000);

}

function empezarPartida() {
	
	if (jugador === false && damasRestClient.oponente !== undefined) {
				
		jugadorConectado(damasRestClient.oponente, damasRestClient.puntuacionOponente);
				
		jugador = true;
	}

}

function checkPartida() {

	damasRestClient.getPartida();
	damasRestClient.getJugadores();
	
	empezarPartida();
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


