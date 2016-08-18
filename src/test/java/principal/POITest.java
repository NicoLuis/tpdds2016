package principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

import poi.*;
import bases.*;

import org.junit.Assert;

public class POITest {
	
	private ParadaColectivo paradaDel47;
	private CGP cgp;
	private Banco banco;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Point ubicacionCercana;
	private Point ubicacionLejana;
	private LocalDateTime horarioFueraDeServicio;
	private LocalDateTime horarioEnServicio;
	private HomePois basePois;
	private ArrayList<POI> listaPois;
	
	@Before
	public void init(){
		basePois = HomePois.GetInstancia();
		ubicacionCercana = basePois.crear_ubicacionCercana().getCoordenadas();
		ubicacionLejana = basePois.crear_ubicacionLejana().getCoordenadas();
		cgp = basePois.crear_CGP_1();
		paradaDel47 = basePois.crear_paradaDel47();
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
		Assert.assertTrue(paradaDel47.estaCerca(ubicacionCercana)); 
	}
	
	@Test
	public void testParada47Lejano(){
		Assert.assertFalse(paradaDel47.estaCerca(ubicacionLejana));
	}

	@Test
	public void testCGPDentroDeLaMismaComuna(){
		Assert.assertTrue(cgp.estaCerca(ubicacionCercana)); 
	}
	
	@Test
	public void testCGPLejano(){
		Assert.assertFalse(cgp.estaCerca(ubicacionLejana)); 
	}

	@Test 
	public void testBancoCercanoAMenosDe500Metros(){
		Assert.assertTrue(banco.estaCerca(ubicacionCercana)); 
	}
	
	@Test 
	public void testBancoLejano(){
		Assert.assertFalse(banco.estaCerca(ubicacionLejana));  
	}
	
	@Test 
	public void testLibreriaDentroDelRadio(){
		Assert.assertTrue(libreriaEscolar.estaCerca(ubicacionCercana));
	}
	
	@Test
	public void testLibreriaFueraDelRadio(){
		Assert.assertFalse(libreriaEscolar.estaCerca(ubicacionLejana));
	}
	
	@Test
	public void testKioskoDeDiariosDentroDelRadio(){
		Assert.assertTrue(kioskoDeDiarios.estaCerca(ubicacionCercana));
	}
	
	@Test
	public void testKioskoDeDiariosLejano(){
		Assert.assertFalse(kioskoDeDiarios.estaCerca(ubicacionLejana));
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
