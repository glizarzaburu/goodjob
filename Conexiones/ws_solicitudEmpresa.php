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
	
	$nombreComercial = $_POST['nombre_comercial'];
	$ruc = $_POST['ruc'];
	$telefono = $_POST['telefono'];
	$direccion = $_POST['direccion'];
	$razonSocial = $_POST['razon_social'];
    $correo = $_POST['correo'];
    $codigoPostal = $_POST['codigo_postal'];
    $idDistrito = $_POST['id_distrito'];
	
    $consulta_string = "INSERT INTO empresa (ErazonSocial, Eruc, Ececular, Edireccion, 
        EnombreComercial, ecorreo, EfechaRegistro, EcodigoPostal, idDistrito) VALUES (
        '$razonSocial', '$ruc', '$telefono', '$direccion', '$nombreComercial', '$correo', 
        CURDATE(), '$codigoPostal', $idDistrito
    )";
	
	if (mysqli_query($con, $consulta_string))
	{
		echo "Solicitud Enviada";
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>
