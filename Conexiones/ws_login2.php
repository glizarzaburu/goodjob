<?php

$hostname="localhost";
$database="goodjobc_good_job_testing";
$username="goodjobc";
$password="";


$json=array();

	if(isset($_GET["username"]) && isset($_GET["password"])){
		$Ucorreo=$_GET['username'];
		$Upass=$_GET['password'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT * FROM usuarios WHERE username = '{$Ucorreo}' AND password = '{$Upass}'";
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){
		
			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}else{
			$results["username"]='No encontrado';
			$results["password"]='No encontrado';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}else{
		   	$results["username"]='WS Registro no encontrado!';
			$results["password"]='WS Registro no encontrado!';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>