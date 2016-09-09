create table poi (
	nombrepoi varchar(50) primary key,
	direccion varchar(50),
	coordenada_x numeric(12,6),
	coordenada_y numeric(12,6)
)

// Si lo bajaste recien no lo ejecutes
ALTER TABLE poi ALTER COLUMN coordenada_x numeric(12,6)
ALTER TABLE poi ALTER COLUMN coordenada_y numeric(12,6) 
//


create table sucursalBanco (
	nombreBanco varchar(50) primary key foreign key references poi(nombrepoi),
	nombreGerente varchar(50)
)

create table cgp (
	nombrecgp varchar(50) primary key foreign key references poi(nombrepoi),
	telefono numeric(10)
)

create table servicioCGP (
	nombreCGP varchar(50) foreign key references cgp(nombrecgp),
	servicio varchar(50)
	primary key(nombrecgp, servicio)
	
	
)

INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y)
    VALUES('Sucursal Banco BBVA', 'Av. Riestra 5012', -34.6719, -58.4695)
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco BBVA', 'Carlos Gutierrez')

INSERT INTO poi
    VALUES('Parada del 114', 'Av. Lacarra 2254', -34.6715, -58.4676)
	
INSERT INTO poi
    VALUES('Parada del 107', 'Av. Escalada 2214', -34.6578, -58.4787)

INSERT INTO poi
    VALUES('Parada del 47', 'Triunvirato 4566', -34.6715, -58.4676)

INSERT INTO poi
    VALUES('CGP_1', 'Av. Escalada 3112', -34.6672, -58.4669)
INSERT INTO cgp
    VALUES('CGP_1', 49822157)
INSERT INTO servicioCGP
    VALUES('CGP_1', 'Rentas')

INSERT INTO poi
    VALUES('CGP_2', 'Av. Escalada 3112', -34.6705, -58.4841)
INSERT INTO cgp
    VALUES('CGP_2', 42014897)
INSERT INTO servicioCGP
    VALUES('CGP_2', 'Asesoramiento')
INSERT INTO servicioCGP
    VALUES('CGP_2', 'Atencion al cliente')