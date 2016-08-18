package principal;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;

import poi.Franja;
import poi.LocalComercial;
import poi.Rubro;


public class TestLocal {
	Point punto;
	private LocalComercial local = new LocalComercial();
	private LocalComercial local1 = new LocalComercial();
	private LocalComercial local2 = new LocalComercial();
	private LocalComercial local3 = new LocalComercial();


	@Before
	public void init(){
		local.setNombre("Venta de ropa");
		local.setRubro(new Rubro("Ropa", 200));
		local1.setNombre("Venta de Buzos");
		local1.setRubro(new Rubro("Ropa", 100));
		local2.setNombre("Venta de Remeras");
		local2.setRubro(new Rubro("Ropa", 100));
		local3.setNombre("Venta de Ropa");
		local3.setRubro(new Rubro("Carrousel", 299));
	}

	@Test
	public void localesAceptadosPorUnaBusquedaDeUserPorRubro() {
		Assert.assertTrue(local1.coincideConLaBusqueda("Ropa"));
		Assert.assertTrue(local2.coincideConLaBusqueda("Ropa"));
	}

	@Test
	public void localesAceptadosPorUnaBusquedaDeUserPorNombreLocal() {

		Assert.assertFalse(local1.coincideConLaBusqueda("Buzos") && local2.coincideConLaBusqueda("Buzos"));
	}
	@Test

	public void carrouselEstaDisponibleElMiercoles4aLas11Y25() {
		Franja franja = new Franja(LocalTime.of(10, 00), LocalTime.of(13, 00), 1, 6);
		local3.addFranjaHoraria(franja);
		LocalDateTime.of(2016, 5, 4, 11, 25);
		Assert.assertTrue(local3.getFranjaHoraria().get(0).estaDisponible(LocalDateTime.of(2016, 5, 4, 11, 25)));

	}



}


