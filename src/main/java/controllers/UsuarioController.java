package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sistExternos.Encryptor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import bases.RepoBusquedas;
import bases.RepoTerminales;

public class UsuarioController {

	String key = "Bar12345Bar12345"; // 128 bit key
    String initVector = "RandomInitVector"; // 16 bytes IV
    String usuarioLogueado;
	Conexion miconex = new Conexion();
	
	static UsuarioController instancia;
	private UsuarioController(){}
	public static UsuarioController GetInstancia() {

		if (instancia == null) {
			instancia = new UsuarioController();
			RepoBusquedas.GetInstancia().crearBusquedas();
		}
		return instancia;
	}
	
	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}
	public Conexion getConexion() {
		return miconex;
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
			Statement state = miconex.getConexion().createStatement();
			String querySeleccionar = "SELECT * FROM dbo.usuario";
			ResultSet rs = state.executeQuery(querySeleccionar);
			if(!rs.equals(null)){
				while(rs.next()){
					if(nombreusuario.equals(rs.getString("nombreusuario"))){
						return new ModelAndView(null, "errorRegistro.hbs");
					}
				}
			}
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
}
