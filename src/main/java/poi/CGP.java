package main.java.poi;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;
import java.util.ArrayList;

public class CGP extends POI{
	//CONSTRUCTOR
	
	
		public CGP(Point ubicacion, Comuna unaComuna) {
			super(ubicacion, unaComuna);
				
		}
		public CGP(){}
		
		    
		
		@Override
		public boolean estaCercaDe(Point unaUbicacion){
			return this.getComuna().getZona().isInside(unaUbicacion);
		}
		
		@Override
		public boolean coincideConLaBusqueda(String textoBusqueda){
			ArrayList<Servicio> serviciosQueBrinda= this.getComuna().getServiciosQueBrinda();
			if(serviciosQueBrinda.contains(textoBusqueda) || this.isInTagsList(textoBusqueda)){
				return true;
			}
			else{
				return false;
			}
		}
		
		public boolean estaDisponible (LocalDateTime unTiempo, Servicio Valor){
			ArrayList<Servicio> servicioDisponible = this.getComuna().getServiciosQueBrinda();
			if (servicioDisponible.contains(Valor)){
				RangoDeAtencion rangoServicio = Valor.getRangoDeAtencion();
				return (rangoServicio.disponible(unTiempo));
			}
			else{
				return true;
			}
			
			
		}
}
