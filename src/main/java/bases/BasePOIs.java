package bases;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import poi.*;

public class BasePOIs{
	private Comuna comuna8;
	private POI ubicacionCercana;
	private POI ubicacionLejana;
	private CGP cgp_1;
	private CGP cgp_2;
	private ParadaColectivo paradaDel47;
	private ParadaColectivo paradaDel107;
	private ParadaColectivo paradaDel114;
	private SucursalBanco sucursalBanco_1;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Rubro rubroLibreriaEscolar;
	private Rubro rubroKioskoDeDiarios;
	private Rubro rubrosSupermercado;
	private Servicio rentas;
	private Servicio cajero;
	private LocalComercial supermercado;
	private POI localDeRopa;
	private POI casaDeComida;
	private Servicio atencionAlCliente;
	private Servicio asesoramiento;
	
	private ArrayList<Comuna> listaComunas;
	private ArrayList<POI> listaPois;
	
	public BasePOIs(){
		crearComuna8();
		crear_Rubros();
		crearServicios();
		crear_ubicacionCercana();
		crear_ubicacionLejana();
		crear_CGP_1();
		crear_CGP_2();
		crear_paradaDel47();
		crear_paradaDel107();
		crear_paradaDel114();
		crear_SucursalBanco_1();
		crear_libreriaEscolar_1();
		crear_kioskoDeDiarios_1();	
		crear_supermercado_1();
		crear_localDeRopa_1();
		crear_casaDeComida_1();
		crear_arrayComunas();
		crear_arrayPOIs();
	}
	
//////////////////////////////				CONSTRUCTORES				//////////////////////////////
	
	public Comuna crearComuna8(){
		Polygon zonaComuna8 = new Polygon();
		comuna8 = new Comuna();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setNombre("Comuna 8");
		comuna8.setZona(zonaComuna8);
		return comuna8;
	}
	
	public void crearServicios(){
		rentas = new Servicio("Rentas", new RangoDeAtencion(7.30,19.30,1,5));
		cajero = new Servicio("Cajero", new RangoDeAtencion(10,15.30,1,5));
		asesoramiento = new Servicio("Asesoramiento", new RangoDeAtencion(10,15.30,1,5));
		atencionAlCliente = new Servicio("AtencionAlCliente", new RangoDeAtencion(10,15.30,1,5));
	}

	
	public POI crear_ubicacionCercana() {
		ubicacionCercana = new POI();
		ubicacionCercana.setNombre("ubicacionCercana");
		ubicacionCercana.setDireccion("Sayos 4937");
		ubicacionCercana.setComuna(comuna8);
		ubicacionCercana.setUbicacion (new Point(-34.6717, -58.4679));
		return ubicacionCercana;
	}
	
	public POI crear_ubicacionLejana() {
		ubicacionLejana = new POI();
		ubicacionLejana.setNombre("ubicacionLejana");
		ubicacionLejana.setDireccion("Av. Juan B. Justo 4045 ");
		ubicacionLejana.setComuna(comuna8);
		ubicacionLejana.setUbicacion(new Point(-34.6048, -58.4591));
		return ubicacionLejana;
	}
	
	public CGP crear_CGP_1() {
		cgp_1 = new CGP();
		cgp_1.setNombre("CGP_1");
		cgp_1.setDireccion("Av Escalada 3100");
		cgp_1.setComuna(comuna8);
		cgp_1.setUbicacion(new Point(-34.6672, -58.4669));
		cgp_1.addServicio(rentas);
		return cgp_1;
	}
	
	public CGP crear_CGP_2() {
		cgp_2 = new CGP();
		cgp_2.setNombre("CGP_2");
		cgp_2.setDireccion("Murguiondo 3457");
		cgp_2.setComuna(comuna8);
		cgp_2.setUbicacion(new Point(-34.6705, -58.4841));
		cgp_2.addServicio(asesoramiento);
		cgp_2.addServicio(atencionAlCliente);
		return cgp_2;
	}
	
	public ParadaColectivo crear_paradaDel47() {
		paradaDel47 = new ParadaColectivo();
		paradaDel47.setNombre("Parada del 47");
		paradaDel47.setDireccion("Corvanalan 3691");
		paradaDel47.setComuna(comuna8);
		paradaDel47.setUbicacion(new Point(-34.6715, -58.4676));
		return paradaDel47;
	}
	
	public ParadaColectivo crear_paradaDel107() {
		paradaDel107 = new ParadaColectivo();
		paradaDel107.setNombre("Parada del 107");
		paradaDel107.setDireccion("Av. Eva Peron 4900");
		paradaDel107.setComuna(comuna8);
		paradaDel107.setUbicacion(new Point(-34.6578,-58.4787));
		return paradaDel107;
	}
	
	public ParadaColectivo crear_paradaDel114() {
		paradaDel114 = new ParadaColectivo();
		paradaDel114.setNombre("Parada del 114");
		paradaDel114.setDireccion("Corvanalan 3691");
		paradaDel114.setComuna(comuna8);
		paradaDel114.setUbicacion(new Point(-34.6715, -58.4676));
		return paradaDel114;
	}
	
