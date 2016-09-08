use dds2016


create table usuario (
	nombreusuario varchar(50) primary key,
	contrasenia varchar(50) not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	administrador bit
)