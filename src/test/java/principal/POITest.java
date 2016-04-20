package test.java.principal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import main.java.poi.POI;
import main.java.principal.*;

import org.junit.Before;
import org.junit.Test;

public class POITest {

	private Coordenada coordenada_1, coordenada_2;
	private POI poi_1, poi_2;
	private Collection <POI> listaPOIs;
	
	@Before
	public void setUp() {
		
		coordenada_1 = new Coordenada();
		coordenada_1.setLatitud(90,0,0,'N');
		coordenada_1.setLongitud(90,10,10,'W');
		
		coordenada_2 = new Coordenada();
		coordenada_2.setLatitud(52,0,0,'N');
		coordenada_2.setLongitud(0,0,0.18,'E');
		
		poi_1 = new POI();
		poi_1.setNombre("POI 1");
		poi_1.setCoordenada(coordenada_1);
		
		poi_2 = new POI();
		poi_2.setCoordenada(coordenada_2);
		
		listaPOIs = new ArrayList<>();
		listaPOIs.add(poi_2);
	}
	
	
	
	@Test
	public void poi_1esValido(){
		assertTrue(poi_1.esValido());
	}
	
	@Test
	public void poi_2NOesValidoPorqueNoTieneNombre(){
		assertFalse(poi_2.esValido());
	}
	
	
	@Test
	public void poi_1seEncuentraAMenosDe4300000MetrosDepoi_2(){
		assertTrue( poi_1.seEncuentraAMenosDe( 4300000, poi_2 ) );
	}
	
	@Test
	public void poi_1seEncuentraAMenosDe4300000MetrosDeOtroPOI(){
		assertTrue( poi_1.seEncuentraAMenosDeOtroPOI( 4300000, listaPOIs ) );
	}
}
