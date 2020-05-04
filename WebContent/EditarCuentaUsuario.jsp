<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario" %>
<%@ page import="org.joda.time.DateTime" %>
<!DOCTYPE html>
<html>
<head>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	DateTime fecha = new DateTime(usuario.getRegistro());
	
	String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
%>
<title>Editar cuenta - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="icon" href="media/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/ajustes/InputFileDisplayName.js"></script>
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
	<div class="container bg-light rounded border pt-5 pb-5 mt-5 shadow mh-100">
	<div class="row ml-5 mb-5">
		<h3>EDITAR PERFIL</h3>
		<button type="button" class="btn btn-danger d-flex ml-auto mr-5" data-toggle="modal" data-target="#eliminarCuenta">Eliminar cuenta</button>
		  <div class="modal fade" id="eliminarCuenta">
		    <div class="modal-dialog modal-dialog-centered modal-lg">
		      <div class="modal-content">
		        <div class="modal-header">
		          <h4 class="modal-title">¿Estás seguro de eliminar tu cuenta de Damas Royale?</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
		        <div class="modal-body">
		        <p class="mb-4">Al pulsar el botón <span class="badge badge-danger">Eliminar cuenta</span>, se eliminará, de forma automática, la cuenta y todos los datos asociados. <br><br>· Al realizar esta acción, no habrá vuelta atrás y no podrás acceder a Damas Royale mediante esta cuenta.<br>· Si quieres volver a acceder a Damas Royale, deberás crear una cuenta nueva.</p>
		        <div class="modal-footer mt-3">
		        <a class="text-decoration-none mr-auto" href="Eliminar"><button type="button" class="btn btn-danger pl-5 pr-5">Eliminar cuenta</button></a>
		        
		          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        </div>
		      </div>
		    </div>
		  </div>   
      </div>
		</div>
		<div class="row ml-5">
			<div class="col-4">
			<div class="row">
				<img class="img-thumbnail shadow-sm rounded img-usuario-250" src="media/<%=usuario.getImagen() %>" width="250">
			</div>
			<div class="row mt-3">
				<h3><%=usuario.getNombre() %></h3>
			</div>
			<div class="row">
				<p>Te unistes el <%=fecha.getDayOfMonth() %> de <%= mes[fecha.getMonthOfYear()-1] %> de <%= fecha.getYear()%></p>
			</div>
			</div>
			<form class="ml-5" action="Editar" method="post" enctype="multipart/form-data">
			 <div class="form-group font-weight-bold text-dark">
			    <label>Nombre de usuario</label>
			    <input type="text" class="form-control" required="required" name="nombre" value="<%= usuario.getNombre()%>">
			  </div>
			  <div class="form-group font-weight-bold text-dark">
			    <label>Contraseña</label>
			    <input type="password" class="form-control"  required="required" name="contrasenya" value="<%= usuario.getContrasenya()%>">
			  </div>
				<div class="form-group mb-5 font-weight-bold text-dark">
				<label>Imagen de perfil</label>
					<div class="custom-file">
					  <input type="file" class="custom-file-input" name="imagen" id="file">
					  <label class="custom-file-label" >Seleccionar imagen</label>
					</div>
				</div>
				<a href="Ficha?id=<%=usuario.getId() %>" class="text-decoration-none float-left"><button type="button" class="btn btn-secondary d-flex ">Cancelar</button></a>
				<button type="submit" class="btn btn-secondary d-flex ml-auto ">Guardar cambios</button>
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