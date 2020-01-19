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
    
    $ruc = $_GET['ruc'];
    $pass = $_GET['pass'];
	
    $consulta = $con->prepare("SELECT idEmpresa, ErazonSocial, Eruc, Ececular, Edireccion, EfechaRegistro,
    EcodigoPostal, ecorreo, EnombreComercial, EnumeroActividades, DNombre, estado from empresa e 
    INNER JOIN distrito d ON e.idDistrito = d.idDistrito WHERE 
    Eruc = '$ruc' AND e.password = '$pass'");
		
	$consulta->execute();
	
    $consulta->bind_result($id, $razon_social, $ruc, $celular, $direccion, $fecha_registro, $codigo_postal,
        $correo, $nombre_comercial, $numero_actividades, $distrito, $estado);
	
	$empresa = array();
	
	while($consulta->fetch())
	{
		$temp = array();
        $temp['id'] = $id;
        $temp['razon_social'] = $razon_social;
        $temp['nombre_comercial'] = $nombre_comercial;
        $temp['ruc'] = $ruc;
        $temp['telefono'] = $celular;
        $temp['correo'] = $correo;
        $temp['codigo_postal'] = $codigo_postal;
        $temp['distrito'] = $distrito;
        $temp['direccion'] = $direccion;
        $temp['fecha_registro'] = $fecha_registro;
        $temp['numero_actividades'] = $numero_actividades;
        $temp['estado'] = $estado;
		array_push($empresa, $temp);
	}
	echo json_encode($empresa);
	mysqli_close($con);
?>