package bases;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario1 ", " nombre: pepe1 ", 1));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 2, 17).withHourOfDay(12).withMinuteOfHour(5), " usuario2 ", " nombre ", 20));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 1, 21).withHourOfDay(23).withMinuteOfHour(52), " philips ", " nombrr ", 3));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 12, 3).withHourOfDay(8).withMinuteOfHour(27), " usuario4 ", " consultaaa ", 10));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 8, 1).withHourOfDay(3).withMinuteOfHour(33), " juan ", " consulta ", 2));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 12, 25).withHourOfDay(17).withMinuteOfHour(7), " pepe ", " nombre: pepe4 ", 4));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " jvc ", " nombre: pepe4 ", 5));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " juan ", " consulta8 ", 0));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " philips ", " nombre: kkjhty ", 4));
		addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " usuario10 ", " nombre: kkjhty ", 7));
	}
	
}
