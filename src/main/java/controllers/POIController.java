package controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import org.uqbar.geodds.Point;

import poi.*;
import bases.*;

public class POIController {
	
	HomePois basepoi = HomePois.GetInstancia();

	public ModelAndView nuevo(Request request, Response response) {
		return new ModelAndView(null, "POIs.hbs");
	}
	
	public ModelAndView historial(Request request, Response response) {
		return new ModelAndView(null, "historialBusqueda.hbs");
	}
	
	public ModelAndView resultadoHistorial(Request request, Response response) {
		
		
		ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
		String str = "/resultadoHistorial?cantidadFilas=" + lista.size();
		for (Busqueda busqueda : lista) {
			str = str + "&" + "fecha="  + busqueda.getFechaYhora()   + "&"
			+ "usuario=" + busqueda.getUsuario() + "&"
			+ "parametros="  + busqueda.getParametros()  + "&"
			+ "pois=" + busqueda.getCantResultados();
		}
		
		System.out.println(str);
		response.redirect(str);
		return null;
	}
  
	public ModelAndView calcularDistancia(Request request, Response response) {
		return new ModelAndView(null, "calcularDistancia.hbs");
	}
	public ModelAndView calcularDistanciaAPOI(Request request, Response response) {
		return new ModelAndView(null, "calcularDistanciaAPOI.hbs");
	}
	

	
	public ModelAndView calculoDeDistancia(Request request, Response response) {
		try {
		
		POI poi_1 = tomarDatos(request, "nombre", "direccion", "ubicacionX", "ubicacionY", false);
		POI poi_2 = tomarDatos(request, "nombre2", "direccion2", "ubicacionX2", "ubicacionY2", true);
		
		BigDecimal distanciaRedondeada = redondear(poi_1, poi_2);
		boolean estaCerca = poi_2.estaCerca(poi_1.getCoordenadas());
		
		enviar(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
		
		} catch (IllegalStateException e) {
			response.redirect("/POIs/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/POIs/Invalido");
		}
		
		response.redirect("/POIs/Invalido");
		
		return null;
	}
	
	public ModelAndView resultadoDistancia(Request request, Response response) {
		return new ModelAndView(null, "resultadoDistancia.hbs");
	}
  
	public ModelAndView invalido(Request request, Response response) {
		System.out.println("POI NO VALIDO");
		return new ModelAndView(null, "error.hbs");
	}
	
	
	public ModelAndView calculoDeDistanciaAPOI(Request request, Response response) {
		try {
			
			POI poi_1 = tomarDatos(request, "nombre", "direccion", "ubicacionX", "ubicacionY", false);
			POI poi_2 = tomarDatosConocidos(request);
			

			BigDecimal distanciaRedondeada = redondear(poi_1, poi_2);
			boolean estaCerca = poi_2.estaCerca(poi_1.getCoordenadas());
			
			enviar(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
			
		} catch (IllegalStateException e) {
			response.redirect("/POIs/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/POIs/Invalido");
		}
		
		response.redirect("/POIs/Invalido");
		
		return null;
	}
	
	
	
	
	public POI tomarDatos(Request request, String nombre, String direcc, String ubicacionX, String ubicacionY, boolean tieneTipoDos){
		String nom = request.queryParams(nombre);
		String direccion = request.queryParams(direcc);

		Point unaUbicacion = 
				new Point(	Float.parseFloat(request.queryParams(ubicacionX)), 
							Float.parseFloat(request.queryParams(ubicacionY)));
				
		POI poi;	
		if(tieneTipoDos == true){
		switch(request.queryParams("tipo2")){
			case "Parada de colectivos": 
				poi = new ParadaColectivo(); 
				poi.setNombre(nom);
				poi.setDireccion(new Direccion(direccion));
				poi.setUbicacion(unaUbicacion);				
				return poi;
			case "Centro de Gestión y Participación": 
				poi = new CGP();
				poi.setNombre(nom);
				poi.setDireccion(new Direccion(direccion));
				poi.setUbicacion(unaUbicacion);
				return poi;
			case "Sucursal de Banco": 
				poi = new Banco();
				poi.setNombre(nom);
				poi.setDireccion(new Direccion(direccion));
				poi.setUbicacion(unaUbicacion);
				return poi;
			case "Local comercial": 
				poi = new LocalComercial();
				poi.setNombre(nom);
				poi.setDireccion(new Direccion(direccion));
				poi.setUbicacion(unaUbicacion);
				return poi;
		}}else{
			poi = new ParadaColectivo(); 
			poi.setNombre(nom);
			poi.setDireccion(new Direccion(direccion));
			poi.setUbicacion(unaUbicacion);	
			return poi;
		}
		
		return null;
	}
	
	public POI tomarDatosConocidos(Request request){
		switch(request.queryParams("poi2")){
			case "ubicacionCercana": return basepoi.crear_ubicacionCercana() ; 
			case "ubicacionLejana": return basepoi.crear_ubicacionLejana() ; 
			case "CGP_1": return basepoi.crear_CGP_1() ; 
			case "CGP_2": return basepoi.crear_CGP_2() ; 
			case "Parada del 47": return basepoi.crear_paradaDel47() ;
			case "Parada del 107": return basepoi.crear_paradaDel107() ; 
			case "Parada del 114": return basepoi.crear_paradaDel114() ; 
			case "Sucursal Banco": return basepoi.crear_SucursalBanco_1() ; 
			case "Libreria Escolar": return basepoi.crear_libreriaEscolar_1() ; 
			case "Kiosko de Diarios": return basepoi.crear_kioskoDeDiarios_1() ; 
			default: return null;
		}
	}
	
	
	public BigDecimal redondear(POI poi_1, POI poi_2){
		BigDecimal bigDecimal = new BigDecimal( poi_1.getCoordenadas().distance(poi_2.getCoordenadas())*1000 );
		return bigDecimal.setScale(0, RoundingMode.HALF_UP);
	}
	
	public void enviar(BigDecimal distanciaRedondeada, POI poi_1, POI poi_2, boolean estaCerca, Response response){
		String str = "/POIs/resultadoDistancia?distancia=" + distanciaRedondeada + 
				"&nom=" + poi_1.getNombre() +
				"&drc=" + poi_1.getDireccion().getCalle() +
				"&nom2=" + poi_2.getNombre() +
				"&drc2=" + poi_2.getDireccion() +
				"&c=" + estaCerca;
		if( poi_1.esPOIValido() && poi_2.esPOIValido() )	response.redirect(str);
	
	}
	
	
	
	
	
	public void enviar(POI poi, boolean disponible, Response response){
		String str = "/POIs/Disponible?disp=" + disponible +
				"&nom2=" + poi.getNombre() +
				"&drc2=" + poi.getDireccion().getCalle();
		if( poi.esPOIValido() )	response.redirect(str);
	}
	
	
	
	
	
	
	
	
	
	public ModelAndView calcularDisponibilidad(Request request, Response response) {
		try {
			
			POI poi = tomarDatosConocidos(request);
			
			LocalDateTime time = 
					LocalDateTime.of(	Integer.parseInt(request.queryParams("año")),
										Integer.parseInt(request.queryParams("mes")),
										Integer.parseInt(request.queryParams("dia")),
										Integer.parseInt(request.queryParams("hora")),
										Integer.parseInt(request.queryParams("minutos")), 0, 0);
			
			
			enviar(poi, poi.estaDisponible(time), response);
			
			
		} catch (IllegalStateException e) {
			response.redirect("/POIs/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/POIs/Invalido");
		}
		
		response.redirect("/POIs/Invalido");
		
		return null;
	}
	
	public ModelAndView disponible(Request request, Response response) {
		return new ModelAndView(null, "disponible.hbs");
	}
	
	public ModelAndView disponibilidad(Request request, Response response) {
		return new ModelAndView(null, "disponibilidad.hbs");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}