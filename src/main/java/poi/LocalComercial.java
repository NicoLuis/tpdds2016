package main.java.poi;

import org.uqbar.geodds.Point;

public class LocalComercial extends POI{
	public LocalComercial(Point unaUbicacion, Comuna comuna8, Rubro unRubro){
		
		super(unaUbicacion,comuna8);
		this.setRubro(unRubro);
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

}
