package main.java.poi;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.uqbar.geodds.Point;

public class SucursalBanco extends POI{
	
	public SucursalBanco(Point ubicacion, Comuna comuna8) {
		super(ubicacion, comuna8);		
	}

	public double cercaniaRequerida(){
		return 500.0;
	}
	public boolean estaDisponible (LocalDateTime unTiempo, Servicio Valor){
		ArrayList<Servicio> servicioDisponible = this.getComuna().getServiciosQueBrinda();
		RangoDeAtencion rangoBanco = super.getRangoDeAtencion();		
		if (servicioDisponible.contains(Valor) && rangoBanco.disponible(unTiempo)){
			RangoDeAtencion rangoServicio = Valor.getRangoDeAtencion();
			return (rangoServicio.disponible(unTiempo));
		}
		else{
			
			return false;
		}
	}
}
