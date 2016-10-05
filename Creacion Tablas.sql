USE [dds2016]
GO

/****** Object:  Table [dbo].[usuario]    Script Date: 05/10/2016 13:57:44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[usuario](
	[nombreusuario] [varchar](50) NOT NULL,
	[contrasenia] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[administrador] [bit] NULL,
 CONSTRAINT [PK_usuario] PRIMARY KEY CLUSTERED 
(
	[nombreusuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO



USE [dds2016]
GO

/****** Object:  Table [dbo].[poi]    Script Date: 05/10/2016 13:56:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[poi](
	[poi_id] [int] IDENTITY(1,1) NOT NULL,
	[direccion] [varchar](50) NULL,
	[coordenada_x] [numeric](12, 6) NULL,
	[coordenada_y] [numeric](12, 6) NULL,
	[tipo] [varchar](50) NOT NULL,
	[nombrepoi] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[poi_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO




USE [dds2016]
GO

/****** Object:  Table [dbo].[cgp]    Script Date: 05/10/2016 13:51:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[cgp](
	[cgp_id] int NOT NULL identity (1,1),
	[telefono] [numeric](10, 0) NULL,
	[horainicio] [varchar](50) NULL,
	[horafin] [varchar](50) NULL,
	[poi_id] [int] NULL,
	[nombreCGP] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[cgp_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

USE [dds2016]
GO

/****** Object:  Table [dbo].[servicioCGP]    Script Date: 05/10/2016 13:52:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[servicioCGP](
	[nombreCGP] [varchar](50) NOT NULL,
	[servicio] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[nombreCGP] ASC,
	[servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO




USE [dds2016]
GO

/****** Object:  Table [dbo].[accionXusuario]    Script Date: 05/10/2016 13:55:26 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[accionXusuario](
	[nombreUsuario] [varchar](50) NOT NULL,
	[accion] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[nombreUsuario] ASC,
	[accion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [dds2016]
GO

/****** Object:  Table [dbo].[busquedas]    Script Date: 05/10/2016 13:55:41 ******/
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


USE [dds2016]
GO

/****** Object:  Table [dbo].[localComercial]    Script Date: 05/10/2016 13:55:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[localComercial](
	[local_id] [int] NOT NULL,
	[nombre] [varchar](50) NULL,
	[ubicacion] [varchar](50) NULL,
	[rubro] [varchar](50) NULL,
	[poi_id] [int] NULL,
 CONSTRAINT [PK_localComercial] PRIMARY KEY CLUSTERED 
(
	[local_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[localComercial]  WITH CHECK ADD  CONSTRAINT [FK_localComercial_poi] FOREIGN KEY([poi_id])
REFERENCES [dbo].[poi] ([poi_id])
GO

ALTER TABLE [dbo].[localComercial] CHECK CONSTRAINT [FK_localComercial_poi]
GO

USE [dds2016]
GO

/****** Object:  Table [dbo].[rubro]    Script Date: 05/10/2016 13:56:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[rubro](
	[rubro_id] [int] NULL,
	[radio_cercania] [nchar](10) NULL,
	[rubrosQuePertenece] [varchar](50) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [dds2016]
GO

/****** Object:  Table [dbo].[serviciobanco]    Script Date: 05/10/2016 13:57:08 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO
USE [dds2016]
GO

/****** Object:  Table [dbo].[sucursalBanco]    Script Date: 05/10/2016 13:57:19 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[sucursalBanco](
	[banco_id] [int] IDENTITY(1,1) NOT NULL,
	[nombreGerente] [varchar](50) NULL,
	[nombrebanco] [varchar](50) NULL,
	[poi_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[banco_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO



CREATE TABLE [dbo].[serviciobanco](
	[banco] [varchar](50) NOT NULL,
	[servicio] [varchar](50) NOT NULL,
 CONSTRAINT [PK_serviciobanco] PRIMARY KEY CLUSTERED 
(
	[banco] ASC,
	[servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


