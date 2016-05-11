package main.java.poi;

import org.uqbar.geodds.Point;

public class SucursalBanco extends POI{
	public SucursalBanco(Point ubicacion, Comuna comuna8) {
		super(ubicacion, comuna8);
		
	}

	public double cercaniaRequerida(){
		return 500.0;
	}
}
