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
			//RepoBusquedas.GetInstancia().crearBusquedas();
		}
		return instancia;
	}


	public static void reset() {
		instancia = new RepoBusquedas();
	}
	
	public ArrayList<Busqueda> getListaBusqueda(){
		return listaBusquedas;
	}
	public void setListaBusqueda(String value){
		listaBusquedas = new ArrayList<Busqueda>();
	}
	
	public void addBusqueda(Busqueda busq){
		listaBusquedas.add(busq);
	}
	//
	public void crearBusquedas(){
		/*addBusqueda(new Busqueda(new DateTime().withDate(LocalDate.now()), " nLuis ", " nombre: pepe1 ", 0, ""));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 2, 17).withHourOfDay(12).withMinuteOfHour(5), " jBenitez ", " nombre: CGP ", 7, "CGP_1, CGP_2, CGP_3, CGP_4, CGP_5, CGP_6, CGP_7"));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 1, 21).withHourOfDay(23).withMinuteOfHour(52), " mBrandes ", " nombre: UTN ", 1, "UTN"));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 12, 3).withHourOfDay(8).withMinuteOfHour(27), " gCoss ", " nombre: Yamaha", 2, "Concesionario Yamaha, Yamaha Motors"));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 12, 3).withHourOfDay(8).withMinuteOfHour(26), " gCoss ", " nombre: Honda", 4, "Honda Pilar, Concesionario Honda. Honda Autos, Buena Honda"));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 8, 1).withHourOfDay(3).withMinuteOfHour(33), " mBrandes ", " nombre: CGP ", 7, "CGP_1, CGP_2, CGP_3, CGP_4, CGP_5, CGP_6, CGP_7"));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 12, 25).withHourOfDay(17).withMinuteOfHour(7), " mHuayta ", " nombre: pepe4 ", 0, ""));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 3, 30).withHourOfDay(20).withMinuteOfHour(5), " fNagelkop ", " nombre: Sony ", 1, "Sony Store"));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 7, 26).withHourOfDay(22).withMinuteOfHour(52), " mHuayta ", " nombre: 101, 150, Remiseria ", 3, "Parada del 101, Parada del 150, Remiseria Juanito"));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 8, 12).withHourOfDay(5).withMinuteOfHour(5), " jBenitez ", " nombre: 31, 75 ", 2, "Parada del 31, Parada del 75"));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 10, 8).withHourOfDay(18).withMinuteOfHour(18), " nLuis ", " nombre: kkjhty ", 0, ""));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 1, 20).withHourOfDay(12).withMinuteOfHour(6), " mBrandes ", " nombre: McDonalds", 4, "McDonalds_1, McDonalds_2, McDonalds_3, McDonalds_4"));
		addBusqueda(new Busqueda(new DateTime().withDate(2015, 2, 9).withHourOfDay(12).withMinuteOfHour(38), " fNagelkop ", " nombre: Fravega ", 3, "Fravega Abasto, Fravega Villa del Parque, Fravega Palermo"));
		addBusqueda(new Busqueda(new DateTime().withDate(2016, 5, 25).withHourOfDay(20).withMinuteOfHour(5), " mHuayta ", " nombre: CGP_7, 18 ", 2, "CGP_7, Parada del 18"));
	*/}
	
}
