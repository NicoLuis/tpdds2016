USE [dds2016]
GO

/****** Object:  Table [dbo].[servicioCGP]    Script Date: 12/09/2016 14:52:28 ******/
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

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[servicioCGP]  WITH CHECK ADD FOREIGN KEY([nombreCGP])
REFERENCES [dbo].[cgp] ([nombrecgp])
GO

INSERT INTO [dbo].[servicioCGP] VALUES ('CGP_1', 'Rentas')
INSERT INTO [dbo].[servicioCGP] VALUES ('CGP_2', 'Asesoramiento')

INSERT INTO [dbo].[servicioCGP] VALUES ('CGP_2', 'Atencion al cliente')

