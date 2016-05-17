package test.java.principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import main.java.poi.*;
import main.java.bases.*;

import org.junit.Assert;

public class POITest {
	
	private ParadaColectivo paradaDel47;
	private CGP cgp;
	private SucursalBanco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Point ubicacionCercana;
	private Point ubicacionLejana;
	private BasePOIs basePois;
	
	@Before
	public void init(){
		basePois = new BasePOIs();
		ubicacionCercana = basePois.crear_ubicacionCercana().getUbicacion();
		ubicacionLejana = basePois.crear_ubicacionLejana().getUbicacion();
		cgp = basePois.crear_CGP_1();
		paradaDel47 = basePois.crear_ParadaColectivo_1();
		banco = basePois.crear_SucursalBanco_1();
		libreriaEscolar = basePois.crear_libreriaEscolar_1();
		kioskoDeDiarios = basePois.crear_kioskoDeDiarios_1();
	}
	
	@Test
	public void testParada47CercanoAMenosDe100Metros(){
		Assert.assertTrue(paradaDel47.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test
	public void testParada47Lejano(){
		Assert.assertFalse(paradaDel47.estaCercaDe(ubicacionLejana)); 
	}

	@Test
	public void testCGPDentroDeLaMismaComuna(){
		Assert.assertTrue(cgp.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test
	public void testCGPLejano(){
		Assert.assertFalse(cgp.estaCercaDe(ubicacionLejana)); 
	}

	@Test 
	public void testBancoCercanoAMenosDe500Metros(){
		Assert.assertTrue(banco.estaCercaDe(ubicacionCercana)); 
	}
	
	@Test 
	public void testBancoLejano(){
		Assert.assertFalse(banco.estaCercaDe(ubicacionLejana));  
	}
	
	@Test 
	public void testLibreriaDentroDelRadio(){
		Assert.assertTrue(libreriaEscolar.estaCercaDe(ubicacionCercana));
	}
	
	@Test
	public void testLibreriaFueraDelRadio(){
		Assert.assertFalse(libreriaEscolar.estaCercaDe(ubicacionLejana));
	}
	
	@Test
	public void testKioskoDeDiariosDentroDelRadio(){
		Assert.assertTrue(kioskoDeDiarios.estaCercaDe(ubicacionCercana));
	}
	
	@Test
	public void testKioskoDeDiariosLejano(){
		Assert.assertFalse(kioskoDeDiarios.estaCercaDe(ubicacionLejana));
	}
	
}