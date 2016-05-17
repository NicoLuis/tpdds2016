package test.java.principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import main.java.poi.*;
import main.java.bases.*;

import org.junit.Assert;

public class BusquedaLibreTest {
	private Comuna comuna8;
	private ParadaColectivo paradaDel114;
	private ParadaColectivo paradaDel107;
	private CGP cgp;
	private ArrayList<Servicio> servicios;
	private Servicio asesoramiento;
	private Servicio atencionAlCliente;
	private LocalComercial libreriaEscolar;
	private LocalComercial supermercado;
	private Rubro rubrosLibreriaEscolar; //Rubros a los que pertenece
	private Rubro rubrosSupermercado;
	private String parada114;
	private String parada107;
	private ArrayList<String> tagsDeLocalDeRopa;
	private POI localDeRopa;
	private POI casaDeComida;
	private ArrayList<String> tagsDeCasaDeComida;
	

	@Before
	public void init(){
		//paradas de colectivos
		paradaDel114 = new ParadaColectivo(new Point(-34.6715, -58.4676), comuna8);
		paradaDel107 = new ParadaColectivo(new Point(-34.6578,-58.4787), comuna8);
		//rubros libreria escolar
		libreriaEscolar = new LocalComercial();
		rubrosLibreriaEscolar = new Rubro(-34.6744);
		rubrosLibreriaEscolar.addRubrosALosQuePertenece("libreria");
		rubrosLibreriaEscolar.addRubrosALosQuePertenece("papelera");
		libreriaEscolar.setRubro(rubrosLibreriaEscolar);
		//rubros supermercado
		supermercado = new LocalComercial();
		rubrosSupermercado = new Rubro(-34.6621);
		rubrosSupermercado.addRubrosALosQuePertenece("libreria");
		rubrosSupermercado.addRubrosALosQuePertenece("verduleria");
		rubrosSupermercado.addRubrosALosQuePertenece("carniceria");
		rubrosSupermercado.addRubrosALosQuePertenece("kiosko");
		supermercado.setRubro(rubrosSupermercado);
		// local de ropa
		localDeRopa = new POI();
		tagsDeLocalDeRopa = new ArrayList<String>();
		tagsDeLocalDeRopa.add("casual");
		tagsDeLocalDeRopa.add("deportva");
		tagsDeLocalDeRopa.add("elegante");
		localDeRopa.setTags(tagsDeLocalDeRopa);;
		// local de comida
		casaDeComida = new POI();
		tagsDeCasaDeComida = new ArrayList<String>();
		tagsDeCasaDeComida.add("almuerzo");
		tagsDeCasaDeComida.add("desayuno");
		tagsDeCasaDeComida.add("cena");
		casaDeComida.setTags(tagsDeCasaDeComida);
		// servicios del CGP
		cgp = new CGP();
		servicios = new ArrayList<Servicio>();
		asesoramiento = new Servicio();
		atencionAlCliente = new Servicio();
		servicios.addServicio(asesoramiento);
		servicios.addServicio(atencionAlCliente);
		cgp.setServicios(servicios);
		
}

	@Test
	public void testCoincideConLaBusquedaDeLaParada114 (){
		Assert.assertTrue(paradaDel114.coincideConLaBusqueda(parada114));
	}

	@Test
	public void testCoincideConLaBusquedaDeLaParada107 (){
		Assert.assertTrue(paradaDel107.coincideConLaBusqueda(parada107));
	
	}

	@Test
	public void testElRubroPerteneceALaLibreriaEscolar(){
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("libreria") && libreriaEscolar.coincideConLaBusqueda("papelera"));
	}
	
	@Test
	public void testElRubroPerteneceAlSupermercado(){
		Assert.assertTrue(supermercado.coincideConLaBusqueda("libreria") && supermercado.coincideConLaBusqueda("papelera"));
	}
	
	@Test
	public void testElRubroNoPerteneceALaLibreriaEscolar(){
		Assert.assertFalse(libreriaEscolar.coincideConLaBusqueda("libreria") && libreriaEscolar.coincideConLaBusqueda("verduleria"));
	}
	
	@Test
	public void testElPuntoTieneElTagDeRopa(){
		Assert.assertTrue(localDeRopa.isInTagsList("elegante") && localDeRopa.isInTagsList("casual"));
	}
	
	@Test
	public void testElPuntoTieneElTagDeComida(){
		Assert.assertTrue(localDeRopa.isInTagsList("cena"));
	}
	
	@Test
	public void testPuntosQueNoContienenElTag(){
		Assert.assertFalse(localDeRopa.isInTagsList("desayuno"));
	}
	
	@Test
	public void testLaBusquedaCoincideConLosServiciosDelCGP(){
		Assert.assertTrue(cgp.coincideConLaBusqueda("asesoramiento"));
	}
}
