package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
	public Conexion(){
		try
        {
			//Si les falla aca porque todavia no configuraron sql server Comenten esta parte!!!!!
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

            String userName = "sa";
            String password = "gestiondedatos";
            String url = "jdbc:sqlserver://localhost\\SQLSERVER2012;DatabaseName=dds2016";
            Connection con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conecte bien");
            //String result = new result[20];
            Statement st;
            ResultSet rs;
            st = con.createStatement();
            rs = st.executeQuery("SELECT TOP 3 [nombrepoi],[direccion],[coordenada_x],[coordenada_y]  FROM [dds2016].[dbo].[poi]");
            while (rs.next())
                System.out.println (rs.getString("nombrepoi"));   // Print col 1
            st.close();
        } catch (Exception e)
        {
        	System.out.println("Error");
            e.printStackTrace();
        }

	}
}
