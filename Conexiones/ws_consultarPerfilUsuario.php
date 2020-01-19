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
	
	$consulta = $con->prepare("SELECT idUsuario, concat(Unombre, ' ', UPaterno, ' ', UMaterno) as nombre, puntaje, reputacion_ptos, cantidad_votos
from usuario where idUsuario = {$id_usuario};");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre, $puntaje, $reputacion_ptos, $cantidad_votos);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['nombre'] = $nombre;
		$temp['puntaje'] = $puntaje;
		$temp['reputacion_ptos'] = $reputacion_ptos;
		$temp['cantidad_votos'] = $cantidad_votos;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>