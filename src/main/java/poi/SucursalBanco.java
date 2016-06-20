package poi;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class SucursalBanco extends POI{
	
	public SucursalBanco(Point ubicacion, Comuna comuna8) {
		super(ubicacion, comuna8);		
	}
	public SucursalBanco(){}

	public double cercaniaRequerida(){
		return 500.0;
	}
	
	public boolean estaDisponible (LocalDateTime unTiempo){	
		RangoDeAtencion rangoLocal = new RangoDeAtencion(10, 15, 1, 5);
		return (rangoLocal.disponible(unTiempo));
	}
}
