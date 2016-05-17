package main.java.poi;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CGP extends POI{
	
		private ArrayList<Servicio> servicios;
	
	//CONSTRUCTOR
	
	
		public CGP(Point ubicacion, Comuna unaComuna) {
			super(ubicacion, unaComuna);
				
		}
		public CGP(){
			this.servicios = new ArrayList<Servicio>();
		}
		
		    
		
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
		
		public boolean estaDisponible (LocalDateTime unTiempo, String valor){
			if (getServicios().stream().anyMatch( serv -> serv.getNombre() == valor )){
				return	getServicios().stream().
							filter( serv -> serv.getNombre() == valor ).
							allMatch( serv -> serv.getRangoDeAtencion().disponible(unTiempo));
			}
			return false;
		}
		
		public boolean estaDisponible (LocalDateTime unTiempo){
			return	getServicios().stream().
						anyMatch( serv -> serv.getRangoDeAtencion().disponible(unTiempo));
		}
		
		public ArrayList<Servicio> getServicios() {
			return servicios;
		}
		public void setServicios(ArrayList<Servicio> servicios) {
			this.servicios = servicios;
		}
		public void addServicio(Servicio servicio) {
			this.servicios.add(servicio);
		}
		
		
}
