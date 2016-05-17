package main.java.poi;

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
			ArrayList<String> serviciosQueBrinda= this.getComuna().getServiciosQueBrinda();
			if(serviciosQueBrinda.contains(textoBusqueda) || this.isInTagsList(textoBusqueda)){
				return true;
			}
			else{
				return false;
			}
		}
}
