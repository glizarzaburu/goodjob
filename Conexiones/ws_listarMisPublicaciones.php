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
	
	$id_empresa = $_GET['id_empresa'];
	
	$consulta = $con->prepare("SELECT id, titulo, fecha_fin, 
    participantes_actuales, estado 
    FROM actividad 
    where empresa = {$id_empresa};");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $titulo, $fecha_fin, $participantes_actuales, $estado);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['titulo'] = $titulo;
		$temp['fecha_fin'] = $fecha_fin;
		$temp['participantes_actuales'] = $participantes_actuales;
		$temp['estado'] = $estado;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>