<?php

$hostname="localhost";
$database="goodjod";
$username="root";
$password="";


$json=array();

	$conexion=mysqli_connect($hostname,$username,$password,$database);
		
	$consulta="select a.id, titulo, a.descripcion, concat(Unombre, ' ', UPaterno) as 'NombreCompleto', fecha_creacion, fecha_fin 
		, participantes_actuales, participantes_requeridos, foto, t.descripcion as 'TipoRecompensa', recompensa, estado 
		from actividad a inner join usuario u 
		on a.id_usuario = u.idUsuario 
		inner join tipo_recompensa t 
		on t.id = a.tipo_recompensa 
		where estado = 1;";
	$resultado=mysqli_query($conexion,$consulta);

	if($consulta)
	{
		if($reg=mysqli_fetch_array($resultado))
		{
			$json['datos'][]=$reg;
		}
		mysqli_close($conexion);
		echo json_encode($json);
		
	}else
	{
		$results["Ucorreo"]='No encontrado';
		$results["Upass"]='No encontrado';
		$json['datos'][]=$results;
		echo json_encode($json);
	}
?>