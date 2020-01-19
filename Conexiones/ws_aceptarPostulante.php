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
	
    $consulta_string = "update postulacion_actividad set estado = 2 where 
    id_actividad = {$id_actividad} and id_usuario = {$id_usuario};";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Aceptado';
	}
	else{
		echo 'Ocurrió un error';
	}
	
	mysqli_close($con);
?>