package controllers;
import java.math.BigDecimal;
import java.util.*;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import poi.*;
import sistExternos.Encryptor;
import bases.*;

public class POIController {
	ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
	
			
	public void chequearUsuario(Response response) {
		String usuarioLogueado = UsuarioController.GetInstancia().getUsuarioLogueado();
		if(usuarioLogueado == null) response.redirect("/");
	}
	
	
	public ModelAndView opciones(Request request, Response response) {
		chequearUsuario(response);
		if( RepoTerminales.GetInstancia().getBooleanAdmin() )
			return new ModelAndView(null, "MenuAdmin.hbs");
		return new ModelAndView(null, "MenuUser.hbs");
	}
	

	/*
	 * 
	 * 
	 * 
	 * -----		DETALLES		------
	 * 
	 * 
	 * 
	 */
	
	public ModelAndView generarDetalles(Request request, Response response) {
		System.out.println(request);
		String nombre_poi=request.queryParams("nombre");
		String direccion_poi=request.queryParams("direccion");
		ArrayList<POI> lista= HomePois.GetInstancia().getListaPois();
		ArrayList<POI> lista_coincidencias= new ArrayList<POI>();
		for (POI poi: lista){
			if(poi.getNombre().contains(nombre_poi)){
				System.out.println("HOLAAAAAAAAAAAAAAAAAAAA");
				lista_coincidencias.add(poi);
			}
		}
		String str = construirListaDetalles(lista_coincidencias);
		response.redirect(str);
		return null;
	}
	
	public String construirListaDetalles(ArrayList<POI> lista){
		String str= "paginaBusqueda?cantidadFilas="+lista.size();
		Map<String,String> detalles= new HashMap <String,String>();
		for(POI poi : lista){
			poi.setDetalles();
			detalles = poi.get_detalles();
		}

	 for (Map.Entry<String, String> detalle : detalles.entrySet()) {
	 str =str + "&"+ detalle.getKey() +"=" +detalle.getValue();
	 System.out.println("Key : " + detalle.getKey() + " Value : " + detalle.getValue());
	}
		return str;
}
	
	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * -----		DISTANCIA		------
	 * 
	 * 
	 * 
	 */
	
	public ModelAndView calcularDistancia(Request request, Response response) {
		return new ModelAndView(null, "calcularDistancia.hbs");
	}
	public ModelAndView calcularDistanciaAPOI(Request request, Response response) {
		return new ModelAndView(null, "calcularDistanciaAPOI.hbs");
	}
	
