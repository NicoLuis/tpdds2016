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
	 String key = "Bar12345Bar12345"; // 128 bit key
     String initVector = "RandomInitVector"; // 16 bytes IV
	String usuarioLogueado;
	ArrayList<Busqueda> lista = RepoBusquedas.GetInstancia().getListaBusqueda();
	Conexion miconex = new Conexion();
	public ModelAndView opciones(Request request, Response response) {
		chequearUsuario(response);
		if( RepoTerminales.GetInstancia().getBooleanAdmin() )
			return new ModelAndView(null, "MenuAdmin.hbs");
		return new ModelAndView(null, "MenuUser.hbs");
	}
	
	public ModelAndView modificarUser(Request request, Response response) {
		String nom = null;
		String ap = null;
		String user = null;
		try{
			Statement st;
			ResultSet rs;
			
	        st = miconex.getConexion().createStatement();
	        String query = "SELECT [nombreusuario], [contrasenia], [nombre], [apellido], [administrador] FROM dbo.usuario WHERE [nombreusuario] = '"+ usuarioLogueado +"'"; 
	        rs = st.executeQuery(query);
	        if(!rs.equals(null)){
	            while(rs.next()){
	            	nom = rs.getString("nombre");
	            	ap = rs.getString("apellido");
	            	user = rs.getString("nombreusuario");
	            	//boolean admin = rs.getBoolean("admin");
	            }   		
	        }st.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
			String str = "/modificarUsuario?nombre=" + nom + "&apellido=" + ap + "&usuario="+ user;
			response.redirect(str);
			return null;
	}
	
	
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
	public ModelAndView actualizarUsuario(Request request, Response response) {
		try{
			
			String nombre = request.queryParams("nombre");
			String apellido = request.queryParams("apellido");
			String nombreusuario = request.queryParams("usuario");
			String contraseña = request.queryParams("password");
			String verif = request.queryParams("password2");
			if(!nombre.equals("") && !apellido.equals("") && !nombreusuario.equals("") && !contraseña.equals("") && !verif.equals(""))
			{	
				//MessageDigest.getInstance(SHA)
				//String encriptada = encrypt(contraseña);
				String Encriptado = Encryptor.encrypt(key, initVector, contraseña);
				System.out.println(Encriptado);
				Statement st;
		        st = miconex.getConexion().createStatement();
		        String query = "UPDATE dbo.usuario SET nombreusuario='"+ nombreusuario +"', contrasenia='"+ Encriptado +"', nombre='"+ nombre +"', apellido='"+ apellido+"' WHERE nombreusuario='"+ usuarioLogueado+"'"; 
		        st.executeUpdate(query);
		        st.close();	
		        usuarioLogueado = nombreusuario;
			}else{
				return new ModelAndView(null, "layoutSesion.hbs");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return new ModelAndView(null, "layoutSesion.hbs");
	}
	
	public ModelAndView resultadoDistancia(Request request, Response response) {
		return new ModelAndView(null, "resultadoDistancia.hbs");
	}
	public ModelAndView modificarUsuario(Request request, Response response) {
		return new ModelAndView(null, "modificarUser.hbs");
	}
	public ModelAndView invalido(Request request, Response response) {
		System.out.println("DATOS NO VALIDOS");
		return new ModelAndView(null, "error.hbs");
	}
	public ModelAndView invalidaSesion(Request request, Response response){
		return new ModelAndView(null, "errorBusqueda.hbs");
	}
	public ModelAndView registroUsuario(Request request, Response response){
		return new ModelAndView(null, "registroUser.hbs");
	}
	public ModelAndView registro(Request request, Response response){
		try{
			String nombre = request.queryParams("nombre");
			String apellido = request.queryParams("apellido");
			String nombreusuario = request.queryParams("nombreusuario");
			String contraseña = request.queryParams("password");
			String verif = request.queryParams("password2");
			if(!nombre.equals("") && !apellido.equals("") && !nombreusuario.equals("") && !contraseña.equals("") && !verif.equals(""))
			{	
				if(!contraseña.equals(verif)){
					return new ModelAndView(null, "errorRegistro.hbs");
				}else{
					//MessageDigest.getInstance(SHA)
					//String encriptada = encrypt(contraseña);
				    String Encriptado = Encryptor.encrypt(key, initVector, contraseña);
				    System.out.println(Encriptado);
					Statement st;
		            st = miconex.getConexion().createStatement();
		            String query = "INSERT INTO dbo.usuario VALUES ('"+ nombreusuario +"', '"+ Encriptado +"', '"+ nombre +"', '"+ apellido+"', 'false')"; 
		            st.executeUpdate(query);
		            st.close();
				}
				return new ModelAndView(null, "home.hbs");
			}else{
				return new ModelAndView(null, "errorRegistro.hbs");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return new ModelAndView(null, "errorRegistro.hbs");
		}
		
	}
	public ModelAndView valido(Request request, Response response) {
		String ing = "Ingresar";
		String opcion = request.queryParams("boton");
		
		if(opcion.equals(ing)){
			try{
				String usuario = request.queryParams("usuario");
				String pass = request.queryParams("contrasenia");
				try{
					String Encriptado = Encryptor.encrypt(key, initVector, pass);
				    System.out.println(Encriptado);
					
					Statement st;
					ResultSet rs;
		            st = miconex.getConexion().createStatement();
		            String query = "SELECT [nombreusuario], [contrasenia], [administrador] FROM dbo.usuario WHERE [nombreusuario] = '"+ usuario + "' and [contrasenia] = '"+ Encriptado+ "'"; 
		            rs = st.executeQuery(query);
		            if(!rs.equals(null)){
			            while(rs.next()){
			            	rs.getString("nombreusuario");
			            	boolean admin = rs.getBoolean("administrador");
			            	this.usuarioLogueado = usuario;
							if(admin == true){
								System.out.println("Ingreso Sesion VALIDO Administrador");
								RepoTerminales.GetInstancia().setBooleanAdmin(true);
								return new ModelAndView(null, "layoutSesion.hbs");
							}else{
								System.out.println("Ingreso Sesion Usuario");
								RepoTerminales.GetInstancia().setBooleanAdmin(false);
								return new ModelAndView(null, "layoutSesion.hbs");
							}
				
			            }   		
		            }st.close();
		            
				}catch(SQLException e){
					e.getStackTrace();
				}}catch (IllegalStateException e){
				response.redirect("/InvalidoSesion");
			}catch(NumberFormatException e){
				response.redirect("/InvalidoSesion");
			}
			
			response.redirect("/InvalidoSesion");
			return null;
		}else{
			response.redirect("/registroUsuario");
			return null;
		}
		
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
		//System.out.println(request.queryParams("cantidad") + "cantidad");
	
		
		
		/*
		 Recibis parametro cantidad que minimo es 1 y maximo es la cantidad de campoos que puede haber
		 
		 siendo 
		 cantiadad = 3
		 
		 nombre1 = parada 21
		 nombre2 = cgp coso
		 nombre3 = lalala
		 
		 
		 Ojo que puede darse el caso de que cantidad sea 5 pero el item 4 no exista porque se elimino,
		 verificar con un null o que no sea ""
		 
		 No va mas nombre solo como esa ahora. 
		 ahora arranca con nombre1 el item1 de busqueda y va creciendo sucesivamente.
		 
		 no me funciono request.querymap o reques.paramvalues que podrian haber resuelto esto mas facil.
		 
		 
		 */
		
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		String query = request.queryString();
		int ultimoIgual = query.lastIndexOf("=");
		String ultimoNumero = query.substring(ultimoIgual - 1, ultimoIgual);
		
		int cantidadMax = Integer.parseInt(ultimoNumero);
		
		
		for(int i = 1; i <= cantidadMax; i++){
			String nombre = request.queryParams("nombre" + i);
			if(nombre != null) listaNombres.add(nombre);
		}
		
		
			ArrayList<POI> listaFiltrada = new ArrayList<POI>();
			HomePois basepoi = HomePois.GetInstancia();
			basepoi.crear_arrayPOIs();
			ArrayList<POI> lista = basepoi.getListaPois();
			
			for(int j = 0; j < listaNombres.size(); j++){
				String str = listaNombres.get(j);
				for(int i = 0; i < lista.size(); i++){
					POI nuevo = lista.get(i);
					if(nuevo.resultadosDeBusquedaLibre(str) != null && !listaFiltrada.contains(nuevo))
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
				Busqueda busque = new Busqueda(DateTime.now(), usuarioLogueado, "nombre: " + listaNombres, listaFiltrada.size(), listaPois);
				this.lista = RepoBusquedas.GetInstancia().getListaBusqueda();
				this.lista.add(busque);
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
	public ModelAndView resultadoDisponibilidad(Request request, Response response) {
		return new ModelAndView(null, "resultadoDisponibilidad.hbs");
	}
	
	public void chequearUsuario(Response response) {
		if(usuarioLogueado == null) response.redirect("/");
	}
}
