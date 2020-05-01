function setTablero(tablero) {

	var filas = $(".fila");

	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaUsuario.png'>";
	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaOponente.png'>";
	
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

			
		}
	}

	$(".ficha").css("cursor", "default");
	$(".ficha.usuario").css("cursor", "pointer");
}

function seleccionar(casilla) {
	$(".casilla").removeClass("selected");
	$(".casilla").removeClass("move");
	$(".casilla").unbind();
	
	$(casilla).addClass("selected");
	$(".casilla.bg-dark:not(:has(img))").addClass("move");
	$(".casilla.bg-dark:not(:has(img))").css("cursor", "pointer");
}

function mover(casilla) {
	$(casilla).removeClass("selected");
	$(casilla).empty();
	$(".casilla").removeClass("move");
	$(".casilla").unbind();
}

function jugadorConectado(jugador, puntuacion) {

	$("#chat").append("<p class='text-danger font-weight-bold'> El usuario " + jugador.nombre + " se ha unido a la partida.</p>");

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

	$("#chat").append(
			"<p><span class='text-dark font-weight-bold'>" + hora + " "
					+ jugador + ":</span>" + mensaje + "</p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);
}

function formatear(date) {

	var hora = addZero(date.getHours());
	var minutos = addZero(date.getMinutes());

	return "[" + hora + ":" + minutos + "]";
}

function addZero(n) {
	return n < 10 ? '0' + n : n;
}
