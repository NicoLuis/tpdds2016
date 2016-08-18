package principal;

import java.util.List;
import java.util.stream.Collectors;

import poi.*;
import bases.HomePois;
import sistExternos.*;

import org.junit.*;

public class TestHomePois {

	private HomePois homePois = HomePois.GetInstancia();
	
	@Before
	public void init(){
		HomePois.reset();
	}
	
	@Test
	public void getInstanciaNoDebeDevolverNull(){
		
		Assert.assertNotSame(null, HomePois.GetInstancia());
	}
	
	@Test
	public void getInstanciaDebeDevolverSiempreLoMismo(){
		
		HomePois home1 = HomePois.GetInstancia();
		HomePois home2 = HomePois.GetInstancia();
		
		Assert.assertSame(home1, home2);
	}
	
	@Test
	public void agregarPoiDeberiaAgregarloALaLista(){
		
		int cantidadInicial = homePois.cantidadPois();
		
		ParadaColectivo colectivo = new ParadaColectivo("100");
		LocalComercial local = new LocalComercial();
		local.setRubro(new Rubro("rubro",10));
		local.setNombre("local");
		
		homePois.agregarPoi(colectivo);
		homePois.agregarPoi(local);
		
		Assert.assertSame(cantidadInicial + 2,  homePois.cantidadPois());
	}
	
	@Test
	public void quitarPoiDeberiaQuitarlo(){
		
		int cantidadInicial = homePois.cantidadPois();
		
		ParadaColectivo colectivo = new ParadaColectivo("100");
		
		homePois.agregarPoi(colectivo);
		homePois.quitarPoi(colectivo);
		
		Assert.assertSame(cantidadInicial, homePois.cantidadPois());
	}
	
	@Test
	public void agregarIntegracionRepetidaNoDeberiaAgregarla(){
		
		homePois.agregarIntegracion(IntegracionBancos.GetInstance());
		
		Assert.assertSame(2, homePois.cantidadIntegraciones());
	}
	
	@Test
	public void quitarIntegracionDeberiaQuitarla(){
		homePois.quitarIntegracion(IntegracionBancos.GetInstance());
		
		Assert.assertSame(1, homePois.cantidadIntegraciones());
	}
}
