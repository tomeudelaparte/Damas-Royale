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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="media/favicon.png">
<link rel="stylesheet" href="css/Tablero.css">
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-sm bg-light navbar-dark border-bottom">
		<a class="navbar-brand p-4"> <img
			class="img-fluid mx-auto d-block" src="media/banner.png" width="400"></a>
	</nav>
	<div class="container-fluid bg-light pt-3 mb-3">
		<div class="row">
			<div class="col-3 bg-dark"></div>
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
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
				</div>
				<div class="row">
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
				</div>
				<div class="row">
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
					<div class="col bg-dark casilla"><img class="img-fluid mx-auto mt-2" src="media/pieza01.png"></div>
					<div class="col bg-light casilla"></div>
				</div>
			</div>
			<div class="col-3 border shadow pb-5 mr-4">
			<div class="row ">
					<img class="img-fluid mx-auto d-block border" src="media/default.jpg" width="150">
			</div>
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