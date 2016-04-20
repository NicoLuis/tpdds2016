package test.java.principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.principal.*;

public class CoordenadaTest {

	private Coordenada coordenada_1, coordenada_2;
	
	@Before
	
	public void setUp() {
		coordenada_1 = new Coordenada();
		coordenada_1.setLatitud(90,0,0,'N');
		coordenada_1.setLongitud(90,10,10,'W');
		
		coordenada_2 = new Coordenada();
		coordenada_2.setLatitud(52,0,0,'N');
		coordenada_2.setLongitud(0,0,0.18,'E');
	}

	
	@Test
	public void decimalHaceBienLaCuenta()
	{
		assertEquals( 90, coordenada_1.getDecimalLat(), 0 );
		assertEquals( - (90 + ((double)10)/60 + ((double)10)/3600), coordenada_1.getDecimalLon(), 0 );
	}
	
	@Test
	public void calcularDistanciaHaceBienLaCuenta()
	{
		assertEquals( 0, coordenada_1.calcularDistancia( 90, - (90 + ((double)10)/60 + ((double)10)/3600)) , 0 );
		assertEquals( 50.97, coordenada_2.calcularDistancia( 52.000459, 0.000017) , 1 );		//distancia obtenida de google maps
	}

	
}
