package poi;

import java.util.ArrayList;
import java.util.List;


public class Rubro {
	//CONSTRUCTOR 
	
		public Rubro(double unRadioDeCercania){
			this.setRadioDeCercania(unRadioDeCercania);
			setRubrosALosQuePertence();
			this.setNombre("");
		}	
		
		public Rubro(String nombre, double unRadioDeCercania){
			this.setRadioDeCercania(unRadioDeCercania);
			this.setNombre(nombre);
			setRubrosALosQuePertence();
		}	
		
		//ATRIBUTOS
		
		private double radioDeCercania;
		private ArrayList<String> rubrosALosQuePertence;//Array de String que contienen los tipos de rubro a los que pertenece la clase que lo llama
		String nombre;
		List<LocalComercial> listaLocales;
		
		//GETTERS Y SETTERS
		
		public double getRadioDeCercania(){
			return radioDeCercania;
		}
		public String getNombre(){
			return nombre;
		}
		public void setNombre(String nombre){
			this.nombre = nombre;
		}	
		
		public void setRadioDeCercania(double unRadioDeCercania){
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
