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
<link rel="stylesheet" href="css/General.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/plugins/DataTablesPartida.js"></script>
</head>
<body class="bg-light font-weight-bold">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow">
		<a class="navbar-brand p-4" href="Jugar">
		<img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a href="Ficha?id=<%=usuario.getId()%>"><img class="img-thumbnail mx-auto d-block" src="media/<%= usuario.getImagen() %>" width="50"></a>
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
	      <a class="nav-link text-dark font-weight-bold mr-5" href="Jugar">JUGAR <i class="fa fa-database"></i></a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link text-dark font-weight-bold" href="Ranking">RANKING <i class="fa fa-line-chart"></i></a>
	    </li>
	  </ul>
	</nav>
	<div class="container mt-2 mh-100">
		<div class="row pt-5 pb-5">
				<div class="col">
				<div class="row ml-1 bg-light border shadow p-4 rounded text-dark">
				<div class="col">
				<table class="display">
				  <thead>
				    <tr>
				      <th>SALA</th>
				      <th>ANFITRIÓN</th>
				      <th>JUGADOR</th>
				      <th></th>
				    </tr>
				  </thead>
				  <h2 class="text-dark m-0 font-weight-bold">LISTA DE PARTIDAS</h2>
				  <p class="text-dark m-0 font-weight-normal">Selecciona <b>UNIRSE</b> para unirte a una partida o <b>CREAR PARTIDA</b> para crear una nueva partida.</p>
				  <a href="CrearPartida" class="text-dark"><button type="button" class="btn btn-secondary float-right font-weight-bold">CREAR PARTIDA</button></a>
				  <tbody>
				<% for(Partida partida : partidas) { 
				
					boolean jugador02 = false;
				%>
					
					<tr>
					<td class="font-weight-bold"><%=index++%></td>
					
					<% for (Usuario listaUsuarios : usuarios) {
					
						 if(partida.getIdUsuario_A() == listaUsuarios.getId()) { %>
				    	  		<td class="font-weight-bold">
					    	  		<a href="Ficha?id=<%=listaUsuarios.getId() %>"><img class="img-thumbnail float-left mr-2 shadow-sm" src="media/<%=listaUsuarios.getImagen() %>" width="50"></a>
					    	  		<a class="nav-link text-dark font-weight-bold" href="Ficha?id=<%=listaUsuarios.getId()%>"><h5 class="mt-1"><%=listaUsuarios.getNombre() %></h5></a>
				    	  		</td>
				    	  <%}
				    	  
						 if(partida.getIdUsuario_B() == listaUsuarios.getId()) { %>
			    	  		<td class="font-weight-bold">
				    	  		<a href="Ficha?id=<%=listaUsuarios.getId() %>"><img class="img-thumbnail float-left mr-2 shadow-sm" src="media/<%=listaUsuarios.getImagen() %>" width="50"></a>
				    	  		<a class="nav-link text-dark font-weight-bold" href="Ficha?id=<%=listaUsuarios.getId()%>"><h5 class="mt-1"><%=listaUsuarios.getNombre() %></h5></a>
			    	  		</td>
			    	  	<%
			    	  
			    	 	 jugador02 = true;
						 
					}}
			    	
					
					if(jugador02==false) {%>
					
					<td class="text-dark font-weight-bold">No hay jugador.</td>
					<td><a href="Partida?id=<%=partida.getId() %>" class="text-dark text-center"><button type="button" class="btn btn-secondary font-weight-bold">UNIRSE</button></a></td>					
					</tr>
									
				<%} else {%>
					
					<td><button type="button" class="btn btn-secondary disabled font-weight-bold"  style="cursor:default">UNIRSE</button></td>
					</tr>
					
					
					<%}}%>
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