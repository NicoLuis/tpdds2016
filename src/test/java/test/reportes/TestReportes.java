package test.reportes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestReportes {

	FixtureReportes fixture = new FixtureReportes();

	@Before

	public void initialize() {

		fixture.init();

	}

	@Test
	public void laCantidadDeBusquedasEnLaFechaActualDebenSerTres() {

		int num = fixture.reporteFecha.getInfoReporte().get(0).cantBusquedas;

		Assert.assertEquals(3, num, 0);

	}

	@Test
	public void laCantidadDeFechasIngresadasEnElReporteDebenSerDos() {

		Assert.assertEquals(2, fixture.reporteFecha.getInfoReporte().size(), 0);
	}

	@Test
	public void laCantidadDeBusquedasEnLaFechaDebenSerDos() {

		int num = fixture.reporteFecha.getInfoReporte().get(1).cantBusquedas;

		Assert.assertEquals(2, num, 0);

	}

	@Test
	public void laCantidadDeTerminalesDebenSerDos() {
		int num = fixture.reporteTerminal.getInfoReporte().size();

		Assert.assertEquals(2, num, 0);
	}

	@Test
	public void laCantidadDeResultadosParaLaTerminal1DebeSer6() {

		Assert.assertEquals(6, fixture.reporteTerminal.getInfoReporte().get(0).cantResultados, 0);
	}

	@Test
	public void laCantidadDeResultadosParaLaTerminal2DebeSer7() {

		Assert.assertEquals(7, fixture.reporteTerminal.getInfoReporte().get(1).cantResultados, 0);
	}

	@Test
	public void laCantidadDeBusquedasParcialesEnlaTerminal1es2() {

		int num = fixture.reportePorResultadosParciales1.getInfoReporte().size();

		Assert.assertEquals(2, num, 0);

	}

	@Test
	public void elResultadoParcialEnLaPrimerBusquedaDeTerm1Es4() {
		Assert.assertEquals(4, fixture.reportePorResultadosParciales1.getInfoReporte().get(0).cantResultados, 0);
	}

	@Test
	public void elResultadoParcialEnLaPrimerBusquedaDeTerm1Es2() {
		Assert.assertEquals(2, fixture.reportePorResultadosParciales1.getInfoReporte().get(1).cantResultados, 0);
	}

	@Test
	public void laCantidadDeBusquedasParcialesSon5() {

		int num = fixture.reportePorResultadosParciales2.getInfoReporte().size();

		Assert.assertEquals(5, num, 0);
	}

	@Test
	public void elResultadoParcialEnLaTerceraBusquedaEs3() {
		Assert.assertEquals(3, fixture.reportePorResultadosParciales2.getInfoReporte().get(2).cantResultados, 0);
	}

}
