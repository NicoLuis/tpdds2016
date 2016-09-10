/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
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
import reportes.ReportePorFecha;




public class ReporteFechaController {
    
    public ModelAndView reporteFecha(Request request, Response response) {
		return new ModelAndView(null, "TotalizarFecha.hbs");
	}
	
	public ModelAndView buscarEnHistorialFechas(Request request, Response response) {
		ResultSet rs;
		List<Busqueda> listaF = RepoBusquedas.GetInstancia().getListaBusqueda();
		RepoBusquedas.GetInstancia().setListaBusqueda("vacia");
		try{
			String queryBusquedas =  "SELECT * FROM busquedas ORDER BY fechayhora";
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
						listaF = RepoBusquedas.GetInstancia().getListaBusqueda();
						}
					}     
			}
			catch(SQLException e){
				e.printStackTrace();
			}

        ReportePorFecha reporte = new ReportePorFecha();
                
        DateTime fechaAnterior = listaF.get(0).getFechaYhora();
		int cantBusquedas = 0;
		Iterator<Busqueda> iterator = listaF.listIterator();
		while (iterator.hasNext()) {
			// Consigo el proximo elemento
			Busqueda actual = iterator.next();

			// si la fecha coincide incremento, sino, pase a otra fecha: agrego
			// la entrada y vuelvo a 0 la cantBusquedas
			if (actual.mismaFecha(fechaAnterior)) {
				cantBusquedas++;
			} else {
				reporte.agregarEntrada(fechaAnterior, cantBusquedas);
				cantBusquedas = 1;// cambie a cero por uno
				fechaAnterior = actual.getFechaYhora();
			}
		}
		// quedo una fecha sin agregar
		reporte.agregarEntrada(fechaAnterior, cantBusquedas);
               
                
		String str = construirStringLista(reporte.getInfoReporte());
		response.redirect(str);	
	
                
               
		return null;
                
                
                
	}
	
	//public ModelAndView generarReporte(Request request, Response response) {
	//	ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
	//	String str = construirStringLista(lista);
	//	response.redirect(str);
	//	return null;
	//}
	
	public String construirStringLista(List<ReportePorFecha.Entrada> lista) {
		String str = "/reportarFecha?cantidadFilas=" + lista.size();
                
		for (ReportePorFecha.Entrada busqueda : lista) {
			str = str + "&" + "fecha="  + construirStringFechaYHora(busqueda.fecha) + "&"
			+ "cantidadBusquedas=" + busqueda.cantBusquedas;			
		}
		return str;
	}
	

	
	public String construirStringFechaYHora(DateTime fechaYHora) {
		String fechaString = fechaYHora.getDayOfMonth() + "/" + fechaYHora.getMonthOfYear() + "/" + fechaYHora.getYear();
		String horaString = fechaYHora.getHourOfDay() + "";
			if(fechaYHora.getMinuteOfHour() < 10) horaString = horaString + ":0" + fechaYHora.getMinuteOfHour();
			else horaString = horaString + ":" + fechaYHora.getMinuteOfHour();
		return fechaString + " " + horaString;
	}
}
