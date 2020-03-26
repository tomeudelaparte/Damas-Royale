$(document).ready(function() {
	
  function ResizeTextArea() {
    ClientHeight = window.innerHeight
    $("textarea").height(ClientHeight - ($("form")[0].clientHeight - $("textarea")[0].clientHeight + 320));
  }

  $(window).resize(function() {
    ResizeTextArea();
  });
  
  ResizeTextArea()

});