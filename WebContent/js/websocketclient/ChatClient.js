var websocket = new WebSocket("ws://localhost:8080/Damas_Royale/room/01");

websocket.onmessage = function(evt) { 
	
	var chat = document.getElementById("chat");
	
	chat.innerHTML += evt.data + "\n";
	chat.scrollTop = chat.scrollHeight;
};

function send_message() {
	
	var date = new Date();
	var hora = "["+date.getHours() + ":" + date.getMinutes()+"]";
    websocket.send(hora +" "+ document.getElementById("message").value);
}

$(document).ready(function() {
	  $(window).keydown(function(event){
	    if(event.keyCode == 13) {
	      event.preventDefault();
	      send_message();
	    }
	  });
	});