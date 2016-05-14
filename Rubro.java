package main.java.poi;

import java.util.ArrayList;

public class Rubro {
	//CONSTRUCTOR 
	
		public Rubro(Double unRadioDeCercania){
			this.setRadioDeCercania(unRadioDeCercania);
		}	
		
		//ATRIBUTOS
		
		private Double radioDeCercania;
		private ArrayList<String> rubrosALosQuePertence;//Array de String que contienen los tipos de rubro a los que pertenece la clase que lo llama
		
		//GETTERS Y SETTERS
		
		public Double getRadioDeCercania(){
			return radioDeCercania;
		}
		
		public void setRadioDeCercania(Double unRadioDeCercania){
			radioDeCercania = unRadioDeCercania;
		}	
		public void setRubrosALosQuePertence(){
			rubrosALosQuePertence=  new ArrayList<String>();
		}
		public ArrayList<String> getRubrosALosQuePertence(){
			return rubrosALosQuePertence;
		}
		public void addRubrosALosQuePertenece(String unRubro){
			rubrosALosQuePertence.add(unRubro);
		}
		
		
}
