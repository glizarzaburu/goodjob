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
	
	$titulo = $_POST['titulo'];
	$descripcion = $_POST['descripcion'];
	$id_empresa = $_POST['id_empresa'];
	$fecha_fin = $_POST['fecha_fin'];
	$participantes_requeridos = $_POST['participantes_requeridos'];
	$recompensa = $_POST['recompensa'];
	$foto = $_POST['foto'];
	$distrito = $_POST['distrito'];
	$tipo_seleccion = $_POST['tipo_seleccion'];
	$tipo_recompensa = $_POST['tipo_recompensa'];
	$titulo_sin_espacios = str_replace(' ', '', $titulo);
	$url_foto = $id_empresa.$titulo_sin_espacios.".jpg";
	
	$consulta_string = "insert into actividad (id, titulo, descripcion, empresa, fecha_creacion, fecha_fin, 
	participantes_actuales, participantes_requeridos, foto, tipo_recompensa, recompensa, distrito, tipo_seleccion, estado)  
	values(null, '{$titulo}', '{$descripcion}', $id_empresa, CURDATE(), '{$fecha_fin}', 0, {$participantes_requeridos}, 
	'{$url_foto}', $tipo_recompensa, {$recompensa}, $distrito, $tipo_seleccion, 0);";

	$ruta_subida = "imagenes_actividades/$url_foto";
	
	if (mysqli_query($con, $consulta_string))
	{
		file_put_contents($ruta_subida, base64_decode($foto));
		echo "Actividad Publicada";
	}
	else{
		echo mysqli_error($con);
	}
	
	mysqli_close($con);
?>
