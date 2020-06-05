<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Portal - Damas Royale</title>
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
		// Muestra mensaje de error de inicio de sesión.
		String error = (String) request.getAttribute("error");
	
		// Muestra mensaje de registro completado.
		String register = (String) request.getAttribute("register");
		
		// Muestra mensaje de cuenta verificada.
		String activation = (String) request.getAttribute("activation");
		
		// Muestra mensaje de cuenta eliminada.
		String delaccount = (String) request.getAttribute("delaccount");
	%>
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow mb-5">
		<a class="navbar-brand p-4"> <img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	</nav>
	<div class="container d-flex h-100 pt-5">
		<div class="row align-self-center w-100">
			<div class="col-6 mx-auto">
				<div class="jumbotron shadow bg-light border pb-4">
					<h3 class="mb-5 text-dark">Iniciar sesión en Damas Royale</h3>
					<form action="Login" method="post">
						<div class="form-group input-group">
							<input type="email" class="form-control" name="email" placeholder="Correo electrónico" required="required">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fa fa-envelope"></i></span>
							</div>
						</div>
						<div class="form-group input-group mb-5">
							<input type="password" class="form-control" name="contrasenya" placeholder="Contraseña" required="required">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fa fa-lock"></i></span>
							</div>
						</div>
						<%
							// Si no es null, muestra el mensaje
							if (error != null) {
				
								error = "<p class='text-danger text-center font-weight-bold'>CORREO ELECTRÓNICO O CONTRASEÑA INCORRECTA.</p>";
				
								out.append(error);
							}
				
							// Si no es null, muestra el mensaje
							if (register != null) {
				
								register = "<p class='text-danger text-center font-weight-bold'>COMPRUEBE SU CORREO PARA VERIFICAR LA CUENTA.</p>";
				
								out.append(register);
							}
				
							// Si no es null, muestra el mensaje
							if (activation != null) {
				
								activation = "<p class='text-danger text-center font-weight-bold'>SU CUENTA HA SIDO VERIFICADA.</p>";
				
								out.append(activation);
							}
							
							// Si no es null, muestra el mensaje
							if (delaccount != null) {
								
								delaccount = "<p class='text-danger text-center font-weight-bold'>SU CUENTA HA SIDO ELIMINADA.</p>";
				
								out.append(delaccount);
							}
						%>
							<button type="submit" class="btn btn-secondary d-flex mx-auto mb-5">Acceder a Damas Royale<i class="fa fa-sign-out ml-2" style="font-size:24px"></i></button>
							<p class="mb-0">¿No tienes un cuenta de Damas Royale? <a href="Register" class="text-decoration-none">Crear una nueva cuenta</a></p>
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