<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.damasroyale.modelo.pojo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.damasroyale.modelo.pojo.Partida"%>
<!DOCTYPE html>
<html>
<head>
<%
	int sala = (int) request.getAttribute("sala");
	Partida partida = (Partida) request.getAttribute("partida");
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	int usuarioPuntuacion = (int) request.getAttribute("usuarioPuntuacion");
	
	Usuario oponente = new Usuario();
	int oponentePuntuacion = 0;
	
	if (request.getAttribute("oponente") != null) {
		oponente = (Usuario) request.getAttribute("oponente");
		oponentePuntuacion = (int) request.getAttribute("oponentePuntuacion");
	}
	

%>
<title>Sala <%=sala%> - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<link rel="stylesheet" href="css/salaPartidaDamas.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/ajustes/TextareaAutoHeight.js"></script>
<script src="js/damasOnline/jquery/DamasJQuery.js"></script>
<script src="js/damasOnline/ajax/ClienteRest.js"></script>
<script src="js/damasOnline/Controlador.js"></script>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow-sm">
		<a class="navbar-brand p-4"> <img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	</nav>
	<div class="container-fluid bg-light pt-3 mb-3">
		<div class="row">
			<div class="col-3 border shadow ml-4 rounded">
			
				<%
				
				if (partida.getIdUsuario_A() == oponente.getId()) {
					
				%>
				<h2 class="p-2 pt-3 text-dark">Oponente <span class="font-weight-light">(Anfitrión)</span></h2>
				
				<% } else { %>
				
				<h2 class="p-2 pt-3 text-dark">Oponente</h2>
				
				<%} %>
				<hr>
				<div id="oponente" class="row mx-auto shadow m-3 mb-5 p-2 rounded">
				
				<% if(oponente.getId() == null) { %>
				
					<h3 class="text-center m-5 pt-2 pb-2">Esperando un jugador...</h3>

				<% } else { %>
					<div class="col-5">
						<img id="opImagen" class="img-fluid mx-auto d-block border" src="media/<%=oponente.getImagen() %>" width="150">
					</div>
					<div class="col-7 mt-3">	
						<a href="Ficha?id=<%=oponente.getId() %>" class="text-dark nav-link p-0" target="_blank"><h1 class="text-center"><%=oponente.getNombre() %></h1></a>
						<p class="text-center"><%=oponentePuntuacion %> PTS</p>
					</div>
				<%}%>
				
				</div>
				
				<h1 class="text-dark font-weight-bold mx-auto text-center">VS</h1>
				<%
				
				if (partida.getIdUsuario_A() == usuario.getId()) {
					
				%>
				<h2 class="p-2 text-dark">Tú <span class="font-weight-light">(Anfitrión)</span></h2>
				
				<% } else { %>
				
				<h2 class="p-2 text-dark">Tú</h2>
				
				<%} %>
				
				<hr>
				<div class="row mx-auto shadow m-3 p-2 rounded mb-5">
					<div class="col-5">
						<img class="img-fluid mx-auto d-block border" src="media/<%=usuario.getImagen() %>" width="150">
					</div>
					<div class="col-7 mt-3">	
						<a href="Ficha?id=<%=usuario.getId() %>" class="text-dark nav-link p-0" target="_blank"><h1 class="text-center"><%=usuario.getNombre() %></a></h1>
						<p class="text-center"><%=usuarioPuntuacion%> PTS</p>
					</div>
				</div>
				<div class="row mx-auto pt-5">
					<button type="button" class="btn btn-danger font-weight-bold ml-2 mt-4" data-toggle="modal" data-target="#abandonar">ABANDONAR PARTIDA</button>
					 <div class="modal fade" id="abandonar" role="dialog">
					    <div class="modal-dialog modal-dialog-centered">
					      <div class="modal-content">
					        <div class="modal-header">
					          <h4 class="modal-title">¿Quiéres abandonar la partida?</h4>
					        </div>
					        <div class="modal-body">
					          <p>Al pulsar el botón de <span class="badge badge-danger">Abandonar partida</span>, la partida terminará y perderás de forma automática. Si no deseas realizar esta acción, pulse el botón de <span class="badge badge-secondary">Cancelar</span> o pulse fuera de esta ventana. </p>
					          <p>Si deseas una alternativa, puedes solicitar un empate con el oponente mediante el botón <span class="badge badge-secondary">SOLICITAR TABLAS</span> y esperar su respuesta.</p>
					        </div>
					        <div class="modal-footer">
					         <button id="abandonarPartida" type="button" class="btn btn-danger mr-auto">Abandonar partida</button>
					         <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					        </div>
					      </div>
					    </div>
					  </div>
					<button type="button" class="btn btn-secondary font-weight-bold ml-auto mr-2 mt-4" data-toggle="modal" data-target="#tablas">SOLICITAR TABLAS</button>
					<div class="modal fade" id="tablas" role="dialog">
					    <div class="modal-dialog modal-dialog-centered">
					      <div class="modal-content">
					        <div class="modal-header">
					          <h4 class="modal-title">¿Quiéres solicitar tablas al oponente?</h4>
					        </div>
					        <div class="modal-body">
					          <p>Al pulsar el botón de <span class="badge badge-danger">Solicitar tablas</span>, solicitarás al oponente terminar la partida en empate. Si no deseas realizar esta acción, pulse el botón de <span class="badge badge-secondary">Cancelar</span> o pulse fuera de esta ventana. </p>
					          <p>Si deseas terminar la partida sin solicitar tablas, puedes pulsar el botón <span class="badge badge-danger">ABANDONAR PARTIDA</span> con la condición de perder y otorgar la victoria al oponente.</p>
					        </div>
					        <div class="modal-footer">
					         <button type="button" class="btn btn-danger mr-auto">Solicitar tablas</button>
					         <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					        </div>
					      </div>
					    </div>
					  </div>
					  <div class="modal fade" id="partidaFinalizada" role="dialog">
					    <div class="modal-dialog modal-dialog-centered">
					      <div class="modal-content">
					        <div class="modal-header">
					          <h4 class="modal-title">La partida ha finalizado.</h4>
					        </div>
					        <div class="modal-body">
					        	
					        </div>
					        <div class="modal-footer">
					         <a href="Jugar" class="mx-auto"><button type="button" class="btn btn-secondary">Volver</button></a>
					        </div>
					      </div>
					    </div>
					  </div>
				</div>
			</div>
			<div class="col-5 border shadow mx-auto m-0 tablero rounded">
				<div class="row fila">
					<div class="col bg-light casilla round-top-left"></div>
					<div id="29" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="30" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="31" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="32"class="col bg-dark casilla round-top-right"></div>
				</div>
				<div class="row fila">
					<div id="25" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="26" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="27" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="28" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row fila">
					<div class="col bg-light casilla"></div>
					<div id="21" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="22" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="23" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="24" class="col bg-dark casilla"></div>
				</div>
				<div class="row fila">
					<div id="17" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="18" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="19" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="20" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row fila">
					<div class="col bg-light casilla"></div>
					<div id="13" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="14" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="15" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="16" class="col bg-dark casilla"></div>
				</div>
				<div class="row fila">
					<div id="9" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="10" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="11" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="12" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row fila">
					<div class="col bg-light casilla"></div>
					<div id="5" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="6" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="7" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="8" class="col bg-dark casilla"></div>
				</div>
				<div class="row fila">
					<div id="1" class="col bg-dark casilla round-bottom-left"></div>
					<div class="col bg-light casilla"></div>
					<div id="2" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="3" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div id="4" class="col bg-dark casilla"></div>
					<div class="col bg-light casilla round-bottom-right"></div>
				</div>
			</div>
			<div class="col-3 border shadow mr-4 rounded">
				<h2 class="p-2 text-dark">Chat</h2>
				<hr>
				<form class="mx-auto">
					<div class="form-group">
					  <div id="chat" class="mt-2 mb-2 border-0 bg-light" style="overflow-y:scroll"></div>
					</div>
					<div class="form-group">
					  <input id="message" class="form-control bg-white pl-2 text-bold font-weight-bold" name="mensaje" type="text" placeholder=" Envía un mensaje a tu oponente.">
					</div>
				</form>
			</div>
		</div>
		<div class="modal" id="incorrecto">
		    <div class="modal-dialog modal-dialog-centered modal-lg">
		      <div class="modal-content">
		        <div class="modal-header rounded">
		          <h4 class="modal-title font-weight-bold text-danger">Movimiento incorrecto</h4>
		          <button type="button" class="close text-danger font-weight-bold" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
		        </div>
		      </div>
		    </div>
		  </div>   
      </div>
	</div>
	<footer id="sticky-footer"
		class=" p-3 bg-light text-dark border-top shadow-lg">
		<div class="container text-center">
			<small>Copyright &copy; 2020 Damas Royale by Tomeu de la
				Parte</small>
		</div>
	</footer>
	<script>
		crearPartida(<%=partida.getId()%>,<%=usuario.getId()%>);
	</script>
</body>
</html>