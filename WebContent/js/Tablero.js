$(document).ready(function() {

	$(".ficha").click(mover);

	function mover() {

		var ficha = $(this);
		var casilla = $(this).parent();

		$(casilla).addClass("selected");

		$(".casilla").css("cursor", "pointer");

		$(".casilla").click(function() {
			$(casilla).removeClass("selected");

			if ($(this).children().length == 0) {
				$(this).append(ficha);
				 $(".ficha").unbind('click').bind('click',mover);

			}
		});
	};
});