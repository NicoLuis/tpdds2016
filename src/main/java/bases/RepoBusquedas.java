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
	
	public void crearBusquedas(){
		addBusqueda(new Busqueda("jperez", "nombre='lalala'", new DateTime().withDate(LocalDate.now()), 2));
		addBusqueda(new Busqueda("jperez", "nombre='lalala2'", new DateTime().withDate(LocalDate.now()), 5));
		addBusqueda(new Busqueda("papa", "nombre='papa'", new DateTime().withDate(LocalDate.now()), 1));
		addBusqueda(new Busqueda("usuario134", "nombre='carlitos'", new DateTime().withDate(LocalDate.now()), 4));
	}
	
}
