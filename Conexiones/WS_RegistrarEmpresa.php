<?php

	$hostname_localhost="localhost";
	$database_localhost="goodjod";
	$username_localhost="root";
	$password_localhost="";

	$json=array();

	if (isset($_GET["ErazonSocial"]) && isset($_GET["Eruc"]) && isset($_GET["Ecelular"]) && isset($_GET["Epass"]) && isset($_GET["EfechaRegistro"]) && isset($_GET["Eestado"])) {
		
		$ErazonSocial=$_GET['ErazonSocial'];
		$Eruc=$_GET['Eruc'];
		$Ecelular=$_GET['Ecelular'];
		$Epass=$_GET['Epass'];
		$EfechaRegistro=$_GET['EfechaRegistro'];
		$Eestado=$_GET['Eestado'];

		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$insert="INSERT INTO empresa(ErazonSocial, Eruc, Ecelular, Epass, EfechaRegistro, Eestado) VALUES('{$ErazonSocial}','{$Eruc}','{$Ecelular}','{$Epass}','{$EfechaRegistro}','{$Eestado}')";

		$resultado_insert=mysqli_query($conexion,$insert);

		if ($resultado_insert) {
			$consulta="SELECT * FROM empresa WHERE Eruc= '{$Eruc}'";
			$resultado=mysqli_query($conexion,$consulta);

			if ($registro=mysqli_fetch_array($resultado)) {
				$json['empresa'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}else{
			$resulta["ErazonSocial"]=0;
			$resulta["Eruc"]=0;
			$resulta["Ecelular"]='No Registra';
			$resulta["Epass"]='No Registra';
			$resulta["EfechaRegistro"]='No Registra';
			$resulta["Eestado"]=0;
			$json['empresa'][]=$resulta;
			echo json_encode($json);
		}
		

	}else{
			$resulta["ErazonSocial"]=0;
			$resulta["Eruc"]=0;
			$resulta["Ecelular"]='WS No Registra';
			$resulta["Epass"]='WS No Registra';
			$resulta["EfechaRegistro"]='WS No Registra';
			$resulta["Eestado"]=0;
			$json['empresa'][]=$resulta;
			echo json_encode($json);
		}

?>