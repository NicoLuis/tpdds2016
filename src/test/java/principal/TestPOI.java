package principal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.reportes.FixtureReportes;

public class TestPOI {

	FixtureReportes fixture = new FixtureReportes();

	@Before

	public void initialize() {

		fixture.init();

	}

	@Test

	public void todosLOsPOIDebenTenerUnUnicoNumero() {

		Assert.assertFalse(fixture.cgp.getNumeroPOI() == fixture.colectivo1.getNumeroPOI()
				&& fixture.colectivo2.getNumeroPOI() == fixture.colectivo3.getNumeroPOI());

	}

}
