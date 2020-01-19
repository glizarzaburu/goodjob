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
	$id_actividad = $_GET['id_actividad'];
	
	$consulta_string = "INSERT INTO postulacion_actividad (id_actividad, id_usuario, fechaPostulacion) 
	VALUES ($id_actividad, $id_usuario, curdate())";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Acabas de postular!';
	}
	else{
		echo 'Ocurrió un error';
	}
	
	mysqli_close($con);
?>