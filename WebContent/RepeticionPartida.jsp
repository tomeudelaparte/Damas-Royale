<%@page import="com.damasroyale.modelo.pojo.Movimiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.damasroyale.modelo.pojo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.damasroyale.modelo.pojo.Partida"%>
<!DOCTYPE html>
<html>
<head>
<%
	Partida partida = (Partida) request.getAttribute("partida");

	ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) request.getAttribute("movimientos");
	
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	int usuarioPuntuacion = (int) request.getAttribute("usuarioPuntuacion");

	Usuario oponente = (Usuario) request.getAttribute("oponente");
	int  oponentePuntuacion = (int) request.getAttribute("oponentePuntuacion");

%>
<title>Repetición - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/salaPartidaDamas.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/repeticionPartida/repeticionPartida.js"></script>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow-sm">
		<a class="navbar-brand p-4" href="Jugar" target="_blank">
		<img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
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

					<div class="col-5">
						<img id="opImagen" class="img-fluid mx-auto d-block border rounded img-usuario-150" src="media/<%=oponente.getImagen() %>" width="150">
					</div>
					<div class="col-7 mt-3">	
						<a href="Ficha?id=<%=oponente.getId() %>" class="text-dark nav-link p-0" target="_blank"><h1 class="text-center"><%=oponente.getNombre() %></h1></a>
						<p class="text-center"><%=oponentePuntuacion %> PTS</p>
					</div>
				</div>
				
				<h1 class="text-dark font-weight-bold mx-auto text-center">VS</h1>
				<%
				
				if (partida.getIdUsuario_A() == usuario.getId()) {
					
				%>
				<h2 class="p-2 text-dark">Usuario <span class="font-weight-light">(Anfitrión)</span></h2>
				
				<% } else { %>
				
				<h2 class="p-2 text-dark">Usuario</h2>
				
				<%} %>
				
				<hr>
				<div id="usuario" class="row mx-auto shadow m-3 p-2 rounded mb-5">
					<div class="col-5">
						<img class="img-fluid mx-auto d-block border rounded img-usuario-150" src="media/<%=usuario.getImagen() %>" width="150">
					</div>
					<div class="col-7 mt-3">	
						<a href="Ficha?id=<%=usuario.getId() %>" class="text-dark nav-link p-0" target="_blank"><h1 class="text-center"><%=usuario.getNombre() %></h1></a>
						<p class="text-center"><%=usuarioPuntuacion%> PTS</p>
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
				<h1 class="p-2 text-dark">Movimientos <span id="movimientos">0/<%=movimientos.size() %></span></h1>
				<div class="row m-5 border shadow rounded">
					<div class="col">
						<button id="anterior" class="btn btn-light float-left pl-4 pr-4"><i class="fa fa-caret-left" style="font-size:200px"></i></button>
					</div>
					<div class="col">
						<button id="siguiente" class="btn btn-light float-right pl-4 pr-4"><i class="fa fa-caret-right" style="font-size:200px"></i></button>
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
		var movimientosJS = <%=movimientos%>;
		cargarMovimientos(movimientosJS);
	</script>
</body>
</html>