package test.procesos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bases.HomePois;
import poi.LocalComercial;
import poi.Rubro;
import procesos.ActualizacionLocales;
import procesos.ExcepcionProceso;
import procesos.ManejadorErrores;
import procesos.NotificarAdmin;

import java.util.List;
import java.util.stream.Collectors;

public class TestProceosoActualizacionLocales {

	ManejadorErrores manejadorE;
	ActualizacionLocales procesoActualizacion;
	String path;
	HomePois homePois;

	@Before
	public void init() {
		manejadorE = new NotificarAdmin();
		path = "C:/ActualizacionDeLocalesTres.txt";
		procesoActualizacion = new ActualizacionLocales(manejadorE, path);
		LocalComercial carrousel = new LocalComercial();
		carrousel.setRubro(new Rubro("", 200));
		carrousel.setNombre("Carrousel");
		LocalComercial grafica = new LocalComercial();
		grafica.setNombre("Grafica");
		grafica.setRubro(new Rubro("", 200));
		homePois = HomePois.GetInstancia();
		homePois.agregarPoi(carrousel);
		homePois.agregarPoi(grafica);

	}
	
	@After
	public void reset() {
		HomePois.reset();
	}

	@Test
	public void seDebemActualizarLasPalabrasClavesDelLocalCarrousel() throws ExcepcionProceso {

		procesoActualizacion.ejecutarProceso();
		
		List<String> palabrasClaves = ((LocalComercial) homePois.getListaPois().stream().filter(poi -> poi.getNombre() == "Carrousel").collect(Collectors.toList()).get(0))
				.getPalabrasClave();

		Assert.assertEquals(palabrasClaves.get(0), "colegio");
		Assert.assertEquals(palabrasClaves.get(1), "escolar");
		Assert.assertEquals(palabrasClaves.get(2), "uniformes");
		Assert.assertEquals(palabrasClaves.get(3), "modas");
	}

	@Test
	public void laCantidadDePalabrasClavesDeCarrouselDebeSerCuatro() throws ExcepcionProceso {

		procesoActualizacion.ejecutarProceso();
		
		List<String> palabrasClaves = ((LocalComercial) homePois.getListaPois().stream().filter(poi -> poi.getNombre() == "Carrousel").collect(Collectors.toList()).get(0))
				.getPalabrasClave();

		Assert.assertEquals(palabrasClaves.size(), 4);
	}

	
	@Test
	public void laCantidadDePalabrasClavesDeLocalGraficaDebenSerDos() throws ExcepcionProceso{// se  agregan dos palabras claves , tinta y fotos
		
		procesoActualizacion.ejecutarProceso();
		
		List<String> palabrasClaves = ((LocalComercial) homePois.getListaPois().stream().filter(poi -> poi.getNombre() == "Grafica").collect(Collectors.toList()).get(0))
				.getPalabrasClave();

		Assert.assertEquals(2, palabrasClaves.size());
	}
	
	@Test
	public void seDebeAgregarLibreriaYDebeTenerDosPalabrasClaves() throws ExcepcionProceso{// se  agregan dos palabras claves , tinta y fotos
		
		procesoActualizacion.ejecutarProceso();
		
		List<String> palabrasClaves = ((LocalComercial) homePois.buscarNombre("Libreria")).getPalabrasClave();

		Assert.assertEquals(palabrasClaves.get(0), "libros");
		Assert.assertEquals(palabrasClaves.get(1), "separadores");
		Assert.assertEquals(2, palabrasClaves.size());
	}
	
	
	
	
}
