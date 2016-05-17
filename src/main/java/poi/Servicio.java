package main.java.poi;

public class Servicio {
	private String 					nombre;
	private RangoDeAtencion        rangoDeAtencion;
	
	public Servicio(String nombre, RangoDeAtencion rangoDeAtencion){
		this.nombre = nombre;
		this.rangoDeAtencion = rangoDeAtencion;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public RangoDeAtencion getRangoDeAtencion() {
		return rangoDeAtencion;
	}

	public void setRangoDeAtencion(RangoDeAtencion rangoDeAtencion) {
		this.rangoDeAtencion = rangoDeAtencion;
	}
	
	

}
