SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE distrito (
  idDistrito int(11) NOT NULL primary key auto_increment,
  DNombre varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO distrito (`idDistrito`, `DNombre`) VALUES
(1, 'Callao'),
(2, 'Bellavista'),
(3, 'Carmen de La Legua'),
(4, 'La Perla'),
(5, 'La Punta'),
(6, 'Mi Perú'),
(7, 'Ventanilla'),
(8, 'Lima'),
(9, 'Ancón'),
(10, 'Ate'),
(11, 'Barranco'),
(12, 'Breña'),
(13, 'Carabayllo'),
(14, 'Cieneguilla'),
(15, 'Chaclacayo'),
(16, 'Chorrillos'),
(17, 'Comas'),
(18, 'El Agustino'),
(19, 'Independencia'),
(20, 'Jesús María'),
(21, 'La Molina'),
(22, 'La Victoria'),
(23, 'Lince'),
(24, 'Los Olivos'),
(25, 'Lurigancho'),
(26, 'Lurín'),
(27, 'Magdalena del Mar'),
(28, 'Miraflores'),
(29, 'Pachacámac'),
(30, 'Pucusana'),
(31, 'Pueblo Libre'),
(32, 'Puente Piedra'),
(33, 'Punta Hermosa'),
(34, 'Punta Negra'),
(35, 'Rímac'),
(36, 'San Bartolo'),
(37, 'San Borja'),
(38, 'San Isidro'),
(39, 'San Juan de Lurigancho'),
(40, 'San Juan de Miraflores'),
(41, 'San Luis'),
(42, 'San Martín de Porres'),
(43, 'San Miguel'),
(44, 'Santa Anita'),
(45, 'Santa María del Mar'),
(46, 'Santa Rosa'),
(47, 'Santiago de Surco'),
(48, 'Surquillo'),
(49, 'Villa El Salvador'),
(50, 'Villa María del Triunfo');


CREATE TABLE empresa (
  idEmpresa int(11) NOT NULL primary key auto_increment,
  `ErazonSocial` varchar(120) NOT NULL,
  `Eruc` varchar(11) NOT NULL,
  `Ececular` varchar(11) NOT NULL,
  `Edireccion` varchar(150) NOT NULL,
  `EfechaRegistro` date NOT NULL,
  `EcodigoPostal` varchar(10) NOT NULL,
  `estado` int(11) DEFAULT 0, -- 0: en espera | 1: activo/aceptado | 2: rechazado
  `EnombreComercial` varchar(120) NOT NULL,
  `EnumeroActividades` int(11) DEFAULT 0,
  `idDistrito` int(11) NOT NULL,
  `ecorreo` varchar(50) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  foreign key (idDistrito) references distrito(idDistrito)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `empresa` (`idEmpresa`, `ErazonSocial`, `Eruc`, `Ececular`, `Edireccion`, `EfechaRegistro`, 
`EcodigoPostal`, `estado`, `EnombreComercial`, `EnumeroActividades`, `idDistrito`, `ecorreo`, `password`) 
VALUES (1, 'GOODJOB SAC PERU', '20345678912', '987987789', 'Los de tomas valle mas naki', '2019-11-10', 
'15103', 1, 'GoodJob', 0, 42, 'goodjob@gmail.com', 'goodjob');


create table tipo_usuario
(
  id int primary key auto_increment,
  descripcion varchar(30) not null
);

insert into tipo_usuario values (null, 'Usuario');
insert into tipo_usuario values (null, 'Administrador');


CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL primary key auto_increment,
  `UFoto` varchar(250) DEFAULT NULL,
  `Unombre` varchar(50) NOT NULL,
  `UPaterno` varchar(50) NOT NULL,
  `UMaterno` varchar(50) NOT NULL,
  `Udni` varchar(8) DEFAULT NULL UNIQUE,
  `UfechaNacimiento` date DEFAULT NULL,
  `Ucelular` varchar(11) DEFAULT NULL,
  `Ucorreo` varchar(50) NOT NULL,
  `Udireccion` varchar(150) DEFAULT NULL,
  `Upass` varchar(40) NOT NULL,
  `UfechaRegistro` date NOT NULL,
  reputacion_ptos decimal(8,2) default 0,
  cantidad_votos int default 0,
  `Uestado` int(11) default 1,
  UnumeroCuentaTarjeta varchar(16) default null,
  Ugenero varchar(20) default null,
  UestadoCivil varchar(20) default null,
  Ucv varchar(40) default null,
  `idDistrito` int(11) DEFAULT 42,
  UlinkFacebook varchar(250) default null,
  UlinkLinkedin varchar(250) default null,
  tipo_usuario int default 1, 
  puntaje int default 0,
  foreign key (idDistrito) references distrito(idDistrito),
  foreign key (tipo_usuario) references tipo_usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO usuario (Unombre, Upaterno, Umaterno, Udni, Ucorreo, Upass, UfechaRegistro, idDistrito, tipo_usuario) VALUES 
('Juan Carlos', 'Castillo', 'Aycachi', '70555913', 'carlosjordi@hotmail.com', sha1('admin'), '2019-07-01', 42, 2);

INSERT INTO usuario (Unombre, Upaterno, Umaterno, Udni, Ucorreo, Upass, UfechaRegistro, idDistrito, tipo_usuario) VALUES
('Max Anthony', 'Cachi', 'Ayala', '63571099', 'anthonyca18m@gmail.com', sha1('admin'), '2019-06-28', 17, 2);

INSERT INTO usuario (Unombre, Upaterno, Umaterno, Udni, Ucorreo, Upass, UfechaRegistro, idDistrito, tipo_usuario) VALUES
('Gustavo Adolfo', 'Lizarzaburu', 'Mercado', '78953215', 'lizarzaburu@gmail.com', sha1('admin'), '2019-07-02', 42, 1);

INSERT INTO usuario (Unombre, Upaterno, Umaterno, Udni, Ucorreo, Upass, UfechaRegistro, idDistrito, tipo_usuario) VALUES
('Bratzon Hilser', 'Pacheco', 'Bada', '89214101', 'mituga6@gmail.com', sha1('admin'), '2019-10-22', 42, 1);


create table tipo_recompensa
(
	id int auto_increment primary key,
    descripcion varchar(30)
);

insert into tipo_recompensa values(null, 'Puntos');
insert into tipo_recompensa values(null, 'Dinero');


create table actividad
(
	id int(11) auto_increment primary key,
    titulo varchar(30) not null,
    descripcion varchar(140) not null,
    empresa int not null,
    fecha_creacion date not null,
    fecha_fin date not null, 
    participantes_actuales int default 0,
    participantes_requeridos int not null,
    foto varchar(255) default null,
    tipo_recompensa int not null,
    recompensa decimal(10,2) not null,
    distrito int not null,
    tipo_seleccion int not null,
    mensaje varchar(250) default null,
    estado int default 0, -- 0: en espera | 1: aceptado | 2: rechazado
    foreign key (tipo_recompensa) references tipo_recompensa(id),
    FOREIGN KEY (empresa) references empresa (idEmpresa),
    FOREIGN KEY (distrito) references distrito (idDistrito)
);

insert into actividad (titulo, descripcion, empresa, fecha_creacion, fecha_fin, participantes_requeridos, foto, tipo_recompensa, 
recompensa, distrito, tipo_seleccion, estado) values ('GoodJob Opening', 'Apertura de la plataforma GoodJob', 1, curdate(), '2019-12-17',
10, '1GoodJob Opening', 1, 3000, 1, 1, 1);

insert into actividad (titulo, descripcion, empresa, fecha_creacion, fecha_fin, participantes_requeridos, foto, tipo_recompensa, 
recompensa, distrito, tipo_seleccion, estado) values ('GoodJob Afterparty', 'Celebreishon GoodJob', 1, curdate(), '2019-12-17',
20, '1GoodJob Afterparty', 1, 5000, 1, 1, 1);

insert into actividad (titulo, descripcion, empresa, fecha_creacion, fecha_fin, participantes_requeridos, foto, tipo_recompensa, 
recompensa, distrito, tipo_seleccion, estado) values ('Navidad con GoodJob', 'Celebremos la navidad junto a los integrantes de GoodJob', 1, curdate(), '2019-12-25',
7, '1Navidad con GoodJob', 1, 2000, 1, 1, 1);

insert into actividad (titulo, descripcion, empresa, fecha_creacion, fecha_fin, participantes_requeridos, foto, tipo_recompensa, 
recompensa, distrito, tipo_seleccion, estado) values ('Serruchando a Cachi', 'De la mano del gran Bratzon, veamos como se serrucha el piso como nunca antes', 1, 
curdate(), '2019-12-03', 3, '1Serruchando a Cachi', 2, 300, 1, 1, 1);


create table postulacion_actividad
(
	id int auto_increment primary key,
    id_actividad int not null,
    id_usuario int not null,
    fechaPostulacion date not null,
    reputacion_promedio decimal(4,2) default 0,
    asistencia int default 0, -- 0: nada | 1: asistio | 2: no asistio
    estado int default 1, -- 1: en espera | 2: aceptado | 3: rechazado
    foreign key(id_actividad) references actividad(id),
    foreign key(id_usuario) references usuario(idUsuario)
);

CREATE TABLE `producto` (
  `id_producto` int(11) primary key auto_increment,
  `nombre` varchar(40) not NULL,
  `stock` int(11) not NULL,
  `valor` decimal(8,2) not NULL,
  `imagen` varchar(200) not NULL,
  empresa int not NULL,
  fecha_registro date not null,
  estado int default 0, -- 0: en espera | 1: aceptado | 2: rechazado
  foreign key (empresa) references empresa(idEmpresa)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO producto (nombre, stock, valor, imagen, empresa, fecha_registro) 
VALUES ('Licuadora', 10, 100, '1Licuadora100', 1, curdate());


CREATE TABLE lugares_canje (
  `id_lugar` int(11) primary key auto_increment,
  `descripcion` varchar(255) not NULL,
  producto int not NULL,
  foreign key (producto) references producto (id_producto)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

COMMIT;
