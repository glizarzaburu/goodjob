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
	
	$consulta = $con->prepare("SELECT a.id, titulo, a.descripcion, e.EnombreComercial, a.fecha_creacion, a.fecha_fin,
	a.participantes_actuales, a.participantes_requeridos, a.foto, t.descripcion, 
	a.recompensa, d.DNombre, a.tipo_seleccion, mensaje, a.estado 
	FROM actividad a INNER JOIN empresa e ON a.empresa = e.idEmpresa 
	INNER JOIN tipo_recompensa t ON t.id = a.tipo_recompensa 
	INNER JOIN distrito d ON d.idDistrito = a.distrito 
	WHERE a.estado = 0;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $titulo, $descripcion, $empresa, $fecha_creacion, 
	$fecha_fin, $participantes_actuales, $participantes_requeridos, $foto, $tipo_recompensa, 
	$recompensa, $distrito, $tipo_seleccion, $mensaje, $estado);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['titulo'] = $titulo;
		$temp['descripcion'] = $descripcion;
		$temp['empresa'] = $empresa;
		$temp['fecha_creacion'] = $fecha_creacion;
		$temp['fecha_fin'] = $fecha_fin;
		$temp['participantes_actuales'] = $participantes_actuales;
		$temp['participantes_requeridos'] = $participantes_requeridos;
		$temp['url_foto'] = $foto;
		$temp['tipo_recompensa'] = $tipo_recompensa;
		$temp['recompensa'] = $recompensa;
		$temp['distrito'] = $distrito;
		$temp['tipo_seleccion'] = $tipo_seleccion;
		$temp['mensaje'] = $mensaje;
		$temp['estado'] = $estado;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>