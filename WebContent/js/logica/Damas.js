$(document).ready(function() {

	var turno = false;
	
	var ficha01 = "<img class='img-fluid mx-auto mt-2 ficha' src='media/pieza01.png'>";
	var ficha02 = "<img class='img-fluid mx-auto mt-2 ficha' src='media/pieza02.png'>";

	var tablero = [
		[0,2,0,2,0,2,0,2],
		[2,0,2,0,2,0,2,0],
		[0,2,0,2,0,2,0,2],
		[0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0],
		[1,0,1,0,1,0,1,0],
		[0,1,0,1,0,1,0,1],
		[1,0,1,0,1,0,1,0]];
	
	var fila = $(".fila");
	
	load()

	function load() {

		for (i = 0; i < tablero.length; i++) {
			
			for(j = 0; j < 8; j++) {
								
				if(tablero[i][j] !== 0) {
					
					if(tablero[i][j] === 2) {
						$(fila[i]).children().eq(j).append(ficha02);
					}
					
					if(tablero[i][j] === 1) {
						$(fila[i]).children().eq(j).append(ficha01);

					}
				}
			}
			
			$(".ficha").css("cursor", "pointer");
			$(".ficha").parent().css("boxshadow", "inset 0px 0px 5px 3px darkred");
			$(".ficha").click(mover);
			
		}
		

		function mover() {

			var ficha = $(this);
			
			var casilla = $(this).parent();
			
			var filaAnterior = $(casilla).parent().index();
			var casillaAnterior = $(casilla).index();
		
			$(casilla).addClass("selected");
			$(".casilla").addClass("move");
			
			$(".casilla").css("cursor", "pointer");
			
			$(".casilla").click(function() {
				if ($(this).children().length === 0 && $(this).attr("id")) {
					
					var filaSiguiente = $(this).parent().index();
					var casillaSiguiente = $(this).index();
					
					tablero[filaAnterior][casillaAnterior] = 0;
					tablero[filaSiguiente][casillaSiguiente] = 1;
					
					$(this).append(ficha);
					
					console.log(tablero);
					$(casilla).removeClass("selected");
					$(".casilla").removeClass("move");
					$(".casilla").unbind();
				}
			});

		}
	}
})