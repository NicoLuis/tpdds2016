package main.java.poi;
import java.util.Collection;
import java.util.ArrayList;
import java.lang.String;
import org.uqbar.geodds.Point;

public class POI {
	public POI(Point unaUbicacion, Comuna unaComuna) {
		this.setUbicacion(unaUbicacion);
		this.setTags(); //Para inicializar el Array
		this.setComuna(unaComuna);
	}
	public POI() {}
	private Point 					ubicacion;
	private String 					nombre;
	private String 					direccion;
	private Comuna	 				comuna;
	private ArrayList<String> 		tags; //Array de String que contienen todos los tags de busqueda libre
	private RangoDeAtencion		 	rangoDeAtencion; 
	
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return this.getUbicacion().distance(otroPOI.getUbicacion())*1000 < metros;	// Para pasarlo a metros
	}
	
	public boolean seEncuentraAMenosDeOtroPOI(double metros, Collection<POI> listaPOIs){ 
		return listaPOIs.stream().anyMatch(poi -> this.estaAMenosDeXMetrosDeOtroPOI(poi, 340000) );
	}
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getUbicacion().distance(unaUbicacion) * 1000 < this.cercaniaRequerida();
	}
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	public boolean estaCercaDeUnPOI(POI unPOI){
		return this.estaAMenosDeXMetrosDeOtroPOI(unPOI, 500); //para pasar a metros
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getUbicacion()!= null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null && this.getNombre() != "";
	}
	//getters y setters
	public RangoDeAtencion getRangoDeAtencion() {
		return rangoDeAtencion;
	}

	public void setRangoDeAtencion(RangoDeAtencion rangoDeAtencion) {
		this.rangoDeAtencion = rangoDeAtencion;
	}
	
	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	
	public Point getUbicacion(){
		return ubicacion;
	}
	
	public void setUbicacion(Point unaUbicacion){
		ubicacion = unaUbicacion;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String unaDireccion) {
		direccion = unaDireccion;
	}
	
	public void setTags(){ //Inicializa el ArrayList
		tags = new ArrayList<String>();
	}
	
	public void addTag(String tag){//Agrega un tag al ArrayList
		tags.add(tag);
	}
	
	public void removeTag(String tag){
		tags.remove(tag);
	}
	
}