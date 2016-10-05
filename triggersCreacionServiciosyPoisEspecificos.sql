/*SCRIPT CON TRIGGERS PARA CUANDO SE CREA UN POI*/

create trigger dbo.AgregarEspecifico on dbo.poi after insert
as
Begin
	declare @tipo varchar(50)
	set @tipo = (select tipo from inserted)
	if(@tipo = 'CGP')
		insert into cgp (poi_id, nombreCGP, horafin,horainicio,telefono) values ((select poi_id from inserted), 
		(select inserted.nombrepoi from inserted), '20', '08', 12345678)
	if(@tipo = 'CGP')
		insert into servicioCGP (nombreCGP, servicio) values ((select nombrepoi from inserted), 'Atencion al cliente')  
	if(@tipo = 'sucursalBanco')
		insert into sucursalBanco(poi_id, nombrebanco, nombreGerente) values ((select poi_id from inserted), 
		(select inserted.nombrepoi from inserted), 'Ignacio Copani')
	if(@tipo = 'sucursalBanco')
		insert into serviciobanco(banco, servicio) values ((select nombrepoi from inserted), 'Asesoramiento')
	if(@tipo = 'sucursalBanco')
		insert into serviciobanco(banco, servicio) values ((select nombrepoi from inserted), 'Atencion al cliente')	
	End
go
insert into poi (nombrepoi, tipo,coordenada_x,coordenada_y,direccion) values ('CGP_2', 'CGP', 222222, 333333, 'No la se' )
	 




	 

























																								