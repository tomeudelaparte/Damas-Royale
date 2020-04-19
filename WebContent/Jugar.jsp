<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@page import="com.damasroyale.modelo.pojo.Partida"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	ArrayList<Partida> partidas = (ArrayList<Partida>) request.getAttribute("partidas");
	ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
	
	int index = 1;
%>
<title>Jugar - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/plugins/DataTablesPartida.js"></script>
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
		<div class="row pt-5 pb-5">
				<div class="col">
				<h2 class="mb-4">Lista de partidas</h2>
				<div class="row ml-1 bg-light border shadow p-4">
				<div class="col">
				<table class="row-border">
				  <thead>
				    <tr>
				      <th>SALA</th>
				      <th>JUGADOR 1</th>
				      <th>JUGADOR 2</th>
				      <th></th>
				    </tr>
				  </thead>
				  <tbody>
				<% for(Partida partida : partidas) { %>
					
					<tr>
					<td class="font-weight-bold"><%=index++%></td>
					
					<% for (Usuario listaUsuarios : usuarios) {
					
					 if(partida.getIdUsuario_A() == listaUsuarios.getId()) { %>
			    	  		<td class="font-weight-bold"><a href="Ficha?id=<%=listaUsuarios.getId() %>"><img class="img-thumbnail float-left mr-2 shadow-sm" src="media/<%=listaUsuarios.getImagen() %>" width="50"></a>
			    	  		<a class="nav-link text-dark font-weight-bold" href="Ficha?id=<%=listaUsuarios.getId()%>"><h5 class="mt-1"><%=listaUsuarios.getNombre() %></h5></a></td>
			    	  <%}}%>
			    	  
			    	  <td class="font-weight-bold"><a class="nav-link text-dark font-weight-bold" href="Ficha?id=1">2</a></td>
					<td><a href="Partida?id=<%=partida.getId() %>" class="text-dark text-center"><button type="button" class="btn btn-secondary">UNIRSE</button></a></td>
					</tr>
					
					
					<%}%>
					</tbody>
					</table>
				</div>
				</div>
				</div>
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