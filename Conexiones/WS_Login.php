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
    
    $correo = $_GET['correo'];
    $pass = $_GET['pass'];
	
    $consulta = $con->prepare("SELECT idUsuario, UFoto, Unombre, UPaterno, UMaterno, Udni, 
	UfechaNacimiento, Ucelular, Ucorreo, Udireccion, UfechaRegistro, reputacion_ptos, 
	cantidad_votos, Uestado, UnumeroCuentaTarjeta, Ugenero, UestadoCivil, Ucv, d.DNombre, 
	UlinkFacebook, UlinkLinkedin, t.descripcion, puntaje
	FROM usuario u INNER JOIN distrito d ON u.idDistrito = d.idDistrito 
	INNER JOIN tipo_usuario t ON t.id = u.tipo_usuario 
	WHERE Ucorreo = '$correo' AND Upass = sha1('$pass') AND Uestado = 1;");
		
	$consulta->execute();
	
	$consulta->bind_result($id, $url_foto, $nombres, $paterno, $materno, $dni, 
	$fecha_nacimiento, $celular, $correo, $direccion, $fecha_registro, $reputacion_ptos, 
	$cantidad_votos, $estado, $numero_tarjeta, $genero, $estado_civil, $cv, $distrito, 
	$fb, $linkedin, $tipo_usuario, $puntaje);
	
	$usuario = array();
	
	while($consulta->fetch())
	{
		$temp = array();
        $temp['id'] = $id;
		$temp['url_foto'] = $url_foto;
		$temp['nombre'] = $nombres;
		$temp['paterno'] = $paterno;
		$temp['materno'] = $materno;
		$temp['dni'] = $dni;
		$temp['fecha_nacimiento'] = $fecha_nacimiento;
		$temp['celular'] = $celular;
		$temp['correo'] = $correo;
		$temp['direccion'] = $direccion;
		$temp['fecha_registro'] = $fecha_registro;
		$temp['reputacion_ptos'] = $reputacion_ptos;
		$temp['cantidad_votos'] = $cantidad_votos;
		$temp['estado'] = $estado;
		$temp['numero_tarjeta'] = $numero_tarjeta;
		$temp['genero'] = $genero;
		$temp['estado_civil'] = $estado_civil;
		$temp['cv'] = $cv;
		$temp['distrito'] = $distrito;
		$temp['link_fb'] = $fb;
		$temp['link_linkedin'] = $linkedin;
		$temp['tipo_usuario'] = $tipo_usuario;
		$temp['puntaje'] = $puntaje;
		array_push($usuario, $temp);
	}
	echo json_encode($usuario);
	mysqli_close($con);
?>