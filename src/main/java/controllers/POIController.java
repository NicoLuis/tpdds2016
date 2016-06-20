package controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import org.uqbar.geodds.Point;

import poi.*;
import bases.*;

public class POIController {
	
	BasePOIs basepoi = new BasePOIs();

	public ModelAndView nuevo(Request request, Response response) {
		return new ModelAndView(null, "POIs.hbs");
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
		boolean estaCerca = poi_2.estaCercaDe(poi_1.getUbicacion());
		
		enviar(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
		
		} catch (IllegalStateException e) {
			response.redirect("/POIs/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/POIs/Invalido");
		}
		
		response.redirect("/POIs/Invalido");
		
		return null;
	}
	
	public ModelAndView distancia(Request request, Response response) {
		return new ModelAndView(null, "distancia.hbs");
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
			boolean estaCerca = poi_2.estaCercaDe(poi_1.getUbicacion());
			
			enviar(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
			
		} catch (IllegalStateException e) {
			response.redirect("/POIs/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/POIs/Invalido");
		}
		
		response.redirect("/POIs/Invalido");
		
		return null;
	}
	
	
	
	
	public POI tomarDatos(Request request, String nombre, String direccion, String ubicacionX, String ubicacionY, boolean tieneTipoDos){
		String nom = request.queryParams(nombre);
		String drc = request.queryParams(direccion);
		Point unaUbicacion = 
				new Point(	Float.parseFloat(request.queryParams(ubicacionX)), 
							Float.parseFloat(request.queryParams(ubicacionY)));
		
		POI poi = new POI();	
		if(tieneTipoDos == true){
		switch(request.queryParams("tipo2")){
			case "Parada de colectivos": 
				poi = new ParadaColectivo(); break;
			case "Centro de Gestión y Participación": 
				poi = new CGP();			 break;
			case "Sucursal de Banco": 
				poi = new SucursalBanco(); 	 break;
			case "Local comercial": 
				poi = new LocalComercial();  break;
		}}
	
		
		poi.setNombre(nom);
		poi.setDireccion(drc);
		poi.setUbicacion(unaUbicacion);
		return poi;
	}
	
	public POI tomarDatosConocidos(Request request){
		POI poi = new POI();
		switch(request.queryParams("poi2")){
			case "ubicacionCercana": poi = basepoi.crear_ubicacionCercana() ; break;
			case "ubicacionLejana": poi = basepoi.crear_ubicacionLejana() ; break;
			case "CGP_1": poi = basepoi.crear_CGP_1() ; break;
			case "CGP_2": poi = basepoi.crear_CGP_2() ; break;
			case "Parada del 47": poi = basepoi.crear_paradaDel47() ; break;
			case "Parada del 107": poi = basepoi.crear_paradaDel107() ; break;
			case "Parada del 114": poi = basepoi.crear_paradaDel114() ; break;
			case "Sucursal Banco": poi = basepoi.crear_SucursalBanco_1() ; break;
			case "Libreria Escolar": poi = basepoi.crear_libreriaEscolar_1() ; break;
			case "Kiosko de Diarios": poi = basepoi.crear_kioskoDeDiarios_1() ; break;
		}
		return poi;
	}
	
	
	public BigDecimal redondear(POI poi_1, POI poi_2){
		BigDecimal bigDecimal = new BigDecimal( poi_1.getUbicacion().distance(poi_2.getUbicacion())*1000 );
		return bigDecimal.setScale(0, RoundingMode.HALF_UP);
	}
	
	public void enviar(BigDecimal distanciaRedondeada, POI poi_1, POI poi_2, boolean estaCerca, Response response){
		String str = "/POIs/Distancia?distancia=" + distanciaRedondeada + 
				"&nom=" + poi_1.getNombre() +
				"&drc=" + poi_1.getDireccion() +
				"&nom2=" + poi_2.getNombre() +
				"&drc2=" + poi_2.getDireccion() +
				"&c=" + estaCerca;
		if( poi_1.esPOIValido() && poi_2.esPOIValido() )	response.redirect(str);
	}
	
	public void enviar(POI poi, boolean disponible, Response response){
		String str = "/POIs/Disponible?disp=" + disponible +
				"&nom2=" + poi.getNombre() +
				"&drc2=" + poi.getDireccion();
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