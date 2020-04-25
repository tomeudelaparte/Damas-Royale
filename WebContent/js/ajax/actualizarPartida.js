var start = false;
var id;

var partida;

var usuario = [];
var oponente = [];

var puntuacionUsuario = [];
var puntuacionOponente = [];

function getPartida(idPartida, idUsuario) {

	var url = 'http://localhost:8080/Damas-Royale/Rest/getPartida/'

	$.ajax({
		url : url + idPartida,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {

			setJugadores(idUsuario, result);
		}

	});

	if (start == false) {
		start = true;
		id = idPartida;
		setInterval("getPartida(id)", 2000);
	}
}

function setJugadores(idUsuario, result) {

	if (idUsuario == result.idUsuario_A && idUsuario != result.idUsuario_B) {

		usuario = getUsuario(result.idUsuario_A);
		puntuacionUsuario = getPuntuacionUsuario(result.idUsuario_A);

		if (result.idUsuario_B > 0) {

			oponente = getUsuario(result.idUsuario_B);
			puntuacionOponente = getPuntuacionUsuario(result.idUsuario_B);

		}

	} else if (idUsuario == result.idUsuario_B
			&& idUsuario != result.idUsuario_A) {

		if (result.idUsuario_B > 0) {

			usuario = getUsuario(result.idUsuario_B);
			puntuacionUsuario = getPuntuacionUsuario(result.idUsuario_B);

			usuarioConectado(usuario["0"]["nombre"]);
		}

		oponente = getUsuario(result.idUsuario_A);
		puntuacionOponente = getPuntuacionUsuario(result.idUsuario_A);

	}

	usuarioConectado(oponente["0"]["nombre"]);

}

function getUsuario(idUsuario) {

	var resultado = [];

	var url = 'http://localhost:8080/Damas-Royale/Rest/getUsuario/'

	$.ajax({
		url : url + idUsuario,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {

			resultado.push(result);
		}
	});

	return resultado;
}

function getPuntuacionUsuario(idUsuario) {

	var resultado = [];

	var url = 'http://localhost:8080/Damas-Royale/Rest/getPuntuacionUsuario/'

	$.ajax({
		url : url + idUsuario,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {

			resultado.push(result);
		}

	});

	return resultado;

}

function usuarioConectado(jugador) {

	$("#chat").append(
			"<p class='text-danger font-weight-bold'> El jugador " + jugador
					+ " se ha unido a la partida.</p>");

	$('#chat').scrollTop($('#chat')[0].scrollHeight);

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

	hora = addZero(date.getHours());
	minutos = addZero(date.getMinutes());

	return "[" + hora + ":" + minutos + "]";
}

function addZero(n) {
	return n < 10 ? '0' + n : n;
}
