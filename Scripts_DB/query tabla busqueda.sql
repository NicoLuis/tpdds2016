
USE [dds2016]
GO

/****** Object:  Table [dbo].[busquedas]    Script Date: 15/09/2016 16:38:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[busquedas](
	[usuario] [varchar](50) NOT NULL,
	[parametros] [varchar](50) NOT NULL,
	[cantresultados] [numeric](18, 0) NOT NULL,
	[fechayhora] [datetime] NOT NULL,
	[poisresultado] [text] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


INSERT INTO dbo.busquedas VALUES ('admin',	'nombre: Honda', 4, 2015-12-03,'Honda Pilar, Concesionario Honda, Honda Autos, Buena Honda')
INSERT INTO dbo.busquedas VALUES ('admin',	'nombre: Yamaha',	2,	2015-12-03,	'Concesionario Yamaha, Yamaha Motors')
INSERT INTO dbo.busquedas VALUES ('admin',	'nombre: pepe4',	0,	2015-12-25, '' 	)