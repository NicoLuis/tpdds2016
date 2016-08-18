package test.sistExternos;

import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import sistExternos.*;
import poi.*;

public class TestIntegracionBancos {
	
	private String json = "["+
   "{ \"banco\": \"Banco de la Plaza\"," +
   "   \"x\": -35.9338322, "+
   "   \"y\": 72.348353,"+
   "   \"sucursal\": \"Avellaneda\","+
   "   \"gerente\": \"Javier Loeschbor\","+
   "   \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"+
   "},"+
   "{ \"banco\": \"Banco de la Plaza\","+
   "   \"x\": -35.9345681,"+
   "   \"y\": 72.344546,"+
   "   \"sucursal\": \"Caballito\","+
   "   \"gerente\": \"Fabián Fantaguzzi\","+
   "   \"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]"+
   "}]";
	
	private ArrayList<POI> listaBancos;
	private IntegracionBancos banco;

	@Before
	public void init(){

		banco = IntegracionBancos.GetInstance();
		
		listaBancos = new ArrayList<POI>();
		
		listaBancos = banco.adaptarJson(json);
	}
	
	@Test
	public void debeDevolver2Bancos(){
		
		Assert.assertEquals(2,listaBancos.size(),0);
	}
	
	@Test
	public void debeTener5Servicios(){
		
		Assert.assertEquals(5, ((Banco) listaBancos.get(0)).getListaServicios().size());
	}
	
	@Test
	public void debeTenerNombreMismoNombre(){
		
		Assert.assertEquals(true, "Banco de la Plaza".equals(listaBancos.get(0).getNombre()));
	}
	
	@Test
	public void verificarBusquedaBancoEnSistBancario() {

		ServicioExternoBanco mock = mock(ServicioExternoBanco.class);
		when(mock.buscar("Banco", "extracciones")).thenReturn(json);
		
		IntegracionBancos banco = IntegracionBancos.GetInstance();
		banco.setServicioExterno(mock);
		
		banco.buscar("Banco");
	}

}
