$(document).ready(function() {
	
  function ResizeTextArea() {
	  var windowHeight = window.innerHeight;
    $("#chat").height(windowHeight - ($("#chat")[0].clientHeight - $("#chat")[0].clientHeight + 320));
  }

  $(window).resize(function() {
    ResizeTextArea();
  });
  
  ResizeTextArea()

});