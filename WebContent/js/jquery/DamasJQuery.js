class DamasJQuery {

	setTablero(tablero) {
		
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
		$(".ficha.usuario").parent().css("boxshadow", "inset 0px 0px 5px 3px darkred");
//		$(".usuario").click(mover);
	}
	
	jugadorConectado(jugador) {

		$("#chat").append(
				"<p class='text-danger font-weight-bold'> El jugador " + jugador
						+ " se ha unido a la partida.</p>");

		$('#chat').scrollTop($('#chat')[0].scrollHeight);

	}

	recibirMensaje(jugador, mensaje) {

		var date = new Date();
		var hora = formatear(date);

		$("#chat").append(
				"<p><span class='text-dark font-weight-bold'>" + hora + " "
						+ jugador + ":</span>" + mensaje + "</p>");

		$('#chat').scrollTop($('#chat')[0].scrollHeight);
	}

	formatear(date) {

		hora = addZero(date.getHours());
		minutos = addZero(date.getMinutes());

		return "[" + hora + ":" + minutos + "]";
	}

	addZero(n) {
		return n < 10 ? '0' + n : n;
	}
}