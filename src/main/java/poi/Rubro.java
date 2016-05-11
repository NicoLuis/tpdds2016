package main.java.poi;

public class Rubro {
	//CONSTRUCTOR 
	
		public Rubro(Double unRadioDeCercania){
			this.setRadioDeCercania(unRadioDeCercania);
		}	
		
		//ATRIBUTOS
		
		private Double radioDeCercania;
		
		//GETTERS Y SETTERS
		
		public Double getRadioDeCercania(){
			return radioDeCercania;
		}
		
		public void setRadioDeCercania(Double unRadioDeCercania){
			radioDeCercania = unRadioDeCercania;
		}
}
