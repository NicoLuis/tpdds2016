package principal;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import poi.*;
import bases.*;

import org.junit.Assert;

public class BusquedaLibreTest {
	private ParadaColectivo paradaDel114;
	private ParadaColectivo paradaDel107;
	private ParadaColectivo paradaDel47;
	private CGP cgp;
	private LocalComercial libreriaEscolar;
	private LocalComercial supermercado;
	private POI localDeRopa;
	private POI casaDeComida;
	private BasePOIs basePois;
	private ArrayList<POI> listaPois;
	

	@Before
	public void init(){
		basePois = new BasePOIs();
		paradaDel107 = basePois.crear_paradaDel107();
		paradaDel114 = basePois.crear_paradaDel114();
		paradaDel47 =basePois.crear_paradaDel47();
		cgp = basePois.crear_CGP_2();
		libreriaEscolar = basePois.crear_libreriaEscolar_1();
		supermercado = basePois.crear_supermercado_1();
		localDeRopa = basePois.crear_localDeRopa_1();
		casaDeComida = basePois.crear_casaDeComida_1();
		listaPois = basePois.crear_arrayPOIs();
	}

	@Test
	public void testCoincideConLaBusquedaDeLaParada114 (){
		Assert.assertTrue(paradaDel114.coincideConLaBusqueda("114"));
	}

	@Test
	public void testCoincideConLaBusquedaDeLaParada107 (){
		Assert.assertTrue(paradaDel107.coincideConLaBusqueda("107"));
	}

	@Test
	public void testElRubroPerteneceALaLibreriaEscolar(){
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("libreria"));
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("papelera"));
	}
	
	@Test
	public void testElRubroPerteneceAlSupermercado(){
		Assert.assertTrue(supermercado.coincideConLaBusqueda("libreria"));
		Assert.assertFalse(supermercado.coincideConLaBusqueda("papelera"));
	}
	
	@Test
	public void testElRubroNoPerteneceALaLibreriaEscolar(){
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("libreria"));
		Assert.assertFalse(libreriaEscolar.coincideConLaBusqueda("verduleria"));
	}
	
	@Test
	public void testElPuntoTieneElTagDeRopa(){
		Assert.assertTrue(localDeRopa.isInTagsList("elegante") && localDeRopa.isInTagsList("casual"));
	}
	
	@Test
	public void testElPuntoTieneElTagDeComida(){
		Assert.assertTrue(casaDeComida.isInTagsList("cena"));
	}
	
	@Test
	public void testPuntosQueNoContienenElTag(){
		Assert.assertFalse(localDeRopa.isInTagsList("desayuno"));
	}
	
	@Test
	public void testUnSoloPOIContieneAsesoramiento(){
		Assert.assertTrue(cgp.coincideConLaBusqueda("Asesoramiento"));
		Assert.assertEquals(basePois.coincideConLaBusqueda(listaPois, "Asesoramiento").count(), 1);
	}
	
	@Test
	public void testCantidadPOIsParada(){
		Assert.assertTrue(paradaDel107.coincideConLaBusqueda("Parada"));
		Assert.assertTrue(paradaDel114.coincideConLaBusqueda("Parada"));
		Assert.assertTrue(paradaDel47.coincideConLaBusqueda("Parada"));
		Assert.assertEquals(basePois.coincideConLaBusqueda(listaPois, "Parada").count(), 3);
	}
	
	@Test
	public void testElRubroPapeleraPerteneceSoloALaLibreriaEscolar(){	
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("papelera"));
		Assert.assertFalse(supermercado.coincideConLaBusqueda("papelera"));
		Assert.assertFalse(paradaDel114.coincideConLaBusqueda("papelera"));
		Assert.assertTrue(basePois.coincideConLaBusqueda(listaPois, "papelera").allMatch(poi -> poi.getNombre() == "Libreria Escolar"));		
	}
	
	@Test
	public void testUnRubroCualquieraNOPerteneceANadie(){	
		Assert.assertEquals(basePois.coincideConLaBusqueda(listaPois, "cualquiera").count(), 0);		
	}
	
	@Test
	public void testCantidadRubrosLibreria(){
		Assert.assertTrue(libreriaEscolar.coincideConLaBusqueda("libreria"));
		Assert.assertTrue(supermercado.coincideConLaBusqueda("libreria"));
		Assert.assertFalse(basePois.coincideConLaBusqueda(listaPois, "libreria").count() == 10);
		Assert.assertEquals(basePois.coincideConLaBusqueda(listaPois, "libreria").count(), 2);
	}
	
	
}
