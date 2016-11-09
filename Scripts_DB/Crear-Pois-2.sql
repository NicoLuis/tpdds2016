

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Riestra 5012', -34.6719, -58.4695, 'sucursalBanco','Sucursal Banco BBVA')
INSERT INTO dbo.sucursalBanco(nombreGerente,nombreBanco)
    VALUES('Carlos Gutierrez', 'Sucursal Banco BBVA')
INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco BBVA', 'Deposito')
INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco BBVA', ' Prestamo')



	INSERT INTO dbo.poi(direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Rivadavia 400', -34.6083, -58.3729, 'sucursalBanco','Sucursal BNA Plaza de Mayo')
INSERT INTO dbo.sucursalBanco(nombreGerente, nombreBanco)
    VALUES('Raul Duzevic','Sucursal Banco BNA Plaza de Mayo')
	INSERT INTO dbo.servicioBanco VALUES ('Sucursal BNA Plaza de Mayo', 'Deposito')
INSERT INTO dbo.servicioBanco VALUES ('Sucursal BNA Plaza de Mayo', 'Prestamo')


INSERT INTO dbo.poi(direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Rivadavia 6744', -34.6283,-58.4604, 'sucursalBanco','Sucursal Banco Patagonia')
INSERT INTO dbo.sucursalBanco(nombreGerente, nombreBanco)
    VALUES('Jonatan Dorso' , 'Sucursal Banco Patagonia')	
INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco Patagonia', 'Deposito')



INSERT INTO dbo.poi(direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Florida 201', -34.6056, -58.3750, 'sucursalBanco','Sucursal Banco HSBC')
INSERT INTO dbo.sucursalBanco(nombreGerente, nombreBanco)
    VALUES('Jorge Hernandez','Sucursal Banco HSBC')
INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco HSBC', 'Deposito')

INSERT INTO dbo.poi(direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Corrientes 2528', -34.6048,-58.4025, 'sucursalBanco','Sucursal Banco Ciudad')
INSERT INTO dbo.sucursalBanco(nombreGerente, nombreBanco)
    VALUES('Hernan Guidi' ,'Sucursal Banco Ciudad')	
	INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco Ciudad', 'Deposito')
INSERT INTO dbo.servicioBanco VALUES ('Sucursal Banco Ciudad', ' Prestamo')

	
INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. Lacarra 2254', -34.6715, -58.4676, 'ParadaColectivo','Parada del 114')
	
INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Escalada 2214', -34.6578, -58.4787, 'ParadaColectivo','Parada del 107')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Triunvirato 4566', -34.6715, -58.4676, 'ParadaColectivo','Parada del 47')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Reconquista 100', -34.6080, -58.3727, 'ParadaColectivo','Parada del 302 a Moreno')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. Presidente Peron 7174', -34.6381, -58.6697, 'ParadaColectivo','Parada del 302 a Liniers')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Escalada 2214', -34.6578, -58.4787, 'ParadaColectivo','Parada del 7')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('General Juan Lavalleja 800', -34.6411,-58.6705, 'ParadaColectivo','Parada del 443')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Escalada 2220', -34.6579, -58.4788, 'ParadaColectivo','Parada del 7')	



INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Rivadavia 320', -34.6079, -58.3719, 'ParadaColectivo','Parada del 29')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. Paseo Colon 357', -34.6119, -58.3696, 'ParadaColectivo','Parada del 101')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Coronel Diaz 1910', -34.5889,-58.4099, 'ParadaColectivo','Parada del 92')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Av. Santa Fe 3258', -34.5887, -58.4108, 'ParadaColectivo','Parada del 68')	







INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. Escalada 3112', -34.6672, -58.4669, 'CGP','CGP_1')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
   VALUES(49822157, '10:30' , '18:00','CGP_1')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_1', ' Rentas ')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. Escalada 3112', -34.6705, -58.4841, 'CGP','CGP_2')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
    VALUES(43313157, '10:00' , '18:00','CGP_2')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_2', ' Asesoramiento ')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_2', 'Atencion al cliente')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)	
    VALUES( 'Av. Rivadavia 7202 ', -34.6302, -58.4670, 'CGP','CGP_3')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
   VALUES(44442157, '10:30' , '19:00','CGP_3')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_3', 'Gestion Comunal')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Coronel Diaz 2110 ', -34.5873,-58.4090, 'CGP','CGP_4')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
    VALUES(49835556, '10:00' , '16:00','CGP_4')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_4', ' Participacion Comunal ')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Uruguay 740',-34.6001, -58.3868, 'CGP','CGP_5')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
    VALUES(43562757, '09:30' , '19:00','CGP_5')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_5', ' Asesoramiento ')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_5', ' Atencion al cliente ')	

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES( 'Av. del Barco Centenera 2906',-34.6502,-58.4269, 'CGP','CGP_6')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
    VALUES(49821557, '10:30' , '15:00','CGP_6')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_6', ' Renovacion de DNI ')



INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Beruti 3325 ', -34.5874, -58.4093, 'CGP','CGP_7')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
    VALUES(42566317, '10:30' , '19:00','CGP_7')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_7', ' Asesoramiento ')
INSERT INTO dbo.servicioCGP
	VALUES('CGP_7', ' Atencion al cliente')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Carlos Calvo 3307 ', -34.6229,-58.4124, 'CGP','CGP_8')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
  VALUES(44442157, '10:30' , '20:00','CGP_8')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_8', ' Renovacion de DNI')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)
    VALUES('Tacuari 101',-34.6095, -58.3791, 'CGP','CGP_9')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
  VALUES(49445357, '09:30' , '16:00','CGP_9')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_9', ' Rentas')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_9', ' Defensa al Consumidor')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_9', ' Prevencion del Delito')

INSERT INTO dbo.poi( direccion, coordenada_x, coordenada_y, tipo,nombrepoi)	
    VALUES('Uriburu 1022',-34.5966,-58.3991, 'CGP','CGP_10')
INSERT INTO dbo.cgp (telefono,horainicio,horafin,nombreCGP)
  VALUES(49444157, '10:30' , '18:00','CGP_10')
INSERT INTO dbo.servicioCGP
    VALUES('CGP_10', ' Reclamos')





	
