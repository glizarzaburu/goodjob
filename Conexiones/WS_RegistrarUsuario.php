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
	
	$nombre = $_POST['nombre'];
	$paterno = $_POST['paterno'];
	$materno = $_POST['materno'];
	$correo = $_POST['correo'];
	$password = $_POST['password'];
	
    $consulta_string = "INSERT INTO usuario (Unombre, UPaterno, UMaterno, Ucorreo, Upass, UfechaRegistro) 
	VALUES ('$nombre', '$paterno', '$materno', '$correo', sha1('$password'), curdate());";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo "Cuenta creada";
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>