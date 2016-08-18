package poi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Banco extends POI{

	private String nombreGerente;
	List<String> listaServicios = new ArrayList<String>();

	public Banco(String nombreBanco, String nombreGerente, double coordX, double coordY) {
		setNombre(nombreBanco);
		setNombreGerente(nombreGerente);
		setCoordenadas(coordX, coordY);

		// Creo la franja Horaria del banco
		Franja franjaBanco = new Franja(LocalTime.of(10, 0), LocalTime.of(15, 0), 1, 5);

		ArrayList<Franja> lista = new ArrayList<Franja>();
		lista.add(franjaBanco);

		this.setFranjaHoraria(lista);
	}
	
	public Banco(){}

	public List<String> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<String> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public String getNombreGerente() {
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

	
	
	
	
	
	
	
	
	/**///*/*//*//*/*/*//*//**///*/*//*//*/*/*//*//**///*/*//*//*/*/*//*//**///*/*//*//*/*/*//*//**///*/*//*//*/*/*//*//**///*/*//*//*/*/*//*/
	public double cercaniaRequerida(){
		return 500.0;
	}
	
	public boolean estaDisponible (LocalDateTime fechaYhora){	
		Franja rangoLocal = new Franja(LocalTime.of(10, 0), LocalTime.of(15, 0), 1, 5);
		return (rangoLocal.estaDisponible(fechaYhora));
	}
	
	public String tipo(){
		return "Sucursal de Banco";
	}
}
