package test.java.principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
	private LocalDateTime horarioFueraDeServicio;
	private LocalDateTime horarioEnServicio;
	private BasePOIs basePois;
	private ArrayList<POI> listaPois;
	
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
		listaPois = basePois.crear_arrayPOIs();
		horarioEnServicio = LocalDateTime.of(2016, 5, 5, 12, 30, 00, 00);
		horarioFueraDeServicio = LocalDateTime.of(2016, 5, 5, 20, 30, 00, 00);
	}
	
	
	
	///////----------------------     CERCANIA     ----------------------///////
	
	
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
	
	
	
	
	///////----------------------     DISPONIBILIDAD     ----------------------///////
	
	@Test
	public void testParada47Disponible(){
		Assert.assertTrue(paradaDel47.estaDisponible(horarioEnServicio));
		Assert.assertTrue(paradaDel47.estaDisponible(horarioFueraDeServicio));
	}
	
	@Test
	public void testBancoDisponible(){
		Assert.assertTrue(banco.estaDisponible(horarioEnServicio));
	}
	
	@Test
	public void testBancoNoDisponible(){
		Assert.assertFalse(banco.estaDisponible(horarioFueraDeServicio));
	}
	
	@Test
	public void testCGPDisponibleBuscandoPorNombre(){
		Assert.assertTrue(cgp.estaDisponible(horarioEnServicio,"Rentas"));	
	}
	
	@Test
	public void testCGPNODisponibleBuscandoPorNombre(){
		Assert.assertFalse(cgp.estaDisponible(horarioFueraDeServicio,"Rentas"));	
	}
	
	@Test
	public void testCGPalMenosUnoDisponibleSinBuscarNombre(){
		Assert.assertTrue(cgp.estaDisponible(horarioEnServicio));	
	}
	
	@Test
	public void testCGNingunoDisponibleSinBuscarNombre(){
		Assert.assertFalse(cgp.estaDisponible(horarioFueraDeServicio));	
	}
	
	@Test
	public void testLibreriaDisponible(){		// Libreria con RangoDeAtencion(10, 18, 1, 5)
		Assert.assertTrue(libreriaEscolar.estaDisponible(horarioEnServicio));	
	}
	
	@Test
	public void testLibreriaNODisponible(){		// Libreria con RangoDeAtencion(10, 18, 1, 5)
		Assert.assertFalse(libreriaEscolar.estaDisponible(horarioFueraDeServicio));	
	}

	
}
