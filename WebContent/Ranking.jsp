<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="com.damasroyale.modelo.pojo.Rank" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	ArrayList<Rank> ranking = (ArrayList<Rank>) request.getAttribute("ranking");
	
	int index = 1;
%>
<head>
<title>Ranking - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom">
		<a class="navbar-brand p-4" href="Jugar">
		<img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item bg-secondary">
				<img class="img-fluid mx-auto d-block" src="media/<%= usuario.getImagen() %>" width="50">
			</li>
			<li class="nav-item dropdown bg-light">
			<a class="nav-link navbar-brand text-dark font-weight-bold mr-5" href="#" id="navbardrop" data-toggle="dropdown"> 
				<span class="align-middle"><%=usuario.getNombre().toUpperCase() %> <i class="fa fa-caret-down"></i></span>
			</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="Jugador?id=<%=usuario.getId()%>"><i class="fa fa-user"></i> Ver mi perfil </a>
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
	<div class="container bg-light border-left border-right pt-5 pb-5 shadow mh-100">
		<h1 class="text-center">RANKING <i class="fa fa-line-chart" style="font-size:36px"></i></h1>
		<h5 class="text-center">Top 10 jugadores de Damas Royale</h5>
		<div class="row mt-5 pl-5 pb-5 mx-auto">
			<table class="table">
			  <thead>
			    <tr>
			      <th class="text-center" scope="col">POSICIÓN</th>
			      <th scope="col">JUGADOR</th>
			      <th scope="col">PUNTUACIÓN</th>
			    </tr>
			  </thead>
			  <tbody>
			    <% for(Rank rank : ranking) {%>
			   <tr>
			      <th class="text-center" scope="row"><%=index++ %></th>
			      <td><img class="rounded-circle float-left mr-2" src="media/<%=rank.getImagen() %>" width="50"> <a href="Jugador?id=<%=rank.getId() %>" class="text-dark"><h5 class="mt-2"><%=rank.getNombre() %></h5></a></td>
			      <td class="text-dark font-weight-bold"><%=rank.getPuntuacion() %> PTS</td>
			    </tr>
			    <%} %>
			  </tbody>
			</table>
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