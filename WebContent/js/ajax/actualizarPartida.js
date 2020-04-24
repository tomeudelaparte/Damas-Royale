var start = false;
var id;

var partida;

var jugador01;
var jugador02;

var puntuacion01 = 0;
var puntuacion02 = 0;

function getPartida(idPartida) {

	var url = 'http://localhost:8080/Damas-Royale/Rest/getPartida/'

	$.ajax({
		url : url + idPartida,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {

			jugador01 = getUsuario(result.idUsuario_A);
			puntuacion01 = getPuntuacionUsuario(result.idUsuario_A);
			
			jugador02 = getUsuario(result.idUsuario_B);
			puntuacion02 = getPuntuacionUsuario(result.idUsuario_B);

		}
	});

	if (start == false) {
		start = true;
		id = idPartida;
		setInterval("getPartida(id)", 2000);
	}
}


function getUsuario(idUsuario) {
	
	var url = 'http://localhost:8080/Damas-Royale/Rest/getUsuario/'
	
	if(idUsuario == null) {
		idUsuario = 0;
	}
	
	$.ajax({
		url : url + idUsuario,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {
			return result;
		}
	});	
}

function getPuntuacionUsuario(idUsuario) {
	
	var url = 'http://localhost:8080/Damas-Royale/Rest/getPuntuacionUsuario/'
	
	if(idUsuario == null) {
		idUsuario = 0;
	}
	
	$.ajax({
		url : url + idUsuario,
		contentType : "application/json",
		dataType : 'json',
		success : function(result) {
			return result;
		}
	});	
}
