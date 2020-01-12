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
	
    $producto = $_POST['producto'];
    $valor = $_POST['valor'];
    $stock = $_POST['stock'];
    $empresa = $_POST['id_empresa'];
	$imagen = $_POST['imagen'];
	$producto_sin_espacio = str_replace(' ', '', $producto);
    $url_imagen = $empresa.$producto_sin_espacio.$valor.".jpg";
	
    $consulta_string = "INSERT INTO producto(nombre, stock, valor, imagen, 
    empresa, fecha_registro) VALUES ('$producto', $stock, $valor, '$url_imagen', $empresa, curdate());";

    $ruta_subida = "imagenes_productos/$url_imagen";
	
	if (mysqli_query($con, $consulta_string))
	{
        file_put_contents($ruta_subida, base64_decode($imagen));
		echo "Producto en espera";
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>
