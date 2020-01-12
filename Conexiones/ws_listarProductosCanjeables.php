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
	
	$consulta = $con->prepare("select id_producto, nombre, stock, valor, imagen, e.EnombreComercial, fecha_registro 
    from producto p inner join empresa e 
    on e.idEmpresa = p.empresa 
    where p.estado = 1;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $nombre, $stock, $valor, $url_imagen, $empresa, $fecha_registro);
	
	$actividad = array();
	
	while($consulta->fetch())
	{
		$temp = array();
		$temp['id'] = $id;
		$temp['nombre'] = $nombre;
		$temp['stock'] = $stock;
        $temp['valor'] = $valor;
        $temp['url_imagen'] = $url_imagen;
        $temp['empresa'] = $empresa;
        $temp['fecha_registro'] = $fecha_registro;
		
		array_push($actividad, $temp);
	}
	echo json_encode($actividad);
	mysqli_close($con);
?>