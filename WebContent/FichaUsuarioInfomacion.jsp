<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="org.joda.time.DateTime" %>
<!DOCTYPE html>
<html>
<head>
<%
	// Usuario de la sesión
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	// Usuario
	Usuario jugador = (Usuario) request.getAttribute("jugador");
	
	// Puntuación del usuario
	int puntuacion = (int) request.getAttribute("puntuacion");
	
	// Partidas jugadas del usuario
	int partidasJugadas = (int) request.getAttribute("jugadas");
	
	// Partidas ganadas del usuario
	int partidasGanadas = (int) request.getAttribute("ganadas");
	
	// Partidas perdidas del usuario
	int partidasPerdidas = (int) request.getAttribute("perdidas");
	
	// Partidas tablas del usuario
	int partidasTablas = (int) request.getAttribute("tablas");
	
	// Fecha de registro
	DateTime fecha = new DateTime(usuario.getRegistro());
	
	// Meses
	String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
%>
<title><%=jugador.getNombre() %> - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow">
		<a class="navbar-brand p-4" href="Jugar">
		<img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a href="Ficha?id=<%=usuario.getId()%>"><img class="img-thumbnail mx-auto d-block rounded img-usuario-50" src="media/<%= usuario.getImagen() %>" width="50"></a>
			</li>
			<li class="nav-item dropdown bg-light">
			<a class="nav-link navbar-brand text-dark font-weight-bold mr-5" href="#" id="navbardrop" data-toggle="dropdown"> 
				<span class="align-middle"><%=usuario.getNombre()%> <i class="fa fa-caret-down"></i></span>
			</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="Ficha?id=<%=usuario.getId()%>"><i class="fa fa-user"></i> Ver mi perfil </a>
					<a class="dropdown-item" href="Editar"><i class="fa fa-gear"></i> Editar mi perfil</a> 
					<a class="dropdown-item" href="Login"><i class="fa fa-sign-out"></i> Cerrar sesión</a>
				</div>
			</li>
		</ul>
	</nav>
	<nav class="navbar navbar-expand-sm bg-light justify-content-center navbar-dark shadow">
	  <ul class="navbar-nav ">
	    <li class="nav-item">
	      <a class="nav-link text-dark font-weight-bold mr-5" href="Jugar">JUGAR ONLINE <i class="fa fa-users"></i></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link text-dark font-weight-bold" href="Ranking">RANKING <i class="fa fa-line-chart"></i></a>
	    </li>
	  </ul>
	</nav>
	<div class="container mt-5 mh-100 text-dark">
	<div class="row">
		<nav class="navbar navbar-expand-sm bg-light rounded-top justify-content-center navbar-dark border-top border-right border-left">
		  <ul class="navbar-nav ">
		    <li class="nav-item">
		      <a class="nav-link text-dark font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&tab=info">INFORMACIÓN</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&tab=stats">ESTADÍSTICAS</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold" href="Ficha?id=<%=jugador.getId()%>&tab=history">HISTORIAL</a>
		    </li>
		  </ul>
		</nav>
		</div>
		<div class="row bg-light border rounded-bottom rounded-right shadow pt-5 pb-5">
			<div class="col-4">
				<div class="row">
					<img class="img-thumbnail mx-auto d-block shadow-sm rounded img-usuario-250" src="media/<%=jugador.getImagen() %>" width="250">
				</div>
				<div class="row ml-5 mt-3">
					<h3><%=jugador.getNombre() %></h3>
				</div>
				<div class="row ml-5">
					<p>Se unió el <%=fecha.getDayOfMonth() %> de <%= mes[fecha.getMonthOfYear()-1] %> de <%= fecha.getYear()%></p>
				</div>
			</div>
			<div class="col-8">
				<h4 class="mb-5">INFORMACIÓN DEL USUARIO</h4>
				<div class="row mb-5 ml-1">
					<div class="col-5 shadow-sm mr-5">
						<i class="fa fa-certificate float-left pr-4" style="font-size: 50px;"></i>
						<h5>PUNTUACIÓN</h5>
						<h5><%=puntuacion%> PTS</h5>
					</div>
					<div class="col-5 shadow-sm">
						<i class="fa fa-dot-circle-o float-left pr-4" style="font-size: 50px;"></i>
						<h5>PARTIDAS JUGADAS</h5>
						<h5><%=partidasJugadas%></h5>
					</div>
				</div>
				<div class="row ml-1 mb-5">
					<div class="col-5 shadow-sm mr-5">
						<i class="fa fa-check-circle float-left pr-4" style="font-size: 50px;"></i>
						<h5>PARTIDAS GANADAS</h5>
						<h5><%=partidasGanadas%></h5>
					</div>
					<div class="col-5 shadow-sm">
						<i class="fa fa-times-circle float-left pr-4" style="font-size: 50px;"></i>
						<h5>PARTIDAS PERDIDAS</h5>
						<h5><%=partidasPerdidas%></h5>
					</div>
				</div>
				<div class="row ml-1">
					<div class="col-5 shadow-sm mr-5">
						<i class="fa fa-circle-o float-left pr-4" style="font-size: 50px;"></i>
						<h5>TABLAS</h5>
						<h5><%=partidasTablas%></h5>
					</div>
				</div>
			</div>
			
			<% 
			// Si el usuario es igual al usuario de la sesióm.
			if(jugador.getId()==usuario.getId()){ %>
			<a href="Editar" class="text-decoration-none d-flex ml-auto mr-5 pr-4"><button type="button" class="btn btn-secondary ">Editar mi perfil</button></a>
			<%} %>
			
		</div>
	</div>
	<footer
		class="fixed-bottom p-4 bg-light text-dark border-top shadow-lg">
		<div class="container text-center">
			<small>Copyright &copy; 2020 Damas Royale by Tomeu de la Parte</small>
		</div>
	</footer>
</body>
</html>