package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import bases.Busqueda;
import bases.RepoBusquedas;


public class HistorialController {

	public ModelAndView historial(Request request, Response response) {
		return new ModelAndView(null, "resultadoHistorial.hbs");
	}
	
	@SuppressWarnings("null")
	public ModelAndView buscarEnHistorial(Request request, Response response) {
		ResultSet rs;
		List<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
		RepoBusquedas.GetInstancia().setListaBusqueda("vacia");
		try{
			String queryBusquedas =  "SELECT * FROM busquedas";
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
						lista = RepoBusquedas.GetInstancia().getListaBusqueda();
						}
					}     
			}
			catch(SQLException e){
				e.printStackTrace();
			}

		String usuario = request.queryParams("usuario");
		String fecha1String = request.queryParams("fecha1");
		String fecha2String = request.queryParams("fecha2");
		
		
		lista = lista.stream().filter(busq -> busq.getUsuario().contains(usuario)).collect(Collectors.toList());
		
		lista = lista.stream().filter(busq -> busq.getFechaYhora().isBeforeNow()).collect(Collectors.toList());
		
		if(fecha1String.length() == 10){
			DateTime fecha1 = convertirEnDateTime(fecha1String);
			System.out.println(fecha1String);
			System.out.println(fecha1);
			lista = lista.stream().filter(busq -> busq.getFechaYhora().isAfter(fecha1)).collect(Collectors.toList());
		}
		
		if(fecha2String.length() == 10){
			DateTime fecha2 = convertirEnDateTime(fecha2String);
			System.out.println(fecha2String);
			System.out.println(fecha2);
			lista = lista.stream().filter(busq -> busq.getFechaYhora().isBefore(fecha2)).collect(Collectors.toList());
		}
		
		if(lista.size() == RepoBusquedas.GetInstancia().getListaBusqueda().size()) 
			response.redirect("/historialBusquedas");
		
		String str = construirStringLista(lista);
		response.redirect(str);
		return null;
	}
	
	public ModelAndView generarHistorial(Request request, Response response) {
		ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
		String str = construirStringLista(lista);
		response.redirect(str);
		return null;
	}
	
	public String construirStringLista(List<Busqueda> lista) {
		String str = "/historialBusquedas?cantidadFilas=" + lista.size();
		for (Busqueda busqueda : lista) {
			str = str + "&" + "fecha="  + construirStringFechaYHora(busqueda.getFechaYhora()) + "&"
			+ "usuario=" + busqueda.getUsuario() + "&"
			+ "parametros="  + busqueda.getParametros()  + "&"
			+ "pois=" + busqueda.getCantResultados()  + "&"
			+ "listaPois=" + busqueda.getNombrePoisResultado();
		}
		return str;
	}
	
	public DateTime convertirEnDateTime(String stringFecha) {
		
		stringFecha = stringFecha.trim();
		
		int anio = Integer.parseInt(stringFecha.substring(6, 10));
		int mes = Integer.parseInt(stringFecha.substring(3, 5));
		int dia = Integer.parseInt(stringFecha.substring(0, 2));
		
		return new DateTime(anio, mes, dia, 0, 0);
	}
	
	public String construirStringFechaYHora(DateTime fechaYHora) {
		String fechaString = fechaYHora.getDayOfMonth() + "/" + fechaYHora.getMonthOfYear() + "/" + fechaYHora.getYear();
		String horaString = fechaYHora.getHourOfDay() + "";
			if(fechaYHora.getMinuteOfHour() < 10) horaString = horaString + ":0" + fechaYHora.getMinuteOfHour();
			else horaString = horaString + ":" + fechaYHora.getMinuteOfHour();
		return fechaString + " " + horaString;
	}
	
	
}