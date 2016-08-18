package sistExternos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import poi.Banco;
import poi.POI;

public class IntegracionBancos implements InterfazExterna{
	
	private ServicioExternoBanco servicioExterno;
	
	// Singleton ////////////////////////////////////////////////////////////////////////////////////
	static IntegracionBancos instancia;
	
	private IntegracionBancos(){
		servicioExterno = new ServicioExternoBanco();
	}
	
	public static IntegracionBancos GetInstance(){
		
		if(instancia == null){
			instancia = new IntegracionBancos();
		}
		return instancia;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String buscarEnSistBancario(String banco){
		
		return servicioExterno.buscar(banco, "extracciones");
	}
	
	public ArrayList<POI> buscar(String busqueda){
		
		return this.adaptarJson(this.buscarEnSistBancario(busqueda));
	}
	
	
	public ArrayList<POI> adaptarJson(String json) {

		Gson gson = new Gson();

		Type tipoListaBancos = new TypeToken<List<BancoJson>>() {
		}.getType();

		ArrayList<BancoJson> listaBancosJson = gson.fromJson(json, tipoListaBancos);
		// Ahora ya tengo la lista de bancos en objetos de la clase BancoJson
		// Tengo que pasarlos al tipo Banco

		ArrayList<POI> listaBancosDefinitiva = new ArrayList<POI>();

		for (BancoJson b : listaBancosJson) {

			Banco nuevoBanco = new Banco(b.banco, b.gerente, b.x, b.y);

			nuevoBanco.setNombreGerente(b.gerente);
			nuevoBanco.setListaServicios(Arrays.asList(b.servicios));
			// La lista de servicios tiene 8 strings, incluidos los vacios. Asi
			// que los saco
			nuevoBanco.setListaServicios(nuevoBanco.getListaServicios().stream().filter(s -> !s.equals(""))
					.collect(Collectors.toCollection(ArrayList::new)));

			listaBancosDefinitiva.add(nuevoBanco);
		}

		return listaBancosDefinitiva;
	}
	
	//Para Mockearlo
	public void setServicioExterno(ServicioExternoBanco servicioExterno){
		this.servicioExterno = servicioExterno;
	}

}
