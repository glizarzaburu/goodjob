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
	
	$consulta = $con->prepare("select a.id, concat(Unombre, ' ', Upaterno, ' ', Umaterno) as nombre_completo, titulo, a.fecha_fin, p.estado, d.dnombre 
		from postulacion_actividad p inner join actividad a 
		on p.id_actividad = a.id inner join usuario u 
		on u.idUsuario = p.id_usuario 
		inner join distrito d on d.iddistrito = a.distrito 
		where p.id_usuario = {$id_usuario};");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre_completo, $titulo, $fecha_fin, $estado, $distrito);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['titulo'] = $titulo;
		$temp['nombre_completo'] = $nombre_completo;
		$temp['fecha_fin'] = $fecha_fin;
		$temp['estado'] = $estado;
		$temp['distrito'] = $distrito;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>