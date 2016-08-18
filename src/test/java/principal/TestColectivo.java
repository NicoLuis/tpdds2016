package principal;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import poi.*;
import org.uqbar.geodds.*;

public class TestColectivo {

	private ParadaColectivo colectivo = new ParadaColectivo("114");
	Point punto;

	@Test
	public void colectivoEstaCercaDeUsuario() {

		colectivo.setCoordenadas(5, 5);
		punto = new Point(4.999999, 4.999999);

		Assert.assertSame(true, colectivo.estaCerca(punto));

	}

	@Test
	public void siempreDisponible() {
		Franja franja = new Franja(LocalTime.of(0, 0), LocalTime.of(23, 59), 1, 7);
		
		colectivo.addFranjaHoraria(franja);

		Assert.assertSame(true, colectivo.estaDisponible());

	}

	@Test

	public void colectivoESAceptado() {

		Assert.assertSame(false, colectivo.coincideConLaBusqueda("115"));

	}
}
