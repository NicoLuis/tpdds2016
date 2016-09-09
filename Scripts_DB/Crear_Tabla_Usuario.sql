use dds2016


create table usuario (
	nombreusuario varchar(50) primary key,
	contrasenia varchar(50) not null,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	administrador bit
)




INSERT INTO usuario
    VALUES('admin', '4F8Q91ePWMvdNEbFmKiQqA==', 'admin', 'admin', 'True')
INSERT INTO usuario
    VALUES('nLuis', '4F8Q91ePWMvdNEbFmKiQqA==', 'admin', 'admin', 'True')
INSERT INTO usuario
    VALUES('hugo', 'N54PpvcUWMx+p6Ob0XrYmA==', 'user', 'user', 'False')
INSERT INTO [dbo].[usuario]
	VALUES('jBenitez', 'gJlj/JeZnA3NwuQKcArdVg====', 'julieta', 'benitez', 'False')
INSERT INTO [dbo].[usuario]
	VALUES('mbrandes', '1OPhWTD/4aug+pbX3XjYLA====', 'milton', 'brandes', 'True')
INSERT INTO [dbo].[usuario]
	VALUES('user', '+nChvldIy7NubRsewRbHUA==', 'user', 'user', 'false')