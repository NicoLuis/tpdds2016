package test.java.principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.time.Month;

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
	private Servicio cajero;
	private Servicio rentas;
		//LocalDateTime(yyyy-MM-ddTHH:mm:ss)
		//horarioEnServicio = new LocalDateTime(2016-05-05T15:30:00);		
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
		rentas = new Servicio("Rentas", new RangoDeAtencion(7.30,19.30,1,5));
		cajero = new Servicio("Cajero", new RangoDeAtencion(10,15.30,1,5));
		horarioEnServicio = LocalDateTime.of(2016, 5, 5, 15, 30, 00, 00);	
		horarioFueraDeServicio = LocalDateTime.of(2016, 5, 5, 15, 30, 00, 00);	
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
	
	@Test
	public void testParada47Disponible(){
		Assert.assertTrue(paradaDel47.estaDisponible(horarioEnServicio));
	}
	
	@Test
	public void testBancoDisponible(){
	Assert.assertTrue(banco.estaDisponible(horarioEnServicio,cajero));
	}
	
	@Test
	public void testBancoNoDisponible(){
	Assert.assertFalse(banco.estaDisponible(horarioFueraDeServicio,cajero));
	}	
	
	//public void testCGPalMenosUnoDisponible(){
	//	Assert.assertTrue(cgp.estaDisponible(horarioEnServicio));	
	//	}
	
	@Test
	public void testCGPDisponible(){
			Assert.assertTrue(cgp.estaDisponible(horarioEnServicio,rentas));	
			}
	
	@Test
	public void testCGPNODisponible(){
		Assert.assertFalse(cgp.estaDisponible(horarioFueraDeServicio,rentas));	
		}
	
	@Test
	public void testLibreriaDisponible(){
		Assert.assertTrue(libreriaEscolar.estaDisponible(horarioEnServicio,rentas));	
		}
	
	@Test
	public void testLibreriaNODisponible(){
	Assert.assertFalse(libreriaEscolar.estaDisponible(horarioFueraDeServicio,rentas));	
	}

	
}
