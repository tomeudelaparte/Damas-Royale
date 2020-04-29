function setTablero(tablero) {

	var filas = $(".fila");

	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/pieza01.png'>";
	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/pieza02.png'>";

	for (var i = 0; i < 8; i++) {

		for (var j = 0; j < 8; j++) {

			if (tablero[i][j] !== 0) {

				if (tablero[i][j] === 1) {

					$(filas[i]).children().eq(j).append(usuario);
				}

				if (tablero[i][j] === 2) {

					$(filas[i]).children().eq(j).append(oponente);
				}
			}
		}
	}

	$(".ficha").css("cursor", "default");
	$(".ficha.usuario").css("cursor", "pointer");
}

function seleccionar(casilla) {
	$(casilla).addClass("selected");
	$(".casilla").addClass("move");

	$(".casilla").css("cursor", "pointer");
}

function mover(casilla) {
	$(casilla).removeClass("selected");
	$(".casilla").removeClass("move");
	$(".casilla").unbind();
}

function jugadorConectado(jugador, puntuacion) {

	$("#chat").append("<p class='text-danger font-weight-bold'> El jugador " + jugador.nombre + " se ha unido a la partida.</p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);

	$('#oponente')
			.html(
					"<div class='col-5'>"
							+ "<img class='img-fluid mx-auto d-block border' src='media/"
							+ jugador.imagen
							+ "' width='150'>"
							+ "</div>"
							+ "<div class='col-7 mt-3'><a href='Ficha?id="
							+ jugador.id
							+ "' class='text-dark nav-link p-0' target='_blank'>"
							+ "<h1 class='text-center'>" + jugador.nombre
							+ "</h1></a><p class='text-center'>" + puntuacion
							+ " PTS</p>" + "</div>");

}

function recibirMensaje(jugador, mensaje) {

	var date = new Date();
	var hora = formatear(date);

	$("#chat").append("<p><span class='text-dark font-weight-bold'>" + hora + " " + jugador + ":</span>" + mensaje + "</p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);
}

function formatear(date) {

	hora = addZero(date.getHours());
	minutos = addZero(date.getMinutes());

	return "[" + hora + ":" + minutos + "]";
}

function addZero(n) {
	return n < 10 ? '0' + n : n;
}
