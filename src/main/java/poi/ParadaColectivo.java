package poi;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class ParadaColectivo extends POI {

	//CONSTRUCTOR
	
	public ParadaColectivo(Point miUbicacion, Polygon area) {
		super();
		this.setUbicacion(miUbicacion);
		this.setComuna(area);
	}
	
	public ParadaColectivo(String nombre) {
		super();
		this.setNombre(nombre);
	}

	public ParadaColectivo(){}
	
	//METODOS
	
	@Override
	public double cercaniaRequerida(){
		return 100.0;
	}
	
	public boolean estaDisponible(LocalDateTime unTiempo){
		return true;
	}
	
	@Override
	public boolean coincideConLaBusqueda(String textoBusqueda){
		if(getNombre().contains(textoBusqueda) || this.isInTagsList(textoBusqueda)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String tipo(){
		return "Parada de Colectivo";
	}
	public void setIcono(){
		icono= "glyphicons glyphicons-bus";
	}
	public void setDetalles(){
		detalles.put("Nombre",this.getNombre());
		detalles.put("Icono",this.getIcono());
	}
}
