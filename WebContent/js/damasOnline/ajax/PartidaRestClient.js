class ClienteRest {

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
		
		var url = 'http://localhost:8080/Damas-Royale/Rest/getPartida/';
		
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
				
				tmp  = data;
			}
		});
		
		this.partida = tmp;
	}
 
	getUsuario(idUsuario) {
		
		var tmp;
		
		var url = 'http://localhost:8080/Damas-Royale/Rest/getUsuario/';

		$.ajax({
			url : url + idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
				
				tmp =  data;
			}
		});
		
		return tmp;
	}

	getPuntuacionUsuario(idUsuario) {
	
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getPuntuacionUsuario/';
	
		$.ajax({
			url : url + idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
	
				tmp = data;
			}
		});
		
		return tmp;
	}
	
	getTablero() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getTablero/';
			
		$.ajax({
			url : url + this.idPartida + "/"+ this.idUsuario,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
	
				tmp = data;
			}
		});
				
		return tmp;
	}
	
	getEstadoPartida() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getEstadoPartida/';
			
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
	
				tmp = data;
			}
		});
				
		return tmp;
	}
	
	getResultadoPartida() {
		
		var tmp;
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getResultadoPartida/';
			
		$.ajax({
			url : url + this.idPartida,
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
	
				tmp = data;
			}
		});
				
		return tmp;
	}
	
	abandonarPartida() {
			
		var url = 'http://localhost:8080/Damas-Royale/Rest/abandonarPartida/';
			
		$.ajax({
			url : url + this.idPartida +"/"+ this.idUsuario +"/"+ this.oponente.id,
			async: false,
			contentType : "application/json",
			dataType : 'json',

		});	
	}
	
	makeMovimiento(idPartida, idJugador, filaOrigen, filaDestino, columnaOrigen, columnaDestino) {
		
		var tmp;
	
		var url = "http://localhost:8080/Damas-Royale/Rest/makeMovimiento/"+idPartida+"/"+idJugador+"/"+filaOrigen+"/"+filaDestino+"/"+columnaOrigen+"/"+columnaDestino;
			
		$.ajax({
			url : url,
			async: false,
			contentType : "application/json",
			dataType : 'json',
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