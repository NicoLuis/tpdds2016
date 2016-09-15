use dds2016


create table accionXusuario (
	nombreUsuario varchar(50) foreign key references usuario(nombreUsuario),
	accion varchar(50)
	primary key(nombreUsuario, accion)
)


insert into accionXusuario values
('admin', 'Totalizar Por Fecha'),
('admin', 'Totalizar Por Usuario'),
('admin', 'Generar Log'),

('nLuis', 'Totalizar Por Fecha'),
('nLuis', 'Totalizar Por Usuario')