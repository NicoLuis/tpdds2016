package main.java.poi;
import java.time.LocalDateTime;
import org.uqbar.geodds.Point;

public class ParadaColectivo extends POI {

	//CONSTRUCTOR
	
	public ParadaColectivo(Point miUbicacion, Comuna comuna8) {
		super(miUbicacion, comuna8);
	}
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
		String nombre= this.getNombre();
		if(textoBusqueda== nombre || this.isInTagsList(textoBusqueda)){
			return true;
		}
		else{
			return false;
		}
	}
}
