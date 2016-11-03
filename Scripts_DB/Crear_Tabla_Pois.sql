create table poi (
	nombrepoi varchar(50) primary key,
	direccion varchar(50),
	coordenada_x numeric(12,6),
	coordenada_y numeric(12,6),
	tipo varchar(50) not null
)

/*Si nunca lo ejecutaste no ejecutes esto 
ALTER TABLE poi ALTER COLUMN coordenada_x numeric(12,6)
ALTER TABLE poi ALTER COLUMN coordenada_y numeric(12,6) 
ALTER TABLE poi ADD  tipo varchar(50) not null
/**/*/

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


INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y, tipo)
    VALUES('Sucursal Banco BBVA', 'Av. Riestra 5012', -34.6719, -58.4695, 'sucursalBanco')
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco BBVA', 'Carlos Gutierrez')

	INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y, tipo)
    VALUES('Sucursal BNA Plaza de Mayo', 'Av. Rivadavia 400', -34.6083, -58.3729, 'sucursalBanco')
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco BNA Plaza de Mayo', 'Raul Duzevic')


INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y, tipo)
    VALUES('Sucursal Banco Patagonia', 'Av. Rivadavia 6744', -34.6283,-58.4604, 'sucursalBanco')
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco Patagonia', 'Jonatan Dorso')	


INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y, tipo)
    VALUES('Sucursal Banco HSBC', 'Florida 201', -34.6056, -58.3750, 'sucursalBanco')
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco HSBC', 'Jorge Hernandez')


INSERT INTO poi(nombrepoi, direccion, coordenada_x, coordenada_y, tipo)
    VALUES('Sucursal Banco Ciudad', 'Av. Corrientes 2528', -34.6048,-58.4025, 'sucursalBanco')
INSERT INTO sucursalBanco(nombreBanco, nombreGerente)
    VALUES('Sucursal Banco Ciudad', 'Hernan Guidi')	


	
INSERT INTO poi
    VALUES('Parada del 114', 'Av. Lacarra 2254', -34.6715, -58.4676, 'ParadaColectivo')
	
INSERT INTO poi
    VALUES('Parada del 107', 'Av. Escalada 2214', -34.6578, -58.4787, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 47', 'Triunvirato 4566', -34.6715, -58.4676, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 302 a Moreno', 'Reconquista 100', -34.6080, -58.3727, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 302 a Liniers', 'Av. Presidente Perón 7174', -34.6381, -58.6697, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 7', 'Av. Escalada 2214', -34.6578, -58.4787, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 443', 'General Juan Lavalleja 800', -34.6411,-58.6705, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 7', 'Av. Escalada 2220', -34.6579, -58.4788, 'ParadaColectivo')	



INSERT INTO poi
    VALUES('Parada del 29', 'Av. Rivadavia 320', -34.6079, -58.3719, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 101', 'Av. Paseo Colon 357', -34.6119, -58.3696, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 92', 'Coronel Diaz 1910', -34.5889,-58.4099, 'ParadaColectivo')

INSERT INTO poi
    VALUES('Parada del 68', 'Av. Santa Fe 3258', -34.5887, -58.4108, 'ParadaColectivo')	






	
INSERT INTO poi
    VALUES('CGP_1', 'Av. Escalada 3112', -34.6672, -58.4669, 'CGP')
INSERT INTO cgp
    VALUES('CGP_1', 49822157)
INSERT INTO servicioCGP
    VALUES('CGP_1', 'Rentas')

INSERT INTO poi
    VALUES('CGP_2', 'Av. Escalada 3112', -34.6705, -58.4841, 'CGP')
INSERT INTO cgp
    VALUES('CGP_2', 42014897)
INSERT INTO servicioCGP
    VALUES('CGP_2', 'Asesoramiento')
INSERT INTO servicioCGP
    VALUES('CGP_2', 'Atencion al cliente')

INSERT INTO poi	
    VALUES('CGP_3', 'Av. Rivadavia 7202 ', -34.6302, -58.4670, 'CGP')
INSERT INTO cgp
    VALUES('CGP_3', 46372355)
INSERT INTO servicioCGP
    VALUES('CGP_3', 'Gestion Comunal')

INSERT INTO poi
    VALUES('CGP_4', 'Coronel Díaz 2110 ', -34.5873,-58.4090, 'CGP')
INSERT INTO cgp
    VALUES('CGP_4', 48275958)
INSERT INTO servicioCGP
    VALUES('CGP_4', 'Participacion Comunal')

INSERT INTO poi
    VALUES('CGP_5', 'Uruguay 740 Piso 1º',-34.6001, -58.3868, 'CGP')
INSERT INTO cgp
    VALUES('CGP_5', 43731896)
INSERT INTO servicioCGP
    VALUES('CGP_5', 'Asesoramiento')
INSERT INTO servicioCGP
    VALUES('CGP_5', 'Atencion al cliente')	

INSERT INTO poi	
    VALUES('CGP_6', 'Av. del Barco Centenera 2906',-34.6502,-58.4269, 'CGP')
INSERT INTO cgp
    VALUES('CGP_6', 08009992727)
INSERT INTO servicioCGP
    VALUES('CGP_6', 'Renovacion de DNI')



INSERT INTO poi	
    VALUES('CGP_7', 'Beruti 3325 ', -34.5874, -58.4093, 'CGP')
INSERT INTO cgp
    VALUES('CGP_7', 48894435)
INSERT INTO servicioCGP
    VALUES('CGP_7', 'Asesoramiento')
INSERT INTO servicioCGP
	VALUES('CGP_7', 'Atencion al cliente')

INSERT INTO poi
    VALUES('CGP_8', 'Carlos Calvo 3307 ', -34.6229,-58.4124, 'CGP')
INSERT INTO cgp
    VALUES('CGP_8', 45635594)
INSERT INTO servicioCGP
    VALUES('CGP_8', 'Renovacion de DNI')

INSERT INTO poi
    VALUES('CGP_9', 'Tacuari 101',-34.6095, -58.3791, 'CGP')
INSERT INTO cgp
    VALUES('CGP_9', 41334455)
INSERT INTO servicioCGP
    VALUES('CGP_9', 'Rentas')
INSERT INTO servicioCGP
    VALUES('CGP_9', 'Defensa al Consumidor')
INSERT INTO servicioCGP
    VALUES('CGP_9', 'Prevención del Delito')

INSERT INTO poi	
    VALUES('CGP_10', 'Uriburu 1022',-34.5966,-58.3991, 'CGP')
INSERT INTO cgp
    VALUES('CGP_10', 42318954)
INSERT INTO servicioCGP
    VALUES('CGP_10', 'Reclamos')




	

		