	public SucursalBanco crear_SucursalBanco_1() {
		sucursalBanco_1 = new SucursalBanco();
		sucursalBanco_1.setNombre("Sucursal Banco");
		sucursalBanco_1.setDireccion("Av Riestra 5002");
		sucursalBanco_1.setComuna(comuna8);
		sucursalBanco_1.setUbicacion(new Point(-34.6719, -58.4695));
		return sucursalBanco_1;
	}

	public void crear_Rubros(){
		rubroKioskoDeDiarios = new Rubro(200.0);
		rubroLibreriaEscolar = new Rubro(500.0);
		rubroLibreriaEscolar.addRubrosALosQuePertenece("libreria");
		rubroLibreriaEscolar.addRubrosALosQuePertenece("papelera");
		rubrosSupermercado = new Rubro(-34.6621);
		rubrosSupermercado.addRubrosALosQuePertenece("libreria");
		rubrosSupermercado.addRubrosALosQuePertenece("verduleria");
		rubrosSupermercado.addRubrosALosQuePertenece("carniceria");
		rubrosSupermercado.addRubrosALosQuePertenece("kiosko");
	}
	
	public LocalComercial crear_libreriaEscolar_1() {
		libreriaEscolar = new LocalComercial();
		libreriaEscolar.setNombre("Libreria Escolar");
		libreriaEscolar.setDireccion("Av Argentina 4802");
		libreriaEscolar.setComuna(comuna8);
		libreriaEscolar.setUbicacion(new Point(-34.6720, -58.4678));
		libreriaEscolar.setRubro(rubroLibreriaEscolar);
		libreriaEscolar.setRangoDeAtencion( new ArrayList<RangoDeAtencion>() );
		libreriaEscolar.addRangoDeAtencion( new RangoDeAtencion(10, 18, 1, 5) );
		return libreriaEscolar;
	}
	
	public LocalComercial crear_kioskoDeDiarios_1() {
		kioskoDeDiarios = new LocalComercial();
		kioskoDeDiarios.setNombre("Kiosko de Diarios");
		kioskoDeDiarios.setDireccion("Albariño 3702");
		kioskoDeDiarios.setComuna(comuna8);
		kioskoDeDiarios.setUbicacion(new Point(-34.6717, -58.4673));
		kioskoDeDiarios.setRubro(rubroKioskoDeDiarios);
		return kioskoDeDiarios;
	}
	
	
	public ArrayList<POI> crear_arrayPOIs(){
		setListaPois(new ArrayList<POI>());
		getListaPois().add(ubicacionCercana);
		getListaPois().add(ubicacionLejana);
		getListaPois().add(cgp_1);
		getListaPois().add(cgp_2);
		getListaPois().add(paradaDel47);
		getListaPois().add(paradaDel107);
		getListaPois().add(paradaDel114);
		getListaPois().add(sucursalBanco_1);
		getListaPois().add(libreriaEscolar);
		getListaPois().add(kioskoDeDiarios);
		getListaPois().add(supermercado);
		getListaPois().add(localDeRopa);
		getListaPois().add(casaDeComida);
		return getListaPois();
	}
	
	public ArrayList<Comuna> crear_arrayComunas(){
		setListaComunas(new ArrayList<Comuna>());
		getListaComunas().add(crearComuna8());
		return getListaComunas();
	}
	
	public LocalComercial crear_supermercado_1(){
		supermercado = new LocalComercial();
		supermercado.setNombre("Supermercado");
		supermercado.setRubro(rubrosSupermercado);
		return supermercado;
	}
	
	public POI crear_localDeRopa_1(){
		localDeRopa = new POI();
		localDeRopa.setNombre("Local de Ropa");
		ArrayList<String> tagsDeLocalDeRopa = new ArrayList<String>();
		tagsDeLocalDeRopa.add("casual");
		tagsDeLocalDeRopa.add("deportiva");
		tagsDeLocalDeRopa.add("elegante");
		localDeRopa.setTags(tagsDeLocalDeRopa);;
		return localDeRopa;
	}
	
	public POI crear_casaDeComida_1(){
		casaDeComida = new POI();
		casaDeComida.setNombre("Casa de Comida");
		ArrayList<String> tagsDeCasaDeComida = new ArrayList<String>();
		tagsDeCasaDeComida.add("almuerzo");
		tagsDeCasaDeComida.add("desayuno");
		tagsDeCasaDeComida.add("cena");
		casaDeComida.setTags(tagsDeCasaDeComida);
		return casaDeComida;
	}
	
	
	//////////////////////////////				FUNCIONES				//////////////////////////////
	
	
	public Stream<POI> coincideConLaBusqueda(ArrayList<POI> listapois, String string){
		Stream<POI> listaFiltrada = 
				listapois.stream().filter(poi -> poi.coincideConLaBusqueda(string));
		return listaFiltrada;
	}

	public ArrayList<POI> getListaPois() {
		return listaPois;
	}

	public void setListaPois(ArrayList<POI> listaPois) {
		this.listaPois = listaPois;
	}

	public ArrayList<Comuna> getListaComunas() {
		return listaComunas;
	}

	public void setListaComunas(ArrayList<Comuna> listaComunas) {
		this.listaComunas = listaComunas;
	}
	
	
	
	
}