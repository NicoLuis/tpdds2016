package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccionesController {
	
	public ModelAndView cargarAccionesEnBase(Request request, Response response){
		String accionesURL= request.queryString();	//	Pedia un string literal "acciones" inexistente, solo se le pasan varios "accion"

		List<String> acciones = FiltrarAcciones(accionesURL);
		System.out.println(acciones);

		
		return new ModelAndView(null, "MenuAdmin.hbs");
	//	response.redirect("/configurarAcciones");
			/*try{
			Conexion con = new Conexion();
			Statement stmt = con.getConexion().createStatement();
			for(int i=0;i<acciones.length;i++){
			String agregandoAcciones = " insert ignore into accionesXusuario "
						 				+ "(nombreUsuario, accion) "
										+ "(UsuarioController.GetInstancia().usuarioLogueado, acciones[i])";
			}
		*/
			
	}

	public int obtenerCantAcciones(String unString){
		return Integer.parseInt(unString.substring(16, 17));
	}
	
	public List<String> FiltrarAcciones(String string)
    {
		String[] array = string.split("&");
		List<String> acciones = new ArrayList<String>();
		
		for (String value : array) {
			if(value.substring(0, 6).equals("accion"))
				acciones.add(value.substring(7).replaceAll("%20", " "));
		}
		return acciones;
    }

}
