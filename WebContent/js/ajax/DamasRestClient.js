class DamasRestClient {

	constructor(idPartida, idUsuario) {
				
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		
		this.partida;
		
		this.usuario;
		this.oponente;
		
		this.puntuacionUsuario;
		this.puntuacionOponente;
	}
	
	createPartida() {

		var url = 'http://localhost:8080/Damas-Royale/Rest/createPartida/';
		
		$.ajax({
			url : url + this.idPartida
		});
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
	
	getJugadores() {
		
		if (this.idUsuario === this.partida.idUsuario_A && this.idUsuario !== this.partida.idUsuario_B) {
			
			this.usuario = this.getUsuario(this.partida.idUsuario_A);
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_A);
	
			if (this.partida.idUsuario_B > 0) {
	
				this.oponente = this.getUsuario(this.partida.idUsuario_B);
				this.puntuacionOponente = this.getPuntuacionUsuario(this.partida.idUsuario_B);
			}
	
		} else if (this.idUsuario === this.partida.idUsuario_B && this.idUsuario !== this.partida.idUsuario_A) {
	
			this.usuario = this.getUsuario(this.partida.idUsuario_B);
			this.puntuacionUsuario = this.getPuntuacionUsuario(this.partida.idUsuario_B);

			this.oponente = this.getUsuario(this.partida.idUsuario_A);
			this.puntuacionOponente = this.getPuntuacionUsuario(this.partidas.idUsuario_A);
		}
	}

}