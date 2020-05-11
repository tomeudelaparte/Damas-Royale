<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="com.damasroyale.modelo.pojo.Partida" %>
<%@ page import="com.damasroyale.modelo.pojo.Resultado" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	Usuario jugador = (Usuario) request.getAttribute("jugador");
	ArrayList<Partida> partidas = (ArrayList<Partida>) request.getAttribute("partidas");
	ArrayList<Resultado> resultados = (ArrayList<Resultado>) request.getAttribute("resultados");
	ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
	
	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy"); 
  	SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
	
	int index = 1;
%>
<title><%=jugador.getNombre() %> - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/plugins/DataTablesFichaUsuarioHistorial.js"></script>
</head>
<body class="bg-light font-weight-bold text-dark">
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
	<div class="container mt-5 mh-100">
	<div class="row">
		<nav class="navbar navbar-expand-sm bg-light rounded-top justify-content-center navbar-dark border-top border-right border-left">
		  <ul class="navbar-nav ">
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&tab=info">INFORMACIÓN</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-secondary font-weight-bold mr-3" href="Ficha?id=<%=jugador.getId()%>&tab=stats">ESTADÍSTICAS</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link text-dark font-weight-bold" href="Ficha?id=<%=jugador.getId()%>&tab=history">HISTORIAL</a>
		    </li>
		  </ul>
		</nav>
		</div>
		<div class="row bg-light border rounded-bottom rounded-right shadow pt-5 pl-5 pr-5 pb-4">
			<div class="col">
				<h4 class="mb-4 text-dark">HISTORIAL DE PARTIDAS</h4>
				<table class="row-border">
			  <thead>
			    <tr>
			      <th class="text-center" scope="col">#</th>
			      <th>FECHA</th>
			      <th>HORA</th>
			      <th>ANFITRIÓN</th>
			      <th>JUGADOR</th>
			      <th>REPETICIÓN</th>
			    </tr>
			  </thead>
			  <tbody>
			  <% for(Resultado resultado : resultados) {
				  
				  for(Partida partida : partidas) {
					  
					  if(partida.getId() == resultado.getIdPartida()) {
						  
						  if(resultado.getGanador() == jugador.getId() && resultado.getPerdedor() != jugador.getId()) {
						  
			    %>
			   <tr class="bg-success text-light">

			   <%} else if(resultado.getGanador() != jugador.getId() && resultado.getPerdedor() == jugador.getId()) {%>
			   
			   <tr class="bg-danger text-light">
			   
			   <%} else if(resultado.isTablas()) {%>
			   
			    <tr class="bg-warning text-light">
			    
			    <%} %>
			      <td class="text-center font-weight-bold"><%=index++%></td>

			      <td class="font-weight-bold"><%=fecha.format(resultado.getFecha_hora()) %></td>
			      <td class="font-weight-bold"><%=hora.format(resultado.getFecha_hora()) %></td>
			      
			      <% for(Usuario listaUsuarios : usuarios) { 
			    	  
			    	  if(partida.getIdUsuario_A() == listaUsuarios.getId()) { %>
			    	  		<td class="font-weight-bold"><a href="Ficha?id=<%=listaUsuarios.getId() %>"><img class="img-thumbnail float-left mr-2 shadow-sm rounded img-usuario-40" src="media/<%=listaUsuarios.getImagen() %>" width="40"></a><a class="nav-link text-light font-weight-bold" href="Ficha?id=<%=listaUsuarios.getId()%>"><%=listaUsuarios.getNombre()%></a></td>
			    	  <%}}
			      
			      for(Usuario listaUsuarios : usuarios) {  
			    	 if(partida.getIdUsuario_B() == listaUsuarios.getId()) { %>
			    	  		<td class="font-weight-bold"><a href="Ficha?id=<%=listaUsuarios.getId() %>"><img class="img-thumbnail float-left mr-2 shadow-sm rounded img-usuario-40" src="media/<%=listaUsuarios.getImagen() %>" width="40"></a><a class="nav-link text-light font-weight-bold" href="Ficha?id=<%=listaUsuarios.getId()%>"><%=listaUsuarios.getNombre()%></a></td>
			    	  <%}}%>

			      <td class="font-weight-bold text-center"><a href="Repeticion?id=<%=partida.getId() %>&usuario=<%=jugador.getId()%>" class="text-light"><i class="fa fa-desktop"></i></a></td>
			    </tr>
			   <% }}}%>	  
			  </tbody>
			</table>
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