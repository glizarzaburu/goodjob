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

    $id = $_GET['id_empresa'];

    $consulta_string = "update empresa set estado = 2 where idEmpresa = $id";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo 'Rechazado';
	}
	else{
		echo 'Ocurrió un error';
	}
	
	mysqli_close($con);
?>