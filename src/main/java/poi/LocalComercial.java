package poi;

import org.uqbar.geodds.Point;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class LocalComercial extends POI{
	public LocalComercial(Point unaUbicacion, Comuna comuna8, Rubro unRubro){
		
		super(unaUbicacion,comuna8);
		this.setRubro(unRubro);
	}
	public LocalComercial(){
		super();
	}
	private Rubro rubro;
	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	@Override
	public boolean coincideConLaBusqueda(String textoBusqueda){
		ArrayList<String> rubros= this.getRubro().getRubrosALosQuePertence();
		if(getNombre().contains(textoBusqueda) || rubros.contains(textoBusqueda) || this.isInTagsList(textoBusqueda)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean estaDisponible (LocalDateTime unTiempo){
		
		ArrayList<RangoDeAtencion> rangoLocal = super.getRangoDeAtencion();
		return rangoLocal.stream().anyMatch(rango -> rango.disponible(unTiempo));
		
	}
}
