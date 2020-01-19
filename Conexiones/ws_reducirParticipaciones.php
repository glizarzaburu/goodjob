<?php

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'goodjod');
	
	$con = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	if(mysqli_connect_errno())
	{
		die('Unable to connect to database' . mysqli_connect_error());
	}
	
	$id_usuario = $_GET['id_usuario'];
	$cantidad = $_GET['cantidad'];
	
	$consulta_string = "update usuario set actividades_disponibles = {$cantidad} where idUsuario = {$id_usuario};";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Actualizado';
	}
	else{
		echo 'Ocurrió un error';
	}
	
	mysqli_close($con);
?>