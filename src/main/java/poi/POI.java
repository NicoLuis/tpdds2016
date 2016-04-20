package main.java.poi;
import java.util.Collection;

import main.java.principal.Coordenada;
import main.java.principal.Direccion;

public class POI {
	private String nombre;
	private Coordenada coordenada;
	private Direccion direccion;
	
	public boolean seEncuentraAMenosDe(double metros, POI poi){ 
		return this.coordenada.calcularDistancia(poi.getCoordenada().getDecimalLat(), poi.getCoordenada().getDecimalLon()) < metros;
	}
	
	public boolean seEncuentraAMenosDeOtroPOI(double metros, Collection<POI> listaPOIs){ 
		return listaPOIs.stream().anyMatch(poi -> this.seEncuentraAMenosDe(4300000, poi) );
	}
	
	
	public boolean esValido(){
		return  getNombre()  != null && 
				getCoordenada()  != null;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}