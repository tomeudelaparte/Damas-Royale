<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Usuario - Damas Royale</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" href="media/favicon.png">
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom"">
	  <a class="navbar-brand p-4" href="Inicio"><img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	  <ul class="navbar-nav ml-auto">
		<li class="nav-item bg-secondary">
		  <img class="img-fluid mx-auto d-block" src="media/default.jpg" width="50">
		</li>
		 <li class="nav-item dropdown bg-light">
	      <a class="nav-link navbar-brand text-dark font-weight-bold" href="#" id="navbardrop" data-toggle="dropdown">
	        <span class="align-middle">MAYORHKIIN <i class="fa fa-caret-down"></i></span>
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="#"><i class="fa fa-user"></i> Ver mi perfil </a>
	        <a class="dropdown-item" href="#"><i class="fa fa-gear"></i> Editar mi perfil</a>
	        <a class="dropdown-item" href="#"><i class="fa fa-sign-out"></i> Cerra sesión</a>
	      </div>
	    </li>
	  </ul>
	</nav>
	<div class="container border mt-5 p-5">
		<div class="row">
			<div class="col-4">
			 <img class="img-fluid mx-auto d-block" src="media/default.jpg" width="250">
			</div>
			<div class="col-8">
				<div class="row mb-5">
					<h2>MAYORHKIIN</h2>
				</div>
				<div class="row">
					<div class="col-6">
						<h4>PUNTUACIÓN</h4>
						<p>0</p>
					</div>
					<div class="col-5">
						<h4>PARTIDAS JUGADAS</h4>
						<p>0</p>
					</div>
				</div>
								<div class="row">
					<div class="col-6">
						<h4>PARTIDAS GANADAS</h4>
						<p>0</p>
					</div>
					<div class="col-5">
						<h4>PARTIDAS PERDIDAS</h4>
						<p>0</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <footer class="fixed-bottom p-4 bg-light text-dark border-top">
	   <div class="container text-center">
	     <small>Copyright &copy; 2020 Damas Royale by Tomeu de la Parte</small>
	   </div>
	 </footer>
</body>
</html>