package poi;

import org.uqbar.geodds.Point;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class LocalComercial extends POI{
	
	private Rubro rubro;
	private List<String> palabrasClave = new ArrayList<String>();
	
	public LocalComercial(String nombre, Point unaUbicacion, Rubro unRubro){
		super();
		this.setNombre(nombre);
		this.setUbicacion(unaUbicacion);
		this.setRubro(unRubro);
	}
	public LocalComercial(){
		super();
		this.setRubro(new Rubro(0));
	}
	
	//GETTERS Y SETTERS
	
	public Rubro getRubro(){
		return rubro;
	}
	
	public void setRubro(Rubro unRubro){
		rubro = unRubro;
	}
	public List<String> getPalabrasClave(){
		return this.palabrasClave;
	}
	public void setPalabrasClave(List<String> palabrasClave){
		this.palabrasClave = palabrasClave;
	}
	//METODOS
	
	public double cercaniaRequerida(){
		return this.getRubro().getRadioDeCercania();
	}
	@Override
	public boolean coincideConLaBusqueda(String textoBusqueda){
		if(nombre.contains(textoBusqueda) || rubro.getRubrosALosQuePertence().contains(textoBusqueda) 
				|| this.isInTagsList(textoBusqueda) || rubro.getNombre().contains(textoBusqueda) )
			return true;
		return false;
	}
	
	public boolean estaDisponible (LocalDateTime unTiempo){
		
		List<Franja> rangoLocal = super.getFranjaHoraria();
		return rangoLocal.stream().anyMatch(rango -> rango.estaDisponible(unTiempo));
		
	}
	
	public String tipo(){
		return "Local Comercial";
	}
	@Override
	public void setIcono(){
		icono="glyphicons glyphicons-shopping-bag";
	}
	@Override
	public void setDetalles(){
		String direccion= this.getDireccion().getCalle() + this.getDireccion().getNumero();
		detalles.put("Icono",this.getIcono());
		detalles.put("Direccion",direccion);
		detalles.put("Nombre",this.getNombre());
		detalles.put("Rubro",this.getRubro().getNombre());
	}
}
