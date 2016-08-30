package controllers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import poi.*;
import bases.*;

public class POIController {
	String usuarioLogueado;
	ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
	
	public ModelAndView opciones(Request request, Response response) {
		chequearUsuario(response);
		if( RepoTerminales.GetInstancia().getBooleanAdmin() )
			return new ModelAndView(null, "MenuAdmin.hbs");
		return new ModelAndView(null, "MenuUser.hbs");
	}
	
	public ModelAndView historial(Request request, Response response) {
		return new ModelAndView(null, "resultadoHistorial.hbs");
	}
	
	public ModelAndView buscarEnHistorial(Request request, Response response) {
		
		String usuario = request.queryParams("usuario");
		String fecha1String = request.queryParams("fecha1");
		String fecha2String = request.queryParams("fecha2");
		
		List<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
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
		lista = RepoBusquedas.GetInstancia().getListaBusqueda();
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
	public ModelAndView generarDetalles(Request request, Response response) {
		ArrayList<POI> lista= HomePois.GetInstancia().getListaPois();
		String str = construirListaDetalles(lista);
		response.redirect(str);
		return null;
	}
	
	public String construirListaDetalles(ArrayList<POI> lista){
		String str= "paginaBusqueda?cantidadFilas="+lista.size();
		for(POI poi : lista){
			ArrayList <String> detalles = poi.get_detalles();
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
	public ModelAndView calcularDistancia(Request request, Response response) {
		return new ModelAndView(null, "calcularDistancia.hbs");
	}
	public ModelAndView calcularDistanciaAPOI(Request request, Response response) {
		return new ModelAndView(null, "calcularDistanciaAPOI.hbs");
	}
	public ModelAndView calcularDistanciaEntre2PoisDados(Request request, Response response) {
		try {
		
		POI poi_1 = tomarDatosPoiNuevo(request, "nombre", "direccion", "ubicacionX", "ubicacionY", false);
		POI poi_2 = tomarDatosPoiNuevo(request, "nombre2", "direccion2", "ubicacionX2", "ubicacionY2", true);
		
		BigDecimal distanciaRedondeada = redondear(poi_1, poi_2);
		boolean estaCerca = poi_2.estaCerca(poi_1.getCoordenadas());
		
		dirigirAResultadoDistancia(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
		
		} catch (IllegalStateException e) {
			response.redirect("/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/Invalido");
		}
		
		response.redirect("/Invalido");
		
		return null;
	}
	
	public ModelAndView resultadoDistancia(Request request, Response response) {
		return new ModelAndView(null, "resultadoDistancia.hbs");
	}
  
	public ModelAndView invalido(Request request, Response response) {
		System.out.println("DATOS NO VALIDOS");
		return new ModelAndView(null, "error.hbs");
	}
	public ModelAndView invalidaSesion(Request request, Response response){
		return new ModelAndView(null, "errorBusqueda.hbs");
	}
	
	public ModelAndView valido(Request request, Response response) {
		try{
			ArrayList<Terminal> lista = RepoTerminales.GetInstancia().getListaTerminales();
			String usuario = request.queryParams("usuario");
			String pass = request.queryParams("contrasenia");
			for(int i = 0; i < lista.size(); i++){
				if((lista.get(i).getusuario().equals(usuario))&& lista.get(i).getpass().equals(pass)){
					this.usuarioLogueado = usuario;
					if(lista.get(i).getAdmin() == true){
						System.out.println("Ingreso Sesion VALIDO Administrador");
						RepoTerminales.GetInstancia().setBooleanAdmin(true);
						return new ModelAndView(null, "layoutSesion.hbs");
					}else{
						System.out.println("Ingreso Sesion Usuario");
						RepoTerminales.GetInstancia().setBooleanAdmin(false);
						return new ModelAndView(null, "layoutSesion.hbs");
					}
					
				}
			}
		}catch (IllegalStateException e){
			response.redirect("/InvalidoSesion");
		}catch(NumberFormatException e){
			response.redirect("/InvalidoSesion");
		}
		
		response.redirect("/InvalidoSesion");
		return null;
		
	}
	
	public ModelAndView calculoDeDistanciaAPOI(Request request, Response response) {
		try {
			
			POI poi_1 = tomarDatosPoiNuevo(request, "nombre", "direccion", "ubicacionX", "ubicacionY", false);
			POI poi_2 = tomarDatosDeHomePois(request);
			
			BigDecimal distanciaRedondeada = redondear(poi_1, poi_2);
			boolean estaCerca = poi_2.estaCerca(poi_1.getCoordenadas());
			
			dirigirAResultadoDistancia(distanciaRedondeada, poi_1, poi_2, estaCerca, response);
			
		} catch (IllegalStateException e) {
			response.redirect("/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/Invalido");
		}
		
		response.redirect("/Invalido");
		
		return null;
	}
	
	
	
	
	public POI tomarDatosPoiNuevo(Request request, String nombre, String direcc, String ubicacionX, String ubicacionY, boolean tieneTipoDos){
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
	
	public POI tomarDatosDeHomePois(Request request){
		HomePois basepoi = HomePois.GetInstancia();
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
	
	public void dirigirAResultadoDistancia(BigDecimal distanciaRedondeada, POI poi_1, POI poi_2, boolean estaCerca, Response response){
		String str = "/POIs/resultadoDistancia?distancia=" + distanciaRedondeada + 
				"&nom=" + poi_1.getNombre() +
				"&drc=" + poi_1.getDireccion().getCalle() +
				"&nom2=" + poi_2.getNombre() +
				"&drc2=" + poi_2.getDireccion() +
				"&c=" + estaCerca;
		if( poi_1.esPOIValido() && poi_2.esPOIValido() )	response.redirect(str);
	
	}	
	public void dirigirAResultadoDisponibilidad(POI poi, boolean disponible, Response response){
		String str = "/POIs/resultadoDisponibilidad?disp=" + disponible +
				"&nom2=" + poi.getNombre() +
				"&drc2=" + poi.getDireccion().getCalle();
		if( poi.esPOIValido() )	response.redirect(str);
	}
	public ModelAndView calcularDisponibilidad(Request request, Response response) {
		try {
			
			POI poi = tomarDatosDeHomePois(request);
			
			LocalDateTime time = 
					LocalDateTime.of(	Integer.parseInt(request.queryParams("año")),
										Integer.parseInt(request.queryParams("mes")),
										Integer.parseInt(request.queryParams("dia")),
										Integer.parseInt(request.queryParams("hora")),
										Integer.parseInt(request.queryParams("minutos")), 0, 0);
			
			
			dirigirAResultadoDisponibilidad(poi, poi.estaDisponible(time), response);
			
			
		} catch (IllegalStateException e) {
			response.redirect("/Invalido");
		} catch (NumberFormatException e) {
			response.redirect("/Invalido");
		}
		
		response.redirect("/Invalido");
		
		return null;
	}
	
	public ModelAndView disponible(Request request, Response response) {
		return new ModelAndView(null, "disponible.hbs");
	}
	public ModelAndView busqueda(Request request, Response response) {
		chequearUsuario(response);
		return new ModelAndView(null, "busquedaPOI.hbs");
	}
	public ModelAndView buscar(Request request, Response response) {
		chequearUsuario(response);
		String nombre = request.queryParams("nombre");
		if(nombre != null && nombre != ""){
			ArrayList<POI> listaFiltrada = new ArrayList<POI>();
			HomePois basepoi = HomePois.GetInstancia();
			basepoi.crear_arrayPOIs();
			ArrayList<POI> lista = basepoi.getListaPois();
			for(int i = 0; i < lista.size(); i++){
				POI nuevo = lista.get(i);
				if(nuevo.resultadosDeBusquedaLibre(nombre) != null){
					listaFiltrada.add(nuevo);
				}
			}
			if(listaFiltrada.size()> 0){
				String str = "/paginaBusqueda?cantidadFilas=" + listaFiltrada.size();
				String listaPois = "";
				for(int i = 0; i < listaFiltrada.size(); i++){
						str = str + "&nombre=" + listaFiltrada.get(i).getNombre() +
						"&direccion=" + listaFiltrada.get(i).getDireccion().getCalle() +" "+ listaFiltrada.get(i).getDireccion().getNumero();
						if(i!=0) listaPois = listaPois + ", ";
						listaPois = listaPois + listaFiltrada.get(i).getNombre();
				}
				Busqueda busque = new Busqueda(DateTime.now(), usuarioLogueado, nombre, listaFiltrada.size(), listaPois);
				this.lista = RepoBusquedas.GetInstancia().getListaBusqueda();
				this.lista.add(busque);
				response.redirect(str);
				return null;
			}
			else{
				Busqueda busque = new Busqueda(DateTime.now(), usuarioLogueado, nombre, 0, "");
				this.lista = RepoBusquedas.GetInstancia().getListaBusqueda();
				this.lista.add(busque);
				System.out.println("No se encontro ningun poi");
				response.redirect("/paginaBusqueda?cantidadFilas=0");
				return null;
			}
		}else{
			System.out.println("Ingrese un nombre correcto");
			response.redirect("/Invalido");
			return null;
		}
	}
	public ModelAndView nuevaAccion(Request request, Response response) {
		return new ModelAndView(null, "configurarAcciones.hbs");
	}
	public ModelAndView resultadoDisponibilidad(Request request, Response response) {
		return new ModelAndView(null, "resultadoDisponibilidad.hbs");
	}
	
	public void chequearUsuario(Response response) {
		if(usuarioLogueado == null) response.redirect("/");
	}
}