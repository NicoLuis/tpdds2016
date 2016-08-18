package principal;

import org.junit.*;
import org.uqbar.geodds.*;
import poi.*;

public class TestCGP {

	private Polygon area = new Polygon();
	private Servicio servicio = new Servicio("Banco");
	private CGP cgp = new CGP(area, servicio, "Gestion de Bancos");

	@Test
	public void cgpEsAceptadoPorUser() {

		area.add(new Point(10, 10));
		area.add(new Point(0, 0));
		area.add(new Point(0, 10));
		area.add(new Point(10, 0));
		
		Point coordenadas = new Point(2,3);
		
		Assert.assertSame(true, cgp.estaCerca(coordenadas));
	}

	@Test
	public void cgpDeberiaSerAceptado() {
		Assert.assertSame(true, cgp.coincideConLaBusqueda("Banco"));
	}
}