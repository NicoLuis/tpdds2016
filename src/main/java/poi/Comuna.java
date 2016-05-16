package main.java.poi;
import org.uqbar.geodds.Polygon;
import java.util.ArrayList;
public class Comuna {
	//ATRIBUTOS
	
		private Polygon zona;
		private ArrayList<Servicio> serviciosQueBrinda;
		
		//GETERS Y SETERS 
		
		public Polygon getZona() {
			return zona;
		}

		public void setZona(Polygon zona) {
			this.zona = zona;
		}
		
		public void setServiciosQueBrinda(){
			serviciosQueBrinda=  new ArrayList<Servicio>();
		}
		public ArrayList<Servicio> getServiciosQueBrinda(){
			return serviciosQueBrinda;
		}
	
		public void addServiciosQueBrinda(Servicio unServicio){
			serviciosQueBrinda.add(unServicio);
		}
}