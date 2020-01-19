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
	
	$consulta = $con->prepare("SELECT id_producto, nombre, stock, valor, fecha_registro FROM producto WHERE estado = 0 AND empresa = $id_empresa;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre, $stock, $valor, $fecha_registro);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['nombre'] = $nombre;
		$temp['stock'] = $stock;
        $temp['valor'] = $valor;
        $temp['fecha_registro'] = $fecha_registro;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>