	public ModelAndView resultadoDistancia(Request request, Response response) {
		return new ModelAndView(null, "resultadoDistancia.hbs");
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
	
	public void dirigirAResultadoDistancia(BigDecimal distanciaRedondeada, POI poi_1, POI poi_2, boolean estaCerca, Response response){
		String str = "/POIs/resultadoDistancia?distancia=" + distanciaRedondeada + 
				"&nom=" + poi_1.getNombre() +
				"&drc=" + poi_1.getDireccion().getCalle() +
				"&nom2=" + poi_2.getNombre() +
				"&drc2=" + poi_2.getDireccion() +
				"&c=" + estaCerca;
		if( poi_1.esPOIValido() && poi_2.esPOIValido() )	response.redirect(str);
	
	}	
	
	/*
	 * 
	 * 
	 * 
	 * -----		TOMAR DATOS (QUEDO DE LA ENTREGA 1/2)		------
	 * 
	 * 
	 * 
	 */
	
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
	

	/*
	 * 
	 * 
	 * 
	 * -----		DISPONIBILIDAD		------
	 * 
	 * 
	 * 
	 */
	
	
	public ModelAndView disponible(Request request, Response response) {
		return new ModelAndView(null, "disponible.hbs");
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
	
	
	public void dirigirAResultadoDisponibilidad(POI poi, boolean disponible, Response response){
		String str = "/POIs/resultadoDisponibilidad?disp=" + disponible +
				"&nom2=" + poi.getNombre() +
				"&drc2=" + poi.getDireccion().getCalle();
		if( poi.esPOIValido() )	response.redirect(str);
	}

	public ModelAndView resultadoDisponibilidad(Request request, Response response) {
		return new ModelAndView(null, "resultadoDisponibilidad.hbs");
	}

	
	/*
	 * 
	 * 
	 * 
	 * -----		BUSQUEDA		------
	 * 
	 * 
	 * 
	 */
	
	public ModelAndView busqueda(Request request, Response response) {
		chequearUsuario(response);
		return new ModelAndView(null, "busquedaPOI.hbs");
	}
	
	

	public ModelAndView buscar(Request request, Response response) {
		String usuarioLogueado = UsuarioController.GetInstancia().getUsuarioLogueado();
		chequearUsuario(response);
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		
		/*	--	Busqueda en SQL --  */
		String query = request.queryString();
		int ultimoIgual = query.lastIndexOf("=");
		String ultimoNumero = query.substring(ultimoIgual - 1, ultimoIgual);
		int cantidadMax = Integer.parseInt(ultimoNumero);
		ArrayList<POI> listaFiltrada = new ArrayList<POI>();
		for(int j = 1; j<=cantidadMax; j++){
			String nom = request.queryParams("nombre" + j);
			listaNombres.add(nom);
		}
		try{
			String queryNombresIngresados =  "SELECT * FROM poi";
				
			Statement st = UsuarioController.GetInstancia().getConexion().getConexion().createStatement();
			ResultSet rs = st.executeQuery( queryNombresIngresados );

			POI poiABuscar = new CGP();		//Solo para esta Busqueda
			if(!rs.equals(null)){
			    while(rs.next()){
			    		
			    	
			    	poiABuscar = new CGP();
			    	poiABuscar.setNombre( rs.getString("nombrepoi") );		// Nombre POI de DB
		    		poiABuscar.setDireccion( new Direccion( rs.getString("direccion") ) );
		    		poiABuscar.setUbicacion( new Point( Double.parseDouble(rs.getString("coordenada_x")), Double.parseDouble(rs.getString("coordenada_x") )) );
		    		
			    	System.out.println("Busco_POI: " + poiABuscar.getNombre());
			    	
			    	for(int i=1; i<=cantidadMax; i++){
			    		String nombre = request.queryParams("nombre" + i); // Nombre POI de Busqueda
			    		
			    		System.out.println("Busco: " + nombre);
			    		if(nombre!=null && poiABuscar.getNombre().contains(nombre) && !listaFiltrada.contains(poiABuscar)){
					    	System.out.println("Agregue: " + poiABuscar.getNombre());
				    		listaFiltrada.add(poiABuscar);
			    		}
			    	}
			    	
			    }
			}
			            
		}
		catch(SQLException e){ e.printStackTrace(); }
		/*	--	Fin Busqueda en SQL --  */
			
			if(listaFiltrada.size()> 0){
				String str = "/paginaBusqueda?cantidadFilas=" + listaFiltrada.size();
				String listaPois = "";
				for(int i = 0; i < listaFiltrada.size(); i++){
						str = str + "&nombre=" + listaFiltrada.get(i).getNombre() +
						"&direccion=" + listaFiltrada.get(i).getDireccion().getCalle() +" "+ listaFiltrada.get(i).getDireccion().getNumero();
						if(i!=0) listaPois = listaPois + ", ";
						listaPois = listaPois + listaFiltrada.get(i).getNombre();
				}
				
				Busqueda busque = new Busqueda(DateTime.now(), usuarioLogueado, "nombre: " + listaNombres, listaFiltrada.size(), listaPois);
				this.lista = RepoBusquedas.GetInstancia().getListaBusqueda();
				this.lista.add(busque);
				try{
					Statement st;
					String fecha = DateTime.now().getYear() + "-" + DateTime.now().getMonthOfYear() + "-" +DateTime.now().getDayOfMonth() + " "+ DateTime.now().getHourOfDay() + ":" +DateTime.now().getMinuteOfHour();
				    st = UsuarioController.GetInstancia().getConexion().getConexion().createStatement();
				    String queryagre = "INSERT INTO dbo.busquedas VALUES ('"+ usuarioLogueado +"', 'nombre: "+ listaNombres  +"', '"+ listaFiltrada.size() +"', '"+fecha +"', '" + listaPois + "');"; 
				    st.executeUpdate(queryagre);
				    st.close();
				}
				
				catch(SQLException e){
					e.printStackTrace();
				}
				response.redirect(str);
				return null;
			}
			else{
				Busqueda busque = new Busqueda(DateTime.now(), usuarioLogueado, "nombre: " + listaNombres, 0, "");
				this.lista = RepoBusquedas.GetInstancia().getListaBusqueda();
				this.lista.add(busque);
				System.out.println("No se encontro ningun poi");
				response.redirect("/paginaBusqueda?cantidadFilas=0");
				return null;
			}
	}
	public ModelAndView nuevaAccion(Request request, Response response) {
		return new ModelAndView(null, "configurarAcciones.hbs");
	}
	/*
	 * 
	 * 
	 * 
	 * -----		ABM		------
	 * 
	 * 
	 * 
	 */
	
	public ModelAndView modificarPOI(Request request, Response response) {
		return new ModelAndView(null, "modificarPOI.hbs");
	}
	
	public ModelAndView seleccionarPOI(Request request, Response response) {
		return new ModelAndView(null, "seleccionarPoiAModificar.hbs");
	}
	
	public ModelAndView tomarDatoPoi(Request request, Response response) {
		
		try{
		String queryn = request.queryParams("nombreAMandar");
			
		String queryNombresIngresados =  "SELECT nombrepoi, direccion, coordenada_x, coordenada_y, tipo FROM poi WHERE nombrepoi='" + queryn +"'";
		String str = "";
		
		Statement st = UsuarioController.GetInstancia().getConexion().getConexion().createStatement();
		ResultSet rs = st.executeQuery( queryNombresIngresados );

		if(!rs.equals(null)){
			while(rs.next()){
		    	str = str + "nombre=" + rs.getString("nombrepoi") + "&";
		    	str = str + "direccion=" + rs.getString("direccion") + "&";
		    	str = str + "coordenada_x=" + rs.getString("coordenada_x") + "&";
		    	str = str + "coordenada_y=" + rs.getString("coordenada_y") + "&";
		    	str = str + "tipo=" + rs.getString("tipo");
		    }
			str = "/modificarPOI?" + str;
			response.redirect(str);
		}
		            
		}
		catch(SQLException e){ e.printStackTrace(); }
		
		return null;
	}
	
	public ModelAndView generarListaDesplegable(Request request, Response response) {
		
		try{
		String queryNombresIngresados =  "SELECT * FROM poi";
		String str = "";
		
		Statement st = UsuarioController.GetInstancia().getConexion().getConexion().createStatement();
		ResultSet rs = st.executeQuery( queryNombresIngresados );

		if(!rs.equals(null)){
		    int i=0;
			while(rs.next()){
		    	i++;
		    	str = str + "nombre" + i + "=" + rs.getString("nombrepoi") + "&";
		    }
			str = "/seleccionarPOI?cantidadFilas=" + i + "&" + str;
			response.redirect(str);
		}
		            
		}
		catch(SQLException e){ e.printStackTrace(); }
		
		return null;
	}
}
