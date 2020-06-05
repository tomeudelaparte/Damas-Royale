$(document).ready(function() {
	
	// Cambia el nombre por el nombre del archivo introducido.
	$('#file').change(function(e) {
		
		// Obtiene el nombre del archivo introducido.
		var fileName = e.target.files[0].name;
		
		// Cambia el nombre mostrado por el del archivo.
		$('.custom-file-label').html(fileName);
	});
});