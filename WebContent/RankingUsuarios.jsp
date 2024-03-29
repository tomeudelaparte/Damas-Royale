<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="com.damasroyale.modelo.pojo.Rank" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<%
	// Usuario de la sesi�n
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	
	// Ranking de usuarios
	ArrayList<Rank> ranking = (ArrayList<Rank>) request.getAttribute("ranking");
	
	// Index contador de posiciones
	int index = 1;
%>
<head>
<title>Ranking - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/rankingUsuarios.css">
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
					<a class="dropdown-item" href="Login"><i class="fa fa-sign-out"></i> Cerrar sesi�n</a>
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
	<div class="container bg-light text-dark border-left border-right pt-5 pb-5 shadow mh-100">
		<h2 class="text-center font-weight-bold">RANKING <i class="fa fa-line-chart" style="font-size:36px"></i></h2>
		<h5 class="text-center font-weight-bold">TOP 10 USUARIOS DE DAMAS ROYALE</h5>
		<div class="row mt-5 pl-5 pb-2 mx-auto">
			<table class="table text-dark">
			  <thead>
			    <tr>
			      <th class="text-center" scope="col">POSICI�N</th>
			      <th scope="col">JUGADOR</th>
			      <th scope="col">PUNTUACI�N</th>
			    </tr>
			  </thead>
			  <tbody>
			    <%
			    // Por cada usuario del ranking
			    for(Rank rank : ranking) {%>
			   <tr>
			      <td class="text-center text-dark font-weight-bold" scope="row"><%=index++ %></td>
			      <td><a href="Ficha?id=<%=rank.getId() %>"><img class="rounded-circle float-left mr-2 img-thumbnail shadow-sm img-usuario-60" src="media/<%=rank.getImagen() %>" width="60"></a> <a href="Ficha?id=<%=rank.getId() %>" class="text-dark nav-link p-0"><h5 class="mt-3"><%=rank.getNombre() %></h5></a></td>
			      <td class="text-dark font-weight-bold"><%=rank.getPuntuacion() %> PTS</td>
			    </tr>
			    <%} %>
			  </tbody>
			</table>
		</div>
	</div>	
	<footer id="sticky-footer"
		class=" p-3 bg-light text-dark border-top shadow-lg">
		<div class="container text-center">
			<small>Copyright &copy; 2020 Damas Royale by Tomeu de la
				Parte</small>
		</div>
	</footer>
</body>
</html>