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
	
	$id_postulacion = $_GET['id_postulacion'];
	
	$consulta = $con->prepare("SELECT concat(u.Unombre, ' ', u.Upaterno) as nombre FROM postulacion_actividad p inner join actividad a 
    on p.id_actividad = a.id inner join usuario u 
    on u.idUsuario = a.id_usuario where p.id = {$id_postulacion};");
		
	$consulta->execute();
	
	$consulta->bind_result($nombre_publicante);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['nombre_publicante'] = $nombre_publicante;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>