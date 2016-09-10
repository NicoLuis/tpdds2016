/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import bases.Busqueda;
import bases.RepoBusquedas;

import reportes.ReportePorTerminal;


public class ReporteUsuarioController {
    
     public ModelAndView reporteUsuario(Request request, Response response) {
		return new ModelAndView(null, "TotalizarUsuario.hbs");
	}
	
	public ModelAndView buscarEnHistorialUsuario(Request request, Response response) {
		ResultSet rs;
		List<Busqueda> busquedas = RepoBusquedas.GetInstancia().getListaBusqueda();
		RepoBusquedas.GetInstancia().setListaBusqueda("vacia");
		try{
			String queryBusquedas =  "SELECT * FROM busquedas ORDER BY usuario";
			Statement st = UsuarioController.GetInstancia().getConexion().getConexion().createStatement();
			rs = st.executeQuery( queryBusquedas );
				if(!rs.equals(null)){
				    int i=0;
					while(rs.next()){
						String fecha = rs.getString("fechayhora");
						org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
						DateTime time = format.parseDateTime(fecha);
						String usuario = rs.getString("usuario");
						String parametros = rs.getString("parametros");
						int cantresultados = rs.getInt("cantresultados");
						String poisresultados = rs.getString("poisresultado");
						Busqueda bus = new Busqueda(time, usuario, parametros, cantresultados, poisresultados);
						RepoBusquedas.GetInstancia().addBusqueda(bus);
						busquedas = RepoBusquedas.GetInstancia().getListaBusqueda();
						}
					}     
			}
			catch(SQLException e){
				e.printStackTrace();
			}
        List<Busqueda> busquedasOrdenadas = ordenarBusquedas(busquedas);
        ReportePorTerminal reporte = new ReportePorTerminal();        
        Busqueda busquedaAnterior = busquedasOrdenadas.get(0);
		int cantResultados = 0;
		Iterator<Busqueda> iterator = busquedasOrdenadas.listIterator();
		while (iterator.hasNext()) {
			Busqueda actual = iterator.next();
			if (actual.mismaTerminal(busquedaAnterior)) {
				cantResultados = cantResultados + actual.getCantResultados();
			} else {
				reporte.agregarEntrada(busquedaAnterior.getUsuario(), cantResultados);
				cantResultados = actual.getCantResultados();
				busquedaAnterior = actual;
			}
		}
		reporte.agregarEntrada(busquedaAnterior.getUsuario(), cantResultados);               
		String str = construirStringLista(reporte.getInfoReporte());
		response.redirect(str);	
		return null;          
	}
	
	//public ModelAndView generarReporteUsuario(Request request, Response response) {
	//	ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
	//	String str = construirStringLista(lista);
	//	response.redirect(str);
	//	return null;
	//}
	
	public String construirStringLista(List<ReportePorTerminal.Entrada> lista) {
		String str = "/reportarUsuario?cantidadFilas=" + lista.size();
                
		for (ReportePorTerminal.Entrada busqueda : lista) {
			str = str + "&" + "usuario="  + busqueda.nombreUsuario + "&"
			+ "cantidadBusquedas=" + busqueda.cantResultados;			
		}
		return str;
	}
        
        
        public List<Busqueda> ordenarBusquedas(List<Busqueda> busquedas) {
		Collections.sort(busquedas, new Comparator<Busqueda>() {
			public int compare(Busqueda one, Busqueda other) {
				return one.getUsuario().compareTo(other.getUsuario());
			}
		});
		return busquedas;
	}
    
    
    
}
