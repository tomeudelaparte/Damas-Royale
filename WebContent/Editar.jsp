<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="com.damasroyale.modelo.pojo.Estadistica" %>
<%@ page import="org.joda.time.DateTime" %>
<!DOCTYPE html>
<html>
<head>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	DateTime fecha = new DateTime(usuario.getRegistro());
	
	String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
%>
<title>Editar - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/InputFile.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
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
	<div class="container bg-light border-left border-right pt-5 pb-5 mt-5 shadow mh-100">
		<div class="row ml-5 mb-5">
			<h2>Editar perfil</h2>
		</div>
		<div class="row ml-5">
			<div class="col-4">
			<div class="row">
				<img class="img-fluid border" src="media/<%=usuario.getImagen() %>" width="250">
			</div>
			<div class="row mt-3">
				<h3><%=usuario.getNombre() %></h3>
			</div>
			<div class="row">
				<p>Te unistes el <%=fecha.getDayOfMonth() %> de <%= mes[fecha.getMonthOfYear()-1] %> de <%= fecha.getYear()%></p>
			</div>
			</div>
			<form class="ml-5" action="Editar" method="post" enctype="multipart/form-data">
			 <div class="form-group">
			    <label>Nombre de usuario</label>
			    <input type="text" class="form-control" required="required" name="nombre" value="<%= usuario.getNombre()%>">
			  </div>
			  <div class="form-group">
			    <label>Contraseña</label>
			    <input type="password" class="form-control"  required="required" name="contrasenya" value="<%= usuario.getContrasenya()%>">
			  </div>
				<div class="form-group mb-5">
				<label>Imagen de perfil</label>
					<div class="custom-file">
					  <input type="file" class="custom-file-input" name="imagen" id="file">
					  <label class="custom-file-label" >Seleccionar imagen</label>
					</div>
				</div>
				<a href="Jugador?id=<%=usuario.getId() %>" class="text-decoration-none float-left"><button type="button" class="btn btn-danger d-flex ">Cancelar</button></a>
				<button type="submit" class="btn btn-primary d-flex ml-auto ">Guardar cambios</button>
			</form>
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