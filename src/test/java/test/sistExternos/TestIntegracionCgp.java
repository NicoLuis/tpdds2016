package test.sistExternos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import poi.CGP;
import bases.HomePois;
import poi.POI;
import sistExternos.*;
  
public class TestIntegracionCgp {

	CentroDTO centro = new CentroDTO();
	private IntegracionCgp cgps;
	private POI cgpNuevo = new CGP();

	@Before
	public void init() {
		cgps = IntegracionCgp.GetInstance();

		RangoServicioDTO rango1 = new RangoServicioDTO();

		RangoServicioDTO rango2 = new RangoServicioDTO();

		rango1.horarioDesde = 9;
		rango1.horarioHasta = 12;
		rango1.dia = 1;
		rango1.minutoDesde = 0;
		rango1.minutoHasta = 30;
		ArrayList<RangoServicioDTO> listaRango1 = new ArrayList<RangoServicioDTO>();
		listaRango1.add(rango1);

		rango2.horarioDesde = 11;
		rango2.horarioHasta = 13;
		rango2.dia = 4;
		rango2.minutoDesde = 0;
		rango2.minutoHasta = 0;

		ArrayList<RangoServicioDTO> listaRango2 = new ArrayList<RangoServicioDTO>();

		listaRango2.add(rango2);
		listaRango2.add(rango1);

		ServicioDTO servicio1 = new ServicioDTO();

		ServicioDTO servicio2 = new ServicioDTO();

		servicio1.setNombre("servicio1");
		servicio1.setRango(listaRango1);

		servicio2.setNombre("servicio2");
		servicio2.setRango(listaRango2);

		centro.comuna = 15;
		centro.zonas = "Balvanera, San Cristóbal";
		centro.director = "Ariel";
		centro.domicilio = "Junin 521";
		centro.telefono = "7364739657936";
		centro.servicios = new ArrayList<ServicioDTO>();
		centro.servicios.add(servicio1);
		centro.servicios.add(servicio2);

		cgpNuevo = cgps.adaptarCGP(centro);

	}

	@Test

	public void CGPAdaptadoDebeSerAceptado() {

		Assert.assertSame(true, cgpNuevo.coincideConLaBusqueda("serv"));

	}

	@Test
	public void CGPAdaptadoDebeAceptarServicio1() {

		Assert.assertSame(true, cgpNuevo.coincideConLaBusqueda("servicio1"));
	}

	@Test
	public void CGPAdaptadoDebeAceptarServicio2() {

		Assert.assertSame(true, cgpNuevo.coincideConLaBusqueda("servicio2"));
	}

	@Test
	public void CGPAdaptadoNoDebeSerAceptado() {

		Assert.assertSame(false, cgpNuevo.coincideConLaBusqueda("serv21"));
	}

	@Test
	public void verificarBusquedaCgpEnSistemaExterno() {
		ServicioExternoCGP mock = mock(ServicioExternoCGP.class);
		IntegracionCgp cgp = IntegracionCgp.GetInstance();
		cgp.setServicioExterno(mock);
		cgp.buscar("15");
		verify(mock).buscar("15");
	}

	@Test
	public void verificarBusquedaCgpSistemaExternoDesdeUsuario() {
		ServicioExternoCGP mock = mock(ServicioExternoCGP.class);
		HomePois homePois = HomePois.GetInstancia();
		homePois.quitarIntegracion(IntegracionBancos.GetInstance());
		homePois.quitarIntegracion(IntegracionCgp.GetInstance());

		IntegracionCgp cgp = IntegracionCgp.GetInstance();

		cgp.setServicioExterno(mock);

		homePois.agregarIntegracion(cgp);

		//Terminal user = new Terminal(10, 10, "");
        //user.buscar("15");lo cambie para que busque directamente el homepois si no hay que inicializar la lista de acciones extras
		homePois.buscar("15", new Point(10, 10));
		
		verify(mock).buscar("15");

	}
}
