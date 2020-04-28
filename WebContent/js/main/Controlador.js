var start = false;

var jugador = false;

var damasJQuery = new DamasJQuery();

var damasRestClient;

function cargarPartida(idPartida, idUsuario) {

	damasRestClient = new DamasRestClient(idPartida, idUsuario);

	damasRestClient.createPartida();
	damasRestClient.getTablero();
	
	setTablero();
	
	checkPartida();
}

function checkPartida() {

	damasRestClient.getPartida();
	damasRestClient.getJugadores();
	setJugadorConectado();

	if (start === false) {

		start = true;
		setInterval("checkPartida()", 2000);
	}
}

function setTablero() {
	damasJQuery.setTablero(damasRestClient.getTablero());
}

function setJugadorConectado() {
	
	if (jugador === false && damasRestClient.oponente !== undefined) {
				
		damasJQuery.jugadorConectado(damasRestClient.oponente.nombre);
		
		jugador = true;
	}

}
