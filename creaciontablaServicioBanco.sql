USE [dds2016]
GO

/****** Object:  Table [dbo].[serviciobanco]    Script Date: 12/09/2016 14:55:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
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

ALTER TABLE [dbo].[serviciobanco]  WITH CHECK ADD  CONSTRAINT [FK_serviciobanco_poi] FOREIGN KEY([banco])
REFERENCES [dbo].[poi] ([nombrepoi])
GO

ALTER TABLE [dbo].[serviciobanco] CHECK CONSTRAINT [FK_serviciobanco_poi]
GO


INSERT INTO [dbo].[serviciobanco] VALUES ('Sucursal Banco BBVA', 'Asesoramiento')
INSERT INTO [dbo].[serviciobanco] VALUES ('Sucursal Banco BBVA', 'Asesoramiento')
