<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="com.damasroyale.modelo.pojo.Stat" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	Usuario jugador = (Usuario) request.getAttribute("jugador");
	ArrayList<Stat> estadistica = (ArrayList<Stat>) request.getAttribute("estadistica");
%>
<title><%=jugador.getNombre() %> - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow">
		<a class="navbar-brand p-4" href="Jugar">
		<img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a href="Ficha?id=<%=usuario.getId()%>"><img class="img-thumbnail mx-auto d-block" src="media/<%= usuario.getImagen() %>" width="50"></a>
			</li>
			<li class="nav-item dropdown bg-light">
			<a class="nav-link navbar-brand text-dark font-weight-bold mr-5" href="#" id="navbardrop" data-toggle="dropdown"> 
				<span class="align-middle"><%=usuario.getNombre().toUpperCase() %> <i class="fa fa-caret-down"></i></span>
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
	      <a class="nav-link text-dark font-weight-bold mr-5" href="Jugar">JUGAR <i class="fa fa-database"></i></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link text-dark font-weight-bold" href="Ranking">RANKING <i class="fa fa-line-chart"></i></a>
	    </li>
	  </ul>
	</nav>
	<div class="container mt-5 mh-100">
	<div class="row">
		<nav class="navbar navbar-expand-sm bg-light justify-content-center navbar-dark border-top border-right border-left">
		  <ul class="navbar-nav ">
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&page=info">INFORMACIÓN</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-dark font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&page=stats">ESTADÍSTICAS</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold" href="Ficha?id=<%=jugador.getId()%>&page=history">HISTORIAL</a>
		    </li>
		  </ul>
		</nav>
		</div>
		<div class="row bg-light border shadow pt-5 pl-5 pr-5 pb-2">
			<div class="col">
				<h2 class="mb-2">Estadísticas del usuario</h2>
				<div class="row mb-3">
					<canvas id="chart"></canvas>
				</div>
				<p class="text-secondary">* La información de las estadísticas se reduce a los últimos 12 meses de actividad del usuario.</p>
			</div>
		</div>
	</div>
	<footer
		class="fixed-bottom p-4 bg-light text-dark border-top shadow-lg">
		<div class="container text-center">
			<small>Copyright &copy; 2020 Damas Royale by Tomeu de la Parte</small>
		</div>
	</footer>
	<script>
	var ctx = document.getElementById('chart');
	ctx.getContext('2d');
	ctx.width  = 20;
	ctx.height = 8;

	var chart = new Chart(ctx, {
	    type: 'line',
	
	    data: {
	        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
	        datasets: [{
	            label: 'Partidas ganadas',
	            backgroundColor: 'rgb(255,255,255,0)',
	            borderColor: 'darkgreen',
	            data: [0, 1, 2, 0, 3, 1, 2, 0, 2, 0, 1, 3]
	        }, 
	        
	        {
	            label: 'Partidas perdidas',
	            backgroundColor: 'rgb(255,255,255,0)',
	            borderColor: 'darkred',
	            data: [1, 3, 2, 1, 1, 2, 4, 2, 3, 2, 1, 0]
	        },
	        
	        {
	            label: 'Tablas',
	            backgroundColor: 'rgb(255,255,255,0)',
	            borderColor: 'rgb(255, 193, 7)',
	            data: [0, 1, 0, 1, 0, 0, 0, 2, 1, 0, 1, 1]
	        }]
	    },
	
	    options: {}
	});
</script>
</body>
</html>