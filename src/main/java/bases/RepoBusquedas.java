package bases;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import poi.Terminal;

public class RepoBusquedas {


	private ArrayList<Busqueda> listaBusquedas;

	// Singleton//////////////////////////////////////////
	static RepoBusquedas instancia;

	private RepoBusquedas() {
		listaBusquedas = new ArrayList<Busqueda>();
	}

	public static RepoBusquedas GetInstancia() {

		if (instancia == null) {
			instancia = new RepoBusquedas();
			RepoBusquedas.GetInstancia().crearBusquedas();
		}
		return instancia;
	}


	public static void reset() {
		instancia = new RepoBusquedas();
	}
	
	public ArrayList<Busqueda> getListaBusqueda(){
		return listaBusquedas;
	}
	
	public void addBusqueda(Busqueda busq){
		listaBusquedas.add(busq);
	}
	//
	public void crearBusquedas(){
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario1 ", " nombre = pepe1 ", 1));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario2 ", " nombre = pepe2 ", 2));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario3 ", " nombre = pepe3 ", 3));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario4 ", " nombre = pepe4 ", 4));
	}
	
}
