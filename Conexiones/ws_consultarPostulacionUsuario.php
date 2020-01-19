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
	
	$consulta = $con -> prepare("select id 
		from postulacion_actividad 
		where id_usuario = {$id_usuario} and id_actividad = {$id_actividad};");
		
	$consulta -> execute();
	
	$consulta -> bind_result($id);
	
	$actividad = array();
	
	while ($consulta -> fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		
		array_push($actividad, $temp);
	}
	
	mysqli_close($con);
	
	echo json_encode($actividad);
?>