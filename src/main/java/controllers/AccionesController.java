package controllers;

import javax.swing.JOptionPane;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AccionesController {
	
	public ModelAndView cargarAccionesEnBase(Request request, Response response){
		String accionesURL= request.queryParams("acciones");
		String[] acciones = accionesURL.replaceAll("\\+"," ").replaceAll("%2C",",").split(" , ");
		System.out.println(accionesURL);
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

}
