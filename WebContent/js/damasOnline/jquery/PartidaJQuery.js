
// Añade las fichas del tablero de la partida.
function setTablero(tablero) {

	// Filas del tablero 
	var filas = $(".fila");

	// Fichas de usuario
	var usuario = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaUsuario.png'>";
	var usuarioReina = "<img class='img-fluid mx-auto mt-2 ficha usuario' src='media/damas/damaReinaUsuario.png'>";

	// Fichas de oponente
	var oponente = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaOponente.png'>";
	var oponenteReina = "<img class='img-fluid mx-auto mt-2 ficha' src='media/damas/damaReinaOponente.png'>";

	// Añade las fichas
	for (var i = 0; i < 8; i++) {

		for (var j = 0; j < 8; j++) {

			// Si la posición es 0, eliminar lo que haya dentro.
			if (tablero[i][j] === 0) {

				$(filas[i]).children().eq(j).empty();
			}

			// Si la posición es 1, añade una ficha de usuario.
			if (tablero[i][j] === 1) {

				$(filas[i]).children().eq(j).html(usuario);
			}

			// Si la posición es 2, añade una ficha de oponente.
			if (tablero[i][j] === 2) {

				$(filas[i]).children().eq(j).html(oponente);
			}

			// Si la posición es 13, añade una ficha reina de usuario.
			if (tablero[i][j] === 13) {

				$(filas[i]).children().eq(j).html(usuarioReina);
			}

			// Si la posición es 23, añade una ficha reina de oponente.
			if (tablero[i][j] === 23) {

				$(filas[i]).children().eq(j).html(oponenteReina);
			}

		}
	}
	
	// Añade un cursor sobre las fichas.
	$(".ficha").css("cursor", "default");
	$(".ficha.usuario").css("cursor", "pointer");
}

// Marca la casilla seleccionada y marca la casilla del ratón.
function seleccionar(casilla) {
	
	// Elimina la clase selected de la casillas.
	$(".casilla").removeClass("selected");
	
	// Elimina la clase move de la casillas.
	$(".casilla").removeClass("move");
	
	// Elimina toda función linkeada a la casillas.
	$(".casilla").unbind();

	// Añade la clase selected a la casilla.
	$(casilla).addClass("selected");
	
	// Añade la clase move a las casillas con ficha.
	$(".casilla.bg-dark:not(:has(img))").addClass("move");
	
	// Añade la clase pointer a las casillas con ficha.
	$(".casilla.bg-dark:not(:has(img))").css("cursor", "pointer");
}

// Elimina classes y eventos
function mover(casilla) {
	
	// Elimina la clase selected de la casilla.
	$(casilla).removeClass("selected");
	
	// Elimina la clase move de las casillas.
	$(".casilla").removeClass("move");
	
	// Elimina toda función linkeada a la casillas.
	$(".casilla").unbind();
	
	$(".casilla.bg-dark:not(:has(img))").css("cursor", "default");
}

// Muestra un mensaje de que el usuario ha ganado.
function partidaFinalizadaUsuario() {

	$('#partidaFinalizada').modal({backdrop: 'static', keyboard: false});
	
	$("#partidaFinalizada").find('.modal-header').html(
			"<h1 class='text-dark mx-auto'>HAS GANADO</h1>");

	$("#partidaFinalizada").modal('show');

}

// Muestra un mensaje de que el usuario ha perdido.
function partidaFinalizadaOponente() {
	
	$('#partidaFinalizada').modal({backdrop: 'static', keyboard: false});
	
	$("#partidaFinalizada").find('.modal-header').html(
			"<h1 class='text-dark mx-auto'>HAS PERDIDO</h1>");

	$("#partidaFinalizada").modal('show');

}

// Muestra un mensaje de que la partida ha finalizado en tablas.
function partidaTablas(usuario, oponente) {
	
	$('#partidaTablas').modal({backdrop: 'static', keyboard: false});
	
	$("#partidaTablas").modal('show');

}

// Añade el turno al usuario y se lo quita al oponente
function addTurnoUsuario() {
	$('#oponente').removeClass("border-warning");
	$('#usuario').addClass("border-warning");
}

// Añade el turno al oponente  y se lo quita al usuario
function addTurnoOponente() {
	$('#usuario').removeClass("border-warning");
	$('#oponente').addClass("border-warning");
}

// Muestra un mensaje de que el oponente ha solicitado tablas.
function tablasSolicitadas() {

	$("#chat").append("<p class='text-secondary font-weight-bold shadow border text-center pt-3'>Tu oponente ha solicitado tablas.<br>"
			+"<button id='aceptarTablas' type='button' class='btn btn-secondary btn-sm mt-3 mb-3 font-weight-bold'>ACEPTAR TABLAS</button></p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);

}

// Muestra el mensaje de que te has conectado.
function jugadorConectadoAlt() {
	$("#chat")
			.append(
					"<p class='text-danger font-weight-bold'> Te has unido a la partida.</p>");
}

// Muestra que un usuario se ha conectado.
function jugadorConectado(usuario, puntuacion) {

	// Muestra el mensaje por el chat
	$("#chat").append(
			"<p class='text-danger font-weight-bold'>" + usuario.nombre
					+ " se ha unido a la partida.</p>");

	// Mueve el chat hacia abajo
	$('#chat').scrollTop($('#chat')[0].scrollHeight);

	// Carga la información del oponente.
	$('#oponente')
			.html(
					"<div class='col-5'>"
							+ "<img class='img-fluid mx-auto d-block border rounded img-usuario-150' src='media/"
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

// Añade los mensajes recientes.
function setMensajes(usuario, oponente, mensajes) {
	
	// Obtiene la cantidad de mensajes del chat.
	var chat = $(".mensaje").length;

	
	for (var i = chat; i < mensajes.length; i++) {
		
		// Si el mensaje es del usuario
		if (mensajes[i].idUsuario === usuario.id) {

			// Muestra mensaje del propio usuario
			recibirMensaje(usuario, mensajes[i]);

		} else {

			// Muestra mensaje del oponente
			recibirMensaje(oponente, mensajes[i]);

		}
	}
}

// Añade un mensaje al chat.
function recibirMensaje(jugador, mensaje) {
		
	// Obtiene la hora del mensaje.
	var hora = mensaje.hora.substring(0, (mensaje.hora.length-3));

	// Añade el mensaje
	$("#chat").append(
			"<p class='mensaje'><span class='text-dark font-weight-bold'> ["
					+ hora + "] " + jugador.nombre + ": </span>"
					+ mensaje.texto + "</p>");

	// Mueve el chat abajo.
	$('#chat').scrollTop($('#chat')[0].scrollHeight);
}