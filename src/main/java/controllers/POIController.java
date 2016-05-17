package main.java.controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import org.uqbar.geodds.Point;

import main.java.poi.*;
import main.java.bases.*;

public class POIController {
	
	

	public ModelAndView nuevo(Request request, Response response) {
	  return new ModelAndView(null, "POIs.hbs");
	}
  
	public ModelAndView arrancar(Request request, Response response) {
	  return new ModelAndView(null, "inicio.hbs");
	}
	
	public ModelAndView calcularDistancia(Request request, Response response) {
		try {
	
		POI poi_1 = new POI();
		POI poi_2 = new POI();
		
		String nom = request.queryParams("nombre");
		String drc = request.queryParams("direccion");
		String nom2 = request.queryParams("nombre2");
		String drc2 = request.queryParams("direccion2");
		
		poi_1.setNombre(nom);
		poi_2.setNombre(nom2);
		
		Float ubicacionX = Float.parseFloat(request.queryParams("ubicacionX"));
		Float ubicacionY = Float.parseFloat(request.queryParams("ubicacionY"));
		Float ubicacionX2 = Float.parseFloat(request.queryParams("ubicacionX2"));
		Float ubicacionY2 = Float.parseFloat(request.queryParams("ubicacionY2"));
		
		Point unaUbicacion = new Point(ubicacionX, ubicacionY);
		Point otraUbicacion = new Point(ubicacionX2, ubicacionY2);
		
		poi_1.setUbicacion(unaUbicacion);
		poi_2.setUbicacion(otraUbicacion);
		
		BigDecimal bigDecimal = new BigDecimal(unaUbicacion.distance(otraUbicacion)*1000);
		BigDecimal distanciaRedondeada = bigDecimal.setScale(0, RoundingMode.HALF_UP);
		
		String str = "/POIs/Distancia?distancia=" + distanciaRedondeada + 
				"&nom=" + nom +
				"&drc=" + drc +
				"&nom2=" + nom2 +
				"&drc2=" + drc2;
		
		if( poi_1.esPOIValido() && poi_2.esPOIValido() )	response.redirect(str);
		
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

}