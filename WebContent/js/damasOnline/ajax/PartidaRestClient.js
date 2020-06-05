class PartidaRestClient {

	constructor(idPartida, idUsuario) {
				
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		
		this.partida;
		
		this.usuario;
		this.oponente;
		
		this.puntuacionUsuario;
		this.puntuacionOponente;
	}
	

	getPartida() {
		
		var tmp;
		
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getPartida/';
		
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
		
		this.partida = tmp;
	}
 
	
	getUsuario(idUsuario) {
		
		var tmp;
		
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getUsuario/';

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
		
		return tmp;
	}
	

	
	getPuntuacionUsuario(idUsuario) {
	
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getPuntuacionUsuario/';
	
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
		
		return tmp;
	}
	
	
	getTablero() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getTablero/';
			
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
				
		return tmp;
	}
	
	getTurno() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getTurno/';
			
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
				
		return tmp;
	}
	
	getEstado() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getEstado/';
			
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
				
		return tmp;
	}
	
	getGanador() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getGanador/';
			
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
				
		return tmp;
	}
	
	comprobarTablas() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/comprobarTablas/';
			
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
				
		return tmp;
	}
	
	solicitarTablas() {
		
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/tablas/';
			
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
	

	abandonarPartida() {
			
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/abandonar/';
			
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
	
	sendMensaje(texto) {
		
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/sendMensaje/';
			
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
	
	getMensajes() {
		
		var tmp;
		
		var url = 'http://localhost:8080/Damas-Royale/PartidaRest/getMensajes/';
			
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
				
		return tmp;
	}
	
	
	makeMovimiento(idPartida, idJugador, filaOrigen, filaDestino, columnaOrigen, columnaDestino) {
		
		var tmp;
	
		var url = "http://localhost:8080/Damas-Royale/PartidaRest/makeMovimiento/"+idPartida+"/"+idJugador+"/"+filaOrigen+"/"+filaDestino+"/"+columnaOrigen+"/"+columnaDestino;
			
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
		
		return tmp;
	}
	
	
	getJugadores() {
		
		if (this.idUsuario === this.partida.idUsuario_A) {
			
			this.usuario = this.getUsuario(this.partida.idUsuario_A);
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_A);
	
			if (this.partida.idUsuario_B > 0) {
	
				this.oponente = this.getUsuario(this.partida.idUsuario_B);
				this.puntuacionOponente = this.getPuntuacionUsuario(this.partida.idUsuario_B);
			}
	
		} else if (this.idUsuario === this.partida.idUsuario_B) {
	
			this.usuario = this.getUsuario(this.partida.idUsuario_B);
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_B);

			this.oponente = this.getUsuario(this.partida.idUsuario_A);
			this.puntuacionOponente = this.getPuntuacionUsuario(this.partida.idUsuario_A);
		}
	}

}