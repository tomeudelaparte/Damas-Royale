const damas = new Damas();

class DamasRestClient {

	constructor(idPartida, idUsuario) {
		this.start = false;
		this.idPartida = idPartida;
		this.idUsuario = idUsuario;
		
		this.partida = [];
		
		this.usuario = [];
		this.oponente = [];
		
		this.puntuacionUsuario = [];
		this.puntuacionOponente = [];
		
		this.createPartida();
		this.checkPartida();
		this.getTablero();
	}
	
	
	checkPartida() {
		
		if (this.start === false) {
			
			this.start = true;
			setInterval(this.getPartida(), 2000);
		}
	}
	
	createPartida() {

		var url = 'http://localhost:8080/Damas-Royale/Rest/createPartida/';
		
		var id = this.idPartida;

		$.ajax({
			url : url + id
		});
	}

	getPartida() {

			var url = 'http://localhost:8080/Damas-Royale/Rest/getPartida/';
			
			var id = this.idPartida;

			$.ajax({
				url : url + id,
				contentType : "application/json",
				dataType : 'json',
				success : function(result) {
					
					this.partida = result;
					if (this.idUsuario === result.idUsuario_A && this.idUsuario !== result.idUsuario_B) {
						
						this.usuario = getUsuario(result.idUsuario_A);
						this.puntuacionUsuario = getPuntuacionUsuario(result.idUsuario_A);
				
						if (result.idUsuario_B > 0) {
				
							this.oponente = getUsuario(result.idUsuario_B);
							this.puntuacionOponente = getPuntuacionUsuario(result.idUsuario_B);
						}
				
					} else if (this.idUsuario === result.idUsuario_B && this.idUsuario !== result.idUsuario_A) {
				
						this.usuario = getUsuario(result.idUsuario_B);
						this.puntuacionUsuario = getPuntuacionUsuario(result.idUsuario_B);

						this.oponente = getUsuario(result.idUsuario_A);
						this.puntuacionOponente = getPuntuacionUsuario(result.idUsuario_A);
					}
				
					console.log(oponente);
					if (this.conectado === false && oponente.nombre !== undefined) {
						classDamas.usuarioConectado(oponente.nombre);
				
						this.conectado = true;
					}
					
				}
			});
		}

	setJugadores(result) {
	

	
	}
 
	getUsuario(idUsuario) {

		var resultado =[];

		var url = 'http://localhost:8080/Damas-Royale/Rest/getUsuario/';

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

	getTablero() {
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getTablero/';
		
		var id = this.idPartida;
	
		$.ajax({
			url : url + id,
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
	
				damas.setTablero(data);
			}
		});
	}

	getPuntuacionUsuario(idUsuario) {
	
		var resultado = [];
	
		var url = 'http://localhost:8080/Damas-Royale/Rest/getPuntuacionUsuario/';
	
		$.ajax({
			url : url + idUsuario,
			contentType : "application/json",
			dataType : 'json',
			success : function(result) {
	
				for (var i = 0; i < result.length; i++) {
					resultado.push(result[i]);
				}
			}
		});
	
		return resultado;
	}

}
