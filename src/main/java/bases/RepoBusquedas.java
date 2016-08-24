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
	//new DateTime().withDate(LocalDate.now()
	public void crearBusquedas(){
		addBusqueda(new Busqueda("jperez", "'lalala2", "1", 2));
		addBusqueda(new Busqueda("jperez", "'lalala2'", "1", 5));
		addBusqueda(new Busqueda("papa", "'lalala2'",  "1", 1));
		addBusqueda(new Busqueda("usuario134", "'lalala2'",  "1", 4));
	}
	
}
