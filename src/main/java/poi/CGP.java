package poi;
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
			super();
			this.servicios = new ArrayList<Servicio>();
		}
		
		    
		
		@Override
		public boolean estaCercaDe(Point unaUbicacion){
			return this.getComuna().getZona().isInside(unaUbicacion);
		}
		
		@Override
		public boolean coincideConLaBusqueda(String textoBusqueda){
			for(Servicio servicio: getServicios()){
				if(servicio.getNombre().contains(textoBusqueda) || this.isInTagsList(textoBusqueda)){
					return true;
				}
			}
			return false;
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
