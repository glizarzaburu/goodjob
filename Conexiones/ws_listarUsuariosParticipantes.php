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
	
	$id_actividad = $_GET['id_actividad'];
	
	$consulta = $con->prepare("select u.idUsuario, Unombre, u.reputacion_ptos, u.cantidad_votos
	from postulacion_actividad p inner join usuario u 
	on p.id_usuario = u.idUsuario 
	where p.id_actividad = {$id_actividad} and p.estado = 2;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre, $reputacion_ptos, $cantidad_votos);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['nombre'] = $nombre;
		$temp['reputacion_ptos'] = $reputacion_ptos;
		$temp['cantidad_votos'] = $cantidad_votos;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>