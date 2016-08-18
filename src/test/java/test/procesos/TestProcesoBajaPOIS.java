package test.procesos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bases.HomePois;
import poi.LocalComercial;
import poi.POI;
import poi.ParadaColectivo; 
import poi.Rubro;
import procesos.BajaPOIS;
import procesos.ExcepcionProceso;
import procesos.ManejadorErrores;
import procesos.NotificarAdmin; 
import sistExternos.ServicioREST; 
import sistExternos.ServicioREST.Baja;

public class TestProcesoBajaPOIS {

	static HomePois homePois;
	static BajaPOIS bajaPOIS;
	static ParadaColectivo colectivo1;
	static ParadaColectivo colectivo2;
	static ParadaColectivo colectivo3;
	static ParadaColectivo colectivo4;
	static LocalComercial carrousel;
	static LocalComercial grafica;
	static ArrayList<POI> listaPois;
	
	@Before
	public void init() {
		ServicioREST mockServ = mock(ServicioREST.class);
		LocalDate fecha = LocalDate.of(2016, 5, 23);
		ArrayList<Baja> listaDeBajas = new ArrayList<Baja>();
		Baja baja1 = mockServ.new Baja("114", fecha);//elimina colectivo1 de la lista de pois
		Baja baja2 = mockServ.new Baja("116", fecha);//elimina colectivo3 de la lista de pois 
		Baja baja3 = mockServ.new Baja("Carrousel", fecha);//elimina carrousel de la lista de pois
		Baja baja4 = mockServ.new Baja("Grafica", LocalDate.of(2017, 5, 12));//no lo elimina por fecha
		listaDeBajas.add(baja1);
		listaDeBajas.add(baja2);
		listaDeBajas.add(baja3);
		listaDeBajas.add(baja4);
		when(mockServ.enviarBajas()).thenReturn(listaDeBajas);
		ManejadorErrores mErrores = new NotificarAdmin();
		bajaPOIS = new BajaPOIS(mErrores, mockServ);
		homePois = HomePois.GetInstancia();
		colectivo1 = new ParadaColectivo("110");
		colectivo2 = new ParadaColectivo("111");
		colectivo3 = new ParadaColectivo("112");
		colectivo4 = new ParadaColectivo("116");
		carrousel = new LocalComercial();
		carrousel.setRubro(new Rubro("", 200));
		carrousel.setNombre("Carrousel");
		grafica = new LocalComercial();
		grafica.setNombre("Grafica");
		grafica.setRubro(new Rubro("", 200));
		homePois.agregarPoi(grafica);
		homePois.agregarPoi(carrousel);
		homePois.agregarPoi(colectivo4);
		homePois.agregarPoi(colectivo3);
		homePois.agregarPoi(colectivo2);
		homePois.agregarPoi(colectivo1);
		listaPois= homePois.getListaPois();
	}

	@Test

	public void seDeberianEliminarLosPoI() throws ExcepcionProceso {// debibo a que se dieron de baja tres pois
		
		int cantidadInicial = homePois.cantidadPois();
		System.out.println("---------------------------------------------------------");
		bajaPOIS.ejecutarProceso();
		System.out.println("---------------------------------------------------------");
		
		Assert.assertEquals(cantidadInicial - 3, HomePois.GetInstancia().cantidadPois());

	}

	@Test
	
	public void  elLocalCarrouselDeberiaSerEliminado() throws ExcepcionProceso{// debido a que se informo su baja (baja3)
		
		bajaPOIS.ejecutarProceso();
		
		Assert.assertFalse( homePois.getListaPois().contains(carrousel));
		
	}
	@Test
	
	public void elLocalGraficaTieneQueSeguirEnlaListaDePOIS() throws ExcepcionProceso{//tiene que seguir estando porque no se informo su baja
		
		bajaPOIS.ejecutarProceso();
		
		Assert.assertTrue( homePois.getListaPois().contains(grafica));
		
	}
		
	@Test

	public void elColectivo2DelaLinea115NoTieneQueSerEliminado() throws ExcepcionProceso { // debido a que la fecha de baja no es valida
																							 
		bajaPOIS.ejecutarProceso();

		Assert.assertTrue( homePois.getListaPois().contains(colectivo2));

	}
	
	@After
	public void aft(){
		HomePois.reset();
	}

}
