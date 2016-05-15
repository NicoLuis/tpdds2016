package main.java.poi;
import org.uqbar.geodds.Polygon;
import java.util.ArrayList;
public class Comuna {
	//ATRIBUTOS
	
		private Polygon zona;
		private ArrayList<String> serviciosQueBrinda;
		
		//GETERS Y SETERS 
		
		public Polygon getZona() {
			return zona;
		}

		public void setZona(Polygon zona) {
			this.zona = zona;
		}
		
		public void setServiciosQueBrinda(){
			serviciosQueBrinda=  new ArrayList<String>();
		}
		public ArrayList<String> getServiciosQueBrinda(){
			return serviciosQueBrinda;
		}
		public void addServiciosQueBrinda(String unServicio){
			serviciosQueBrinda.add(unServicio);
		}
}