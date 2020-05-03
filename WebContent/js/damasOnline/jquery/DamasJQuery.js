function setTablero(tablero) {

	var filas = $(".fila");

	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaUsuario.png'>";
	var usuarioReina = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaReinaUsuario.png'>";
	
	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaOponente.png'>";
	var oponenteReina = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaReinaOponente.png'>";

	
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
				
				if (tablero[i][j] === 13) {

					$(filas[i]).children().eq(j).html(usuarioReina);
				}
				
				if (tablero[i][j] === 23) {

					$(filas[i]).children().eq(j).html(oponenteReina);
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

function partidaFinalizada(resultado, usuario, oponente) {
	
	var nombre;
	
	if(resultado.ganador == usuario.id ) {
		
		nombre = usuario.nombre;
		
	} else {
		
		nombre = oponente.nombre;
	}
	
	$("#partidaFinalizada").find('.modal-body').html("<p>El usuario "+nombre+" ha ganado la partida.</p>");
	
	$("#partidaFinalizada").modal('show');
	
}

function jugadorConectado(usuario, puntuacion) {

	$("#chat").append("<p class='text-danger font-weight-bold'> El usuario " + usuario.nombre + " se ha unido a la partida.</p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);

	$('#oponente')
			.html(
					"<div class='col-5'>"
							+ "<img class='img-fluid mx-auto d-block border' src='media/"
							+ usuario.imagen
							+ "' width='150'>"
							+ "</div>"
							+ "<div class='col-7 mt-3'><a href='Ficha?id="
							+ usuario.id
							+ "' class='text-dark nav-link p-0' target='_blank'>"
							+ "<h1 class='text-center'>" + usuario.nombre
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
