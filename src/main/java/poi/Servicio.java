package poi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Servicio {
	private String 		nombre;
	private List<Franja> franjaHoraria = new ArrayList<Franja>();
	
	
	public Servicio(String nombre){
		this.nombre = nombre;
	}
	
	public Servicio(String nombre, Franja rangoDeAtencion){
		this.nombre = nombre;
		this.franjaHoraria.add(rangoDeAtencion);
	}
	
	public Servicio(){}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public void agregarFranja(Franja franja) {
		franjaHoraria.add(franja);
	}
	
	public List<Franja> getRangoDeAtencion() {
		return franjaHoraria;
	}

	public void setRangoDeAtencion(List<Franja> rangoDeAtencion) {
		this.franjaHoraria = rangoDeAtencion;
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo) {
		return franjaHoraria.stream().filter(f -> f.estaDisponible(unTiempo)).collect(Collectors.toList())
				.size() > 0;
	}
	

}
