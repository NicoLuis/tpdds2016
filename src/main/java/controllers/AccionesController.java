package controllers;

import java.util.Arrays;
import java.util.List;

import java.sql.SQLException;
import java.sql.Statement;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccionesController {
	
	public ModelAndView cargarAccionesEnBase(Request request, Response response){
		String accionesURL= request.queryString();	
		//List<String> acciones =FiltrarAcciones(accionesURL);
		String acciones= accionesURL.toString();		
		List<String> accionesAr = Arrays.asList(acciones.replaceAll("%2C", ",").replaceAll("acciones=","").replaceAll("\\+"," ").split(","));
		Conexion myconex2= new Conexion();
		Statement st2 = null;		
				
			try{
				st2= myconex2.getConexion().createStatement();
					
				String query= "delete from accionXusuario where nombreUsuario='" + UsuarioController.GetInstancia().usuarioLogueado + "'";
				st2.execute(query);
				
				String query2="";
				if(busqueda("Totalizar por Fecha", accionesAr)){
					query2 = "insert into accionXusuario values ('" + UsuarioController.GetInstancia().usuarioLogueado + "','Totalizar por Fecha')";
					st2.execute(query2);
				}
				if(busqueda("Generar Log", accionesAr)){
					query2 = "insert into accionXusuario values ('" + UsuarioController.GetInstancia().usuarioLogueado + "','Generar Log')";
					st2.execute(query2);
				}
				if(busqueda("Totalizar por Usuario", accionesAr)){
					query2 = "insert into accionXusuario values ('" + UsuarioController.GetInstancia().usuarioLogueado + "','Totalizar por Usuario')";
					st2.execute(query2);
				}
				System.out.println("Se agregaron las acciones a la base");	
				if (st2 != null) {
					st2.close();
					if (myconex2 != null) {
						myconex2.getConexion().close();
					}
				}
			}
			catch(SQLException e2){ 
				e2.printStackTrace();
				return new ModelAndView(null, "layoutError.hbs");
			}
				
		return new ModelAndView(null, "configurarAcciones.hbs");	
	}
	
	public int obtenerCantAcciones(String unString){
		return Integer.parseInt(unString.substring(16, 17));
	}
	
	public boolean busqueda(String str2, List<String> myList){
		
		for(String str: myList) {
		    if(str.contains(str2))
		       return true;
		}
		return false;
	}
	
	public ModelAndView borrarAcciones(Request request, Response response){
				
		try{
			Conexion myconex= new Conexion();	
			Statement st;
			st = myconex.getConexion().createStatement();
			String query= "delete from accionXusuario where nombreUsuario='" + UsuarioController.GetInstancia().usuarioLogueado + "'";
			st.execute(query);
			System.out.println("Se eliminaron las acciones de la base");	
			if (st != null) {
					st.close();
				if (myconex != null) {
			myconex.getConexion().close();
				}	
			}
		}catch(SQLException e){ e.printStackTrace(); 
			return new ModelAndView(null, "layoutError.hbs");
		}
		return new ModelAndView(null, "configurarAcciones.hbs");	
				
	}
		
//	public List<String> FiltrarAcciones(String string)
//    {
//		String[] array = string.split(",");
//		ArrayList<String> acciones = new ArrayList<String>();
//		
//		for (String value : array) {
//			if(value.substring(0, 6).equals("accion"))
//				acciones.add(value.substring(7).replaceAll("%2C", ",").replaceAll("s=","").replaceAll("\\+"," "));
//				value.split(",");
//				
//		}
//		return acciones;
//    }
	
}
