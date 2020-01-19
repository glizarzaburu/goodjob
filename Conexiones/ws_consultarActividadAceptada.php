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
	
	$consulta = $con->prepare("SELECT a.titulo, e.EnombreComercial, a.fecha_fin, 
	a.descripcion, concat(t.descripcion, ': ', a.recompensa) as recompensa
	from actividad a INNER JOIN empresa e
	ON a.empresa = e.idEmpresa INNER JOIN tipo_recompensa t ON t.id = a.tipo_recompensa 
	WHERE a.id = $id_actividad;");
		
	$consulta->execute();
	
	$consulta->bind_result($titulo, $nombre_completo, $fecha_fin, $descripcion, $recompensa);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['titulo'] = $titulo;
		$temp['nombre_completo'] = $nombre_completo;
		$temp['fecha_fin'] = $fecha_fin;
		$temp['descripcion'] = $descripcion;
		$temp['recompensa'] = $recompensa;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>