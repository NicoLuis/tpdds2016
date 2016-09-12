USE [dds2016]
GO

/****** Object:  Table [dbo].[cgp]    Script Date: 12/09/2016 14:49:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[cgp](
	[nombrecgp] [varchar](50) NOT NULL,
	[telefono] [numeric](10, 0) NULL,
	[horainicio] [varchar](50) NULL,
	[horafin] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[nombrecgp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[cgp]  WITH CHECK ADD FOREIGN KEY([nombrecgp])
REFERENCES [dbo].[poi] ([nombrepoi])
GO


INSERT INTO [dbo].[cgp] VALUES ('CGP_1', 49822157, '09:00', '20:00')
INSERT INTO [dbo].[cgp] VALUES ('CGP_2', 42014897, '08:00', '19:00')
