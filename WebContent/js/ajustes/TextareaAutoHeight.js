$(document).ready(function() {
	
  // Ejecuta la función por primera vez.
  ResizeTextArea();
	
  // Cada vez que el tamaño de la ventana cambie, se ejecuta la función.
  $(window).resize(function() {
    ResizeTextArea();
  });
	
// Función que hace que el chat llegue hasta bajo de la ventana según la altura. 
  function ResizeTextArea() {
	  
	  // Obtiene la altura de la ventana.
	  var windowHeight = window.innerHeight;
	  
	  // Aplica la altura modificada al chat (AlturaVentana - 320 px)
    $("#chat").height(windowHeight - 320);
  }
	
});