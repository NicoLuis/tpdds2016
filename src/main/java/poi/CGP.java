package main.java.poi;

import org.uqbar.geodds.Point;

public class CGP extends POI{
	//CONSTRUCTOR
	
		public CGP(Point ubicacion, Comuna unaComuna) {
			super(ubicacion, unaComuna);
				
		
		}
		
		@Override
		public boolean estaCercaDe(Point unaUbicacion){
			return this.getComuna().getZona().isInside(unaUbicacion);
		}
}
