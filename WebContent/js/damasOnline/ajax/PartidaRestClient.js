
// Classe REST cliente que guarda la informació básica de la partida 
// y contiene métodos para hacer peticiones al servicio REST.
class PartidaRestClient {

	// Constructor
	constructor(idPartida, idUsuario) {
		
		// Identificador de la partida.
		this.idPartida = idPartida;
		
		// Identificador del usuario.
		this.idUsuario = idUsuario;
		
		// Información básica de la partida.
		this.partida;
		
		// Información del usuario
		this.usuario;
		
		// Información del oponente.
		this.oponente;
		
		// Puntuación del usuario.
		this.puntuacionUsuario;
		
		// Puntuación del oponente.
		this.puntuacionOponente;
	}
	

	// Obtiene la partida..
	getPartida() {
		
		var tmp;
		
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getPartida/';
		
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
				
				tmp  = data;
			}
		});
		
		// Guarda la información.
		this.partida = tmp;
	}
 
	// Obtiene un usuario.
	getUsuario(idUsuario) {
		
		var tmp;
		
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getUsuario/';

		// Petición asíncrona de AJAX
		$.ajax({
			url : url + idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
				
				tmp =  data;
			}
		});
		
		// Devuelve un usuario.
		return tmp;
	}
	

	// Obtiene la puntuación de un usuario.
	getPuntuacionUsuario(idUsuario) {
	
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getPuntuacionUsuario/';
	
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
		
		// Devuelve la puntuación.
		return tmp;
	}
	
	// Obtiene el tablero de la partida.
	getTablero() {
		
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getTablero/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida + "/"+ this.idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
			
		// Devuelve el tablero.
		return tmp;
	}
	
	
	// Obtiene el turno de la partida.
	getTurno() {
		
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getTurno/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
			
		// Devuelve el turno.
		return tmp;
	}
	
	
	// Obtiene el estado de la partida.
	getEstado() {
		
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getEstado/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
			
		// Devuelve el estado.
		return tmp;
	}
	
	// Obtiene el ganador de la partida.
	getGanador() {
		
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getGanador/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
		
		// Devuelve el ganador.
		return tmp;
	}
	
	// Obtiene las tablas de la partida.
	comprobarTablas() {
		
		var tmp;
	
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/comprobarTablas/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url :url + this.idPartida +"/"+ this.idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
			
		// Devuelve las tablas de la partida.
		return tmp;
	}
	
	// Solicita tablas al oponente.
	solicitarTablas() {
		
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/tablas/';
		
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida +"/"+ this.idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },

		});	
	}

	// Abandona la partida.
	abandonarPartida() {
			
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/abandonar/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida +"/"+ this.idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },

		});	
	}
	
	// Envia un mensaje para el chat.
	sendMensaje(texto) {
		
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/sendMensaje/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url + this.idPartida +"/"+ this.idUsuario + "/" + texto,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },

		});	
	}
	
	// Obtiene todos los mensajes de la partida.
	getMensajes() {
		
		var tmp;
		
		// Dirección REST
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getMensajes/';
			
		// Petición asíncrona de AJAX
		$.ajax({
			url :url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
				
		// Devuelve los mensajes.
		return tmp;
	}
	
	// Realiza el movimiento del usuario.
	makeMovimiento(idPartida, idJugador, filaOrigen, filaDestino, columnaOrigen, columnaDestino) {
		
		var tmp;
		
		// Dirección REST
		var url = "http://localhost:8080/Damas-Royale/PartidaRest/makeMovimiento/"+idPartida+"/"+idJugador+"/"+filaOrigen+"/"+filaDestino+"/"+columnaOrigen+"/"+columnaDestino;
			
		// Petición asíncrona de AJAX
		$.ajax({
			url : url,
			async: false,
			contentType : "application/json",
			dataType : 'json',
		    headers: {
	              'Access-Control-Allow-Origin': '*'
	          },
			success : function(data) {
	
				tmp = data;
			}
		});
		
		// Devuelve el movimiento.
		return tmp;
	}
	
	// Guarda los participantes de la partida.
	getJugadores() {
		
		// Si el usuario es el anfitrión
		if (this.idUsuario === this.partida.idUsuario_A) {
			
			// Guarda el usuario
			this.usuario = this.getUsuario(this.partida.idUsuario_A);
			
			// Guarda la puntuación del usuario
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_A);
	
			// Si hay un usuario oponente
			if (this.partida.idUsuario_B > 0) {
	
				// Guarda el oponente.
				this.oponente = this.getUsuario(this.partida.idUsuario_B);
				
				// Guarda la puntuación del oponente.
				this.puntuacionOponente = this.getPuntuacionUsuario(this.partida.idUsuario_B);
			}
	
		// Si el usuario es el oponente (segundo jugador) .
		} else if (this.idUsuario === this.partida.idUsuario_B) {
	
			// Guarda el usuario
			this.usuario = this.getUsuario(this.partida.idUsuario_B);
			
			// Guarda la puntuación del usuario
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_B);

			// Guarda el oponente.
			this.oponente = this.getUsuario(this.partida.idUsuario_A);
			
			// Guarda la puntuación del oponente.
			this.puntuacionOponente = this.getPuntuacionUsuario(this.partida.idUsuario_A);
		}
	}

}