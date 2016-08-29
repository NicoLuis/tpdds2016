package poi;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import bases.HomePois;
import bases.HomeTerminal;
import procesos.AccionExtra;

public class Terminal {

	private ArrayList<AccionExtra> accionesAdicionales;
	private Point coordenadaLocal;
	private HomePois homePois;
	private HomeTerminal homeTerminal;
	private String nombre;
	private int comuna;
	private String usuario;
	private String password;
	private boolean admin;
	public Terminal(double latitud, double longitud, String nombre, int comuna) {
		homePois = HomePois.GetInstancia();
		homeTerminal = HomeTerminal.GetInstancia();
		coordenadaLocal = new Point(latitud, longitud);
		this.nombre = nombre;
		this.comuna = comuna;
		homeTerminal.agregarTerminal(this);
	}

	public String getusuario(){
		return usuario;
	}
	public String getpass(){
		return password;
	}
	public void setUsuario(String user){
		this.usuario = user;
	}
	public void setpass(String pass){
		this.password = pass;
	}
	public void setAdmin(boolean adm){
		this.admin = adm;
	}
	public boolean getAdmin(){
		return admin;
	}
	public int getComuna() {
		return comuna;
	}

	public Terminal(String nom) {
		this.nombre = nom;
	};
	
	public Terminal() {};

	public List<POI> buscar(String busqueda) {

		Long tiempo = System.currentTimeMillis();

		List<POI> resultadoBusqueda = homePois.buscar(busqueda, coordenadaLocal);
		tiempo = System.currentTimeMillis() - tiempo;
		tiempo /= 1000; // Lo paso a segundos

		for (AccionExtra accion : accionesAdicionales) {

			accion.ejecutar(busqueda, resultadoBusqueda, tiempo, this);
		}

		return resultadoBusqueda;
	}

	public List<POI> puntosCercanos() {
		return homePois.puntosCercanos(coordenadaLocal);
	}

	public List<POI> puntosAceptados(String busqueda) {
		return homePois.puntosAceptados(busqueda);
	}

	public List<POI> puntosDisponibles() {
		return homePois.puntosDisponibles();
	}

	public void setCoordenadas(int x, int y) {
		coordenadaLocal = new Point(x, y);
	}

	public ArrayList<AccionExtra> getAccionesAdicionales() {
		return accionesAdicionales;
	}

	public Point getCoordenadas() {
		return coordenadaLocal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setAccionesAdicionales(ArrayList<AccionExtra> accionesAdicionales) {
		this.accionesAdicionales = accionesAdicionales;
	}

}
