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
	
	$consulta = $con->prepare("SELECT id_producto, nombre, stock, valor, imagen, e.EnombreComercial 
    FROM producto p INNER JOIN empresa e 
    ON p.empresa = e.idEmpresa 
    WHERE p.estado = 0;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre, $stock, $valor, $imagen_url, $empresa);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
        $temp['id'] = $id;
        $temp['nombre'] = $nombre;
        $temp['stock'] = $stock;
        $temp['valor'] = $valor;
        $temp['imagen_url'] = $imagen_url;
        $temp['empresa'] = $empresa;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>