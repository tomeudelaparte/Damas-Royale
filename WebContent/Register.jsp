<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Crear cuenta - Damas Royale</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/LoginAndRegister.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="js/InputFile.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" href="media/favicon.png">
</head>
<body class="bg-secondary">
	<%
		String error = (String) request.getAttribute("error");
	%>
	<div class="container d-flex h-100">
		<div class="row align-self-center w-100">
			<div class="col-6 mx-auto">
				<div class="jumbotron shadow">
					<h3 class="mb-3">Crear tu cuenta de Damas Royale</h3>
					<form action="Register" method="post">
					 <div class="form-group">
					    <label>Nombre de usuario</label>
					    <input type="text" class="form-control" required="required" name="nombre">
					  </div>
					  <div class="form-group">
					    <label>Correo electrónico</label>
					    <input type="email" class="form-control" required="required" name="email">
					  </div>
					  <div class="form-group">
					    <label>Contraseña</label>
					    <input type="password" class="form-control"  required="required" name="contrasenya">
					  </div>
						<%
							if (error != null) {
								error = "<p class='text-danger text-center'>*ERROR AL REGISTRARSE*</p>";
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
</body>
</html>