package bases;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import poi.*;
import sistExternos.IntegracionBancos;
import sistExternos.IntegracionCgp;
import sistExternos.InterfazExterna;

public class HomePois{
	
	private ArrayList<POI> listaPois;
	private Collection<InterfazExterna> integraciones;

	//Singleton ////////////////////////////////////////////////////////////////////////////////
	static HomePois instancia;
	
	private HomePois(){
		listaPois = new ArrayList<POI>();
		integraciones = new HashSet<InterfazExterna>();
		
		integraciones.add(IntegracionBancos.GetInstance());
		integraciones.add(IntegracionCgp.GetInstance());
		crearPOIs();
	}
	
	public static HomePois GetInstancia(){
		
		if(instancia == null){
			instancia = new HomePois();
		}
		return instancia;
	}
	public static void reset(){
		instancia = new HomePois();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//Manejo de ListaPois/////////////////////////////////////////////////////////////////////////////
	public void agregarPoi(POI poi){
		this.listaPois.add(poi);
	}
	
	public void quitarPoi(POI poi){
		this.listaPois.remove(poi);
	}
	
	public void removerPoiPorNumero(int num){
		this.listaPois.removeIf(p -> p.getNumeroPOI()== num);
	}
	public void removerPoiPorBusqueda(String textoBusqueda){
		this.listaPois.removeIf(p -> p.coincideConLaBusqueda(textoBusqueda));	
	}
	
	public void modificar(POI poi){
		POI poiAModificar = buscarNombre(poi.getNombre());
		listaPois.remove(poiAModificar);
		listaPois.add(poi);
	}
	
	public int cantidadPois(){
		return listaPois.size();
	}
	//Fin manejo listaPoi/////////////////////////////////////////////////////////////////////////////
	
	
	//Manejo integraciones ///////////////////////////////////////////////////////////////////////////
	public void agregarIntegracion(InterfazExterna integracion){
		integraciones.add(integracion);
	}
	public void quitarIntegracion(InterfazExterna integracion){
		integraciones.remove(integracion);
	}
	public int cantidadIntegraciones(){
		return integraciones.size();
	}
	//Fin manejo integraciones ///////////////////////////////////////////////////////////////////////
	
	
	//Busquedas //////////////////////////////////////////////////////////////////////////////////////
	public List<POI> buscar(String busqueda, Point coordenada){
		List<POI> listaLocal = new ArrayList<POI>();
		listaLocal = listaPois.stream()
					.filter(p -> p.coincideConLaBusqueda(busqueda) )
					.filter(p -> p.estaCerca(coordenada) )
					.filter(p -> p.estaDisponible() )
					.collect(Collectors.toList());
		for(InterfazExterna integracion: integraciones){
			listaLocal.addAll(integracion.buscar(busqueda));
		}
		return listaLocal;
	}
	
	public List<POI> buscarNombrePOIS(String busqueda){
		return listaPois.stream().filter(u -> (u.getNombre()).contains(busqueda)).collect(Collectors.toList());
	}
	
	public POI buscarNombre(String busqueda){
		return listaPois.stream().filter(u -> (u.getNombre()).equals(busqueda)).findFirst().get();
	}
	
	public List<POI> puntosCercanos(Point coordenada){
		return listaPois.stream().filter(p -> p.estaCerca(coordenada)).collect(Collectors.toList());
	}
	
	public List<POI> puntosAceptados(String busqueda){
		return listaPois.stream().filter(p -> p.coincideConLaBusqueda(busqueda)).collect(Collectors.toList());
	}
	
	public List<POI> puntosDisponibles(){
		return listaPois.stream().filter(p -> p.estaDisponible()).collect(Collectors.toList());
	}
	
	//Devuelvo la lista solo de locales comerciales
	public List<POI> getLocales(){
		return listaPois.stream().filter(p -> p.getClass() == LocalComercial.class).collect(Collectors.toList());
	}
	//Fin busquedas //////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////				FUNCIONES				//////////////////////////////
	
	
	public Stream<POI> coincideConLaBusqueda(String string){
		return listaPois.stream().filter(poi -> poi.coincideConLaBusqueda(string));
	}

	public ArrayList<POI> getListaPois() {
		return listaPois;
	}

	public void setListaPois(ArrayList<POI> listaPoisNueva) {
		listaPois = listaPoisNueva;
	}
	
	public void addListaPois(POI poi) {
		listaPois.add(poi);
	}
	

	
	
	
	
	//////////////////////////////				REPO POIS				//////////////////////////////
	
	private Polygon zonaComuna8;
	private POI ubicacionCercana;
	private POI ubicacionLejana;
	private CGP cgp_1;
	private CGP cgp_2;
	private ParadaColectivo paradaDel47;
	private ParadaColectivo paradaDel107;
	private ParadaColectivo paradaDel114;
	private Banco sucursalBanco_1;
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

	
	public void crearPOIs(){
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
		crear_arrayPOIs();
	}
	
//////////////////////////////				CONSTRUCTORES				//////////////////////////////
	
	public Polygon crearComuna8(){
		zonaComuna8 = new Polygon();
		
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		
		return zonaComuna8;
	}
	
	public void crearServicios(){
		rentas = new Servicio("Rentas", new Franja(7,30,19,30,1,5) );
		cajero = new Servicio("Cajero", new Franja(10,0,15,30,1,5));
		asesoramiento = new Servicio("Asesoramiento", new Franja(10,0,15,30,1,5));
		atencionAlCliente = new Servicio("AtencionAlCliente", new Franja(10,0,15,30,1,5));
	}

	
	public POI crear_ubicacionCercana() {
		ubicacionCercana = new CGP();
		ubicacionCercana.setNombre("ubicacionCercana");
		ubicacionCercana.setComuna(zonaComuna8);
		ubicacionCercana.setUbicacion (new Point(-34.6717, -58.4679));
		return ubicacionCercana;
	}
	
	public POI crear_ubicacionLejana() {
		ubicacionLejana = new CGP();
		ubicacionLejana.setNombre("ubicacionLejana");
		ubicacionLejana.setComuna(zonaComuna8);
		ubicacionLejana.setUbicacion(new Point(-34.6048, -58.4591));
		return ubicacionLejana;
	}
	
	public CGP crear_CGP_1() {
		cgp_1 = new CGP();
		cgp_1.setNombre("CGP_1");
		cgp_1.setUbicacion(new Point(-34.6672, -58.4669));
		cgp_1.setComuna(zonaComuna8);
		cgp_1.addServicio(rentas);
		return cgp_1;
	}
	
	public CGP crear_CGP_2() {
		cgp_2 = new CGP();
		cgp_2.setNombre("CGP_2");
		cgp_2.setUbicacion(new Point(-34.6705, -58.4841));
		cgp_2.setComuna(zonaComuna8);
		cgp_2.addServicio(asesoramiento);
		cgp_2.addServicio(atencionAlCliente);
		return cgp_2;
	}
	
	public ParadaColectivo crear_paradaDel47() {
		Direccion dir = new Direccion();
		paradaDel107.setDireccion(new Direccion("Triunvirato", 4566));
		paradaDel47.setNombre("Parada del 47");
		paradaDel47.setDireccion(dir);
		paradaDel47.setUbicacion(new Point(-34.6715, -58.4676));
		return paradaDel47;
	}
	
	public ParadaColectivo crear_paradaDel107() {
		paradaDel107 = new ParadaColectivo();
		paradaDel107.setDireccion(new Direccion("Av. Escalada", 2214)); 
		paradaDel107.setNombre("Parada del 107");
		paradaDel107.setUbicacion(new Point(-34.6578,-58.4787));
		return paradaDel107;
	}
	
	public ParadaColectivo crear_paradaDel114() {
		paradaDel114 = new ParadaColectivo();
		paradaDel114.setDireccion(new Direccion("Av. Lacarra", 2254));
		paradaDel114.setNombre("Parada del 114");
		paradaDel114.setUbicacion(new Point(-34.6715, -58.4676));
		return paradaDel114;
	}
	
	public Banco crear_SucursalBanco_1() {
		sucursalBanco_1 = new Banco();
		sucursalBanco_1.setDireccion(new Direccion("Av. Riestra", 5012));
		sucursalBanco_1.setNombre("Sucursal Banco");
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
		libreriaEscolar.setUbicacion(new Point(-34.6720, -58.4678));
		libreriaEscolar.setDireccion(new Direccion("Av Caseros", 3211));
		libreriaEscolar.setRubro(rubroLibreriaEscolar);
		libreriaEscolar.setFranjaHoraria( new ArrayList<Franja>() );
		libreriaEscolar.addFranjaHoraria( new Franja(10, 18, 1, 5) );
		return libreriaEscolar;
	}
	
	public LocalComercial crear_kioskoDeDiarios_1() {
		kioskoDeDiarios = new LocalComercial();
		kioskoDeDiarios.setNombre("Kiosko de Diarios");
		kioskoDeDiarios.setUbicacion(new Point(-34.6717, -58.4673));
		libreriaEscolar.setDireccion(new Direccion("Corvalan", 3691));
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
		getListaPois().add(supermercado);
		getListaPois().add(kioskoDeDiarios);
		getListaPois().add(localDeRopa);
		getListaPois().add(casaDeComida);
		return getListaPois();
	}
	
	public LocalComercial crear_supermercado_1(){
		supermercado = new LocalComercial();
		supermercado.setNombre("Supermercado");
		supermercado.setRubro(rubrosSupermercado);
		supermercado.setDireccion(new Direccion("Santander", 5423));
		return supermercado;
	}
	
	public POI crear_localDeRopa_1(){
		localDeRopa = new LocalComercial();
		supermercado.setDireccion(new Direccion("Santander", 1214));
		localDeRopa.setNombre("Local de Ropa");
		ArrayList<String> tagsDeLocalDeRopa = new ArrayList<String>();
		tagsDeLocalDeRopa.add("casual");
		tagsDeLocalDeRopa.add("deportiva");
		tagsDeLocalDeRopa.add("elegante");
		localDeRopa.setTags(tagsDeLocalDeRopa);;
		return localDeRopa;
	}
	
	public POI crear_casaDeComida_1(){
		casaDeComida = new LocalComercial();
		supermercado.setDireccion(new Direccion("Monte", 4805));
		casaDeComida.setNombre("Casa de Comida");
		ArrayList<String> tagsDeCasaDeComida = new ArrayList<String>();
		tagsDeCasaDeComida.add("almuerzo");
		tagsDeCasaDeComida.add("desayuno");
		tagsDeCasaDeComida.add("cena");
		casaDeComida.setTags(tagsDeCasaDeComida);
		return casaDeComida;
	}
	
	
	
}