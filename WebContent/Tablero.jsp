<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.damasroyale.modelo.pojo.Usuario"%>
<%@ page import="com.damasroyale.modelo.pojo.Rank"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>Sala 01 - Damas Royale</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="js/websocketclient/ChatClient.js"></script>
<script src="js/Tablero.js"></script>
<script src="js/plugins/TextareaAutoHeight.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<link rel="stylesheet" href="css/Tablero.css">
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom shadow-sm">
		<a class="navbar-brand p-4"> <img class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	</nav>
	<div class="container-fluid bg-light pt-3 mb-3">
		<div class="row">
			<div class="col-3 border shadow ml-4">
				<h2 class="p-2 text-dark">Oponente</h2>
				<hr>
				<div class="row mx-auto shadow m-3 p-2">
					<div class="col-5">
						<img class="img-fluid mx-auto d-block border" src="media/default.jpg" width="150">
					</div>
					<div class="col-7 mt-3">	
						<h1 class="text-center">Detroiton</h1>
						<p class="text-center">1200 PTS</p>
					</div>
				</div>
				<h2 class="p-2 text-dark">Tú</h2>
				<hr>
				<div class="row mx-auto shadow m-3 p-2">
					<div class="col-5">
						<img class="img-fluid mx-auto d-block border" src="media/default.jpg" width="150">
					</div>
					<div class="col-7 mt-3">	
						<h1 class="text-center">Mayorhkiin</h1>
						<p class="text-center">750 PTS</p>
					</div>
				</div>
			</div>
			<div class="col-5 border shadow mx-auto m-0 tablero">
				<div class="row">
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"> <img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
				</div>
				<div class="row">
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row">
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza02.png"></div>
				</div>
				<div class="row">
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row">
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"></div>
				</div>
				<div class="row">
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row">
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
				</div>
				<div class="row">
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2 ficha" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
				</div>
			</div>
			<div class="col-3 border shadow mr-4">
				<h2 class="p-2 text-dark">Chat</h2>
				<hr>
				<form class="mx-auto">
					<div class="form-group">
					  <textarea id="chat" class="form-control mt-2 mb-2 border-0 bg-light" readonly disabled style="resize: none"></textarea>
					</div>
					<div class="form-group">
					  <input id="message" class="form-control bg-white pl-2 text-bold font-weight-bold" name="mensaje" type="text" placeholder=" Envía un mensaje a tu oponente.">
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer id="sticky-footer"
		class=" p-3 bg-light text-dark border-top shadow-lg">
		<div class="container text-center">
			<small>Copyright &copy; 2020 Damas Royale by Tomeu de la
				Parte</small>
		</div>
	</footer>
</body>
</html>