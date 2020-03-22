<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Portal - Damas Royale</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/LoginAndRegister.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" href="media/favicon.png">
</head>
<body class="bg-secondary">
	<%
		String error = (String) request.getAttribute("error");
		String register = (String) request.getAttribute("register");
		String activation = (String) request.getAttribute("activation");
	%>
	<div class="container d-flex h-100">
		<div class="row align-self-center w-100">
			<div class="col-6 mx-auto">
				<div class="jumbotron shadow pb-4">
					<img class="img-fluid mx-auto d-block mb-5" src="media/logo.png" width="350">
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
							if (error != null) {
				
								error = "<p class='text-danger text-center'>*ERROR AL INICIAR SESIÓN*</p>";
				
								out.append(error);
							}
				
							if (register != null) {
				
								register = "<p class='text-danger text-center'>COMPRUEBE SU CORREO PARA ACTIVAR LA CUENTA</p>";
				
								out.append(register);
							}
				
							if (activation != null) {
				
								activation = "<p class='text-danger text-center'>SU CUENTA HA SIDO ACTIVADA</p>";
				
								out.append(activation);
							}
						%>
							<button type="submit" class="btn btn-secondary d-flex mx-auto mb-5">Iniciar sesión en Damas Royale<i class="fa fa-sign-out ml-2" style="font-size:24px"></i></button>
							<p class="mb-0">¿No tienes un cuenta de Damas Royale? <a href="Register" class="text-decoration-none">Crear una nueva cuenta</a></p>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>