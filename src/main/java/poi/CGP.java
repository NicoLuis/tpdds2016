package poi;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import java.util.ArrayList;
import java.util.List;

public class CGP extends POI{
	
	private List<Servicio> servicios = new ArrayList<Servicio>();
	@SuppressWarnings("unused")
	private String telefono;
	@SuppressWarnings("unused")
	private String nombreDirector;
	 

	public CGP(Polygon pol, Servicio service, String nom) {
		comuna = pol;
		servicios.add(service);
		nombre = nom; 
	}
	public CGP(Polygon pol, String nom) {
		comuna = pol;
		nombre = nom;
	}
	public CGP() {
	};
		
		    
		
	@Override
	public boolean estaCerca(Point unaUbicacion){
		return comuna.isInside(unaUbicacion);
	}
	
	@Override
	public boolean coincideConLaBusqueda(String textoBusqueda){
		for(Servicio servicio: getServicios())
			if(servicio.getNombre().contains(textoBusqueda) || this.isInTagsList(textoBusqueda))
				return true;
		return false;
	}
	
	public boolean estaDisponible (LocalDateTime unTiempo, String valor){
		if (getServicios().stream().anyMatch( serv -> serv.getNombre() == valor )){
			return	getServicios().stream().
						filter( serv -> serv.getNombre() == valor ).
						allMatch( serv -> serv.estaDisponible(unTiempo));
		}
		return false;
	}
	
	@Override
	public boolean estaDisponible(LocalDateTime unTiempo){
		return	getServicios().stream().
					anyMatch( serv -> serv.estaDisponible(unTiempo));
	}

	
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}
	
	public String tipo(){
		return "CGP";
	}
	
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
		
		
		
		
		
	public Polygon getArea() {
		return comuna;
	}
		
}
