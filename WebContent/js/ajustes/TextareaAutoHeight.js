$(document).ready(function() {
	
  function ResizeTextArea() {
    ClientHeight = window.innerHeight
    $("#chat").height(ClientHeight - ($("#chat")[0].clientHeight - $("#chat")[0].clientHeight + 320));
  }

  $(window).resize(function() {
    ResizeTextArea();
  });
  
  ResizeTextArea()

});