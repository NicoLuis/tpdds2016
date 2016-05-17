package main.java.bases;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import main.java.poi.*;

public class BasePOIs{
	private Comuna comuna8;
	private POI ubicacionCercana;
	private POI ubicacionLejana;
	private CGP cgp;
	private ParadaColectivo paradaColectivo_1;
	private SucursalBanco sucursalBanco_1;
	private LocalComercial libreriaEscolar;
	private LocalComercial kioskoDeDiarios;
	private Rubro rubroLibreriaEscolar;
	private Rubro rubroKioskoDeDiarios;
	
	public BasePOIs(){
		crearComuna8();
		crear_Rubros();
		crear_ubicacionCercana();
		crear_ubicacionLejana();
		crear_CGP_1();
		crear_ParadaColectivo_1();
		crear_SucursalBanco_1();
		crear_libreriaEscolar_1();
		crear_kioskoDeDiarios_1();
	}
	
	public Comuna crearComuna8(){
		Polygon zonaComuna8 = new Polygon();
		comuna8 = new Comuna();
		zonaComuna8.add(new Point(-34.6744,-58.5025));
		zonaComuna8.add(new Point(-34.6578,-58.4787));
		zonaComuna8.add(new Point(-34.6648,-58.4697));
		zonaComuna8.add(new Point(-34.6621,-58.4240));
		zonaComuna8.add(new Point(-34.7048,-58.4612));
		comuna8.setZona(zonaComuna8);
		return comuna8;
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
		cgp = new CGP();
		cgp.setNombre("CGP_1");
		cgp.setDireccion("Av Escalada 3100");
		cgp.setComuna(comuna8);
		cgp.setUbicacion(new Point(-34.6672, -58.4669));
		return cgp;
	}
	
	public ParadaColectivo crear_ParadaColectivo_1() {
		paradaColectivo_1 = new ParadaColectivo();
		paradaColectivo_1.setNombre("Parada del 47");
		paradaColectivo_1.setDireccion("Corvanalan 3691");
		paradaColectivo_1.setComuna(comuna8);
		paradaColectivo_1.setUbicacion(new Point(-34.6715, -58.4676));
		return paradaColectivo_1;
	}
	
	public SucursalBanco crear_SucursalBanco_1() {
		sucursalBanco_1 = new SucursalBanco();
		sucursalBanco_1.setNombre("Parada del 47");
		sucursalBanco_1.setDireccion("Av Riestra 5002");
		sucursalBanco_1.setComuna(comuna8);
		sucursalBanco_1.setUbicacion(new Point(-34.6719, -58.4695));
		return sucursalBanco_1;
	}

	public void crear_Rubros(){
		rubroKioskoDeDiarios = new Rubro(200.0);
		rubroLibreriaEscolar = new Rubro(500.0);
	}
	
	public LocalComercial crear_libreriaEscolar_1() {
		libreriaEscolar = new LocalComercial();
		libreriaEscolar.setNombre("Libreria Escolar");
		libreriaEscolar.setDireccion("Av Argentina 4802");
		libreriaEscolar.setComuna(comuna8);
		libreriaEscolar.setUbicacion(new Point(-34.6720, -58.4678));
		libreriaEscolar.setRubro(rubroLibreriaEscolar);
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
}