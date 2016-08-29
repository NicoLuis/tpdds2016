package poi;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public abstract class POI {
	
	static int 					MaxNumeroPOI = 0;
	protected int 				numeroPOI;
	protected Point 			coordenadas = new Point(0,0);	
	protected String 			nombre;
	protected Direccion 		direccion;
	protected Polygon			comuna;
	protected List<Franja> 		franjaHoraria = new ArrayList<Franja>();
	protected ArrayList<String>	tags; //Array de String que contienen todos los tags de busqueda libre


	protected POI(){
		numeroPOI= MaxNumeroPOI;
		MaxNumeroPOI++;
		setTags();
		this.setDireccion(new Direccion(""));
	}

	
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return coordenadas.distance(otroPOI.getCoordenadas())*1000 < metros;	// Para pasarlo a metros
	}
	
	public boolean seEncuentraAMenosDeOtroPOI(double metros, Collection<POI> listaPOIs){ 
		return listaPOIs.stream().anyMatch(poi -> this.estaAMenosDeXMetrosDeOtroPOI(poi, 340000) );
	}
	public boolean estaCerca(Point unaUbicacion){
		return coordenadas.distance(unaUbicacion) * 1000 <= this.cercaniaRequerida();
	}
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getCoordenadas()!= null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null && this.getNombre() != "";
	}
	
	
	
	
	
	public boolean coincideConLaBusqueda(String textoBusqueda){
		return this.getNombre().contains(textoBusqueda);
	}
	public boolean isInTagsList(String textoBusqueda){
		return this.getTags().contains(textoBusqueda);
	}
	
	public Point resultadosDeBusquedaLibre(String textoBusqueda){
		if(this.coincideConLaBusqueda(textoBusqueda))
			return this.coordenadas;
		return null;
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo) {
		return franjaHoraria.stream().filter(f -> f.estaDisponible(unTiempo)).collect(Collectors.toList())
				.size() > 0;
	}
	
	public boolean estaDisponible() {
		return franjaHoraria.stream().filter(f -> f.estaDisponible(LocalDateTime.now())).collect(Collectors.toList())
				.size() > 0;
	}
	
	
	//getters y setters

	public int getNumeroPOI() {
		return numeroPOI;
	}
	public Point getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(double x, double y) {
		coordenadas = new Point(x, y);
	}
	public double distancia(POI poi) {
		return coordenadas.distance(poi.getCoordenadas());
	}
	public List<Franja> getFranjaHoraria() {
		return franjaHoraria;
	}
	public void setFranjaHoraria(List<Franja> franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}
	public void addFranjaHoraria(Franja franjaHoraria) {
		this.franjaHoraria.add(franjaHoraria);
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion dir){
		this.direccion = dir;
	}

	//*///////////////*///*///////////////*///*///////////////*///*///////////////*/
	public String getNombre(){
		return this.nombre;
	}
	public void setNombre(String nombreNuevo){
		this.nombre = nombreNuevo;
	}
	public void setComuna(Polygon comuna){
		this.comuna = comuna;
	}
	public void setTags(ArrayList<String> list){
		tags = list;
	}
	public void setTags(){ 					//Inicializa el ArrayList
		tags = new ArrayList<String>();
	}
	public ArrayList<String> getTags(){
		return tags;
	}
	
	public void addTag(String tag){			//Agrega un tag al ArrayList
		tags.add(tag);
	}
	
	public void removeTag(String tag){
		tags.remove(tag);
	}
	public void setUbicacion(Point point) {
		this.coordenadas = point;
	}

	
}