<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Crear cuenta - Damas Royale</title>
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
	<%
		String error = (String) request.getAttribute("error");
	%>
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow mb-5">
		<a class="navbar-brand p-4"> <img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	</nav>
	<div class="container d-flex h-100 pt-5">
		<div class="row align-self-center w-100">
			<div class="col-6 mx-auto">
				<div class="jumbotron shadow bg-light border">
					<h3 class="mb-5 text-dark">Crear tu cuenta de Damas Royale</h3>
					<form action="Register" method="post">
					 <div class="form-group">
					    <label>Nombre de usuario</label>
					    <input type="text" class="form-control" required="required" name="nombre" placeholder="32 carácteres máx.">
					  </div>
					  <div class="form-group">
					    <label>Correo electrónico</label>
					    <input type="email" class="form-control" required="required" name="email" placeholder="64 carácteres máx.">
					  </div>
					  <div class="form-group">
					    <label>Contraseña</label>
					    <input type="password" class="form-control"  required="required" name="contrasenya" placeholder="64 carácteres máx.">
					  </div>
						<%
							if (error != null) {
								error = "<p class='text-danger text-center font-weight-bold'>NOMBRE O CORREO ELECTRÓNICO EXISTENTE.</p>";
								out.append(error);
							}
						%>
						<a href="Login" class="text-decoration-none">¿Quiéres iniciar sesión en Damas Royale?</a>
						<button type="submit" class="btn btn-secondary d-flex ml-auto ">Crear cuenta</button>
					</form>